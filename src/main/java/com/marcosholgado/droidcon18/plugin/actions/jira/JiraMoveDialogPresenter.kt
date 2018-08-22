package com.marcosholgado.droidcon18.plugin.actions.jira

import android.util.Log
import com.intellij.openapi.project.Project
import com.intellij.util.Base64
import com.marcosholgado.droidcon18.plugin.actions.jira.network.*
import com.marcosholgado.droidcon18.plugin.components.JiraComponent
import git4idea.repo.GitRepositoryManager
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class JiraMoveDialogPresenter @Inject constructor(
        private val view: JiraMoveDialog,
        val project: Project,
        private val jiraService: JiraService
) {

    var disposable: Disposable? = null
    var branch: String = ""

    fun load() {
        getBranch()
        getTransitions()
    }

    private fun getBranch() {
        val repositoryManager = GitRepositoryManager.getInstance(project)

        for (repository in repositoryManager.repositories) {
            branch = repository.currentBranch!!.name
            break
        }

        view.setBranch(branch)
    }

    private fun getTransitions() {
        val auth = getAuthCode()
        disposable = jiraService.getTransitions(auth,branch)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { response ->
                            view.loadTransitions(response.transitions)
                        },
                        { error ->
                            Log.d("ERROR", error.message)
                        }
                )
    }

    private fun getAuthCode() : String {
        val component = JiraComponent.getInstance(project!!)
        val username = component.username
        val password = component.password

        val data: ByteArray = "$username:$password".toByteArray(Charsets.UTF_8)
        return "Basic " + Base64.encode(data)
    }

    fun doTransition(selectedItem: Transition, text: String) {
        val auth = getAuthCode()
        val transitionData = TransitionData(selectedItem)

        disposable = jiraService.doTransition(auth, branch, transitionData)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        {
                            view.close()
                            Log.d("YAY", it.toString())
                        },
                        { error ->
                            Log.d("ERROR", error.message)
                        }
                )
    }
}