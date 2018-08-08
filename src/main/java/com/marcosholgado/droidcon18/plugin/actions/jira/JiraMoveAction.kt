package com.marcosholgado.droidcon18.plugin.actions.jira

import android.util.Log
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.util.Base64
import com.marcosholgado.droidcon18.plugin.actions.jira.network.JiraService
import com.marcosholgado.droidcon18.plugin.components.JiraComponent
import git4idea.repo.GitRepositoryManager
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.swing.JFrame

class JiraMoveAction: AnAction() {
    var disposable: Disposable? = null
    private val panel = JiraMovePanel()
    private var project: Project? = null

    override fun actionPerformed(event: AnActionEvent) {

        project = event.project

        val frame = JFrame()
        frame.contentPane = panel.mainPanel
        frame.pack()
        frame.isVisible = true

        getTransitions()
    }

    fun getTransitions() {

        val component = JiraComponent.getInstance(project!!)
        val username = component.username
        val password = component.password
        val jiraURL = component.jiraUrl

        val data: ByteArray = "$username:$password".toByteArray(Charsets.UTF_8)
        val auth = "Basic " + Base64.encode(data)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(jiraURL)
                .build()

        val service = retrofit.create(JiraService::class.java)

        val repositoryManager = GitRepositoryManager.getInstance(project!!)
        var branch = ""

        for (repository in repositoryManager.repositories) {
            branch = repository.currentBranch!!.name
            break
        }

        panel.branchField?.text = branch

        disposable = service.getTransitions(auth,branch)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { response ->
                            for(transition in response.transitions) {
                                panel.transitionCombo?.addItem(transition)
                            }
                        },
                        { error ->
                            Log.d("ERROR", error.message)
                        }
                )
    }
}