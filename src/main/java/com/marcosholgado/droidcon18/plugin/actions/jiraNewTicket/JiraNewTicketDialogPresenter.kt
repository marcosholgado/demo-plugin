package com.marcosholgado.droidcon18.plugin.actions.jiraNewTicket

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.util.Base64
import com.marcosholgado.droidcon18.plugin.actions.jiraMove.network.*
import com.marcosholgado.droidcon18.plugin.components.JiraComponent
import com.marcosholgado.droidcon18.plugin.utils.DroidconBundle.message
import git4idea.branch.GitBrancher
import git4idea.repo.GitRepositoryManager
import hu.akarnokd.rxjava2.swing.SwingSchedulers
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class JiraNewTicketDialogPresenter @Inject constructor(
        private val view: JiraNewTicketDialog,
        val project: Project,
        private val jiraService: JiraService
) {

    private var disposable: Disposable? = null

    fun load() {
        getIssues()
    }

    private fun createBranch(name: String): Completable {
        return Completable.create {
            val repositoryManager = GitRepositoryManager.getInstance(project)
            val brancher = ServiceManager.getService(project, GitBrancher::class.java)
            brancher.checkoutNewBranch(name, repositoryManager.repositories)
            it.onComplete()
        }
    }

    private fun getIssues() {
        val component = JiraComponent.getInstance(project)
        val jql = component.jql
        val auth = getAuthCode()
        disposable = jiraService.search(auth, jql)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(
                        { response ->
                            view.loadIssues(response.issues)
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
        return message("auth.basic", Base64.encode(data))
    }

    fun workOnIssue(issue: Issue) {
        val component = JiraComponent.getInstance(project)
        val auth = getAuthCode()
        val branchName = issue.key

        val transitionData = TransitionData(issue.transitions[0])
        val assigneeData = AssigneeData(component.name)

        val completable = mutableListOf(jiraService.doTransition(auth, branchName, transitionData))
        completable.add(jiraService.assign(auth, branchName, assigneeData))
        completable.add(createBranch(branchName))

        disposable = Completable.merge(completable)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(
                        {
                            view.success(branchName)
                        },
                        { error ->
                            view.error(error)
                        }
                )
    }
}