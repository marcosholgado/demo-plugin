package com.marcosholgado.droidcon18.plugin.actions.jiraMove

import com.intellij.openapi.project.Project
import com.intellij.util.Base64
import com.marcosholgado.droidcon18.plugin.actions.jiraMove.network.*
import com.marcosholgado.droidcon18.plugin.components.JiraComponent
import git4idea.repo.GitRepositoryManager
import hu.akarnokd.rxjava2.swing.SwingSchedulers
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class JiraMoveDialogPresenter @Inject constructor(
        private val view: JiraMoveDialog,
        val project: Project,
        private val jiraService: JiraService
) {

    private var disposable: Disposable? = null
    private var ticket = ""

    fun load() {
        getBranch()
        getTransitions()
    }

    private fun getBranch() {
        val repositoryManager = GitRepositoryManager.getInstance(project)

        for (repository in repositoryManager.repositories) {
            ticket = repository.currentBranch!!.name
            break
        }

        val component = JiraComponent.getInstance(project)
        val match = Regex(component.regex).find(ticket)

        match?.let {
            ticket = match.value
            view.setTicket(ticket)
        }
    }

    private fun getTransitions() {
        val auth = getAuthCode()
        disposable = jiraService.getTransitions(auth, ticket)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(
                        { response ->
                            view.loadTransitions(response.transitions)
                        },
                        { error ->
                            view.error(error)
                        }
                )
    }

    private fun getAuthCode() : String {
        val component = JiraComponent.getInstance(project)
        val username = component.username
        val password = component.password
        val data: ByteArray = "$username:$password".toByteArray(Charsets.UTF_8)
        return "Basic " + Base64.encode(data)
    }

    fun doTransition(selectedItem: Transition, ticket: String, text: String) {
        val auth = getAuthCode()
        val transitionData = TransitionData(selectedItem)
        val completable = mutableListOf(jiraService.doTransition(auth, ticket, transitionData))

        if (text.isNotBlank()) {
            completable.add(jiraService.comment(auth, ticket, Comment(text)))
        }

        disposable = Completable.merge(completable)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(
                        {
                            view.success(selectedItem, ticket)
                        },
                        { error ->
                            view.error(error)
                        }
                )
    }
}