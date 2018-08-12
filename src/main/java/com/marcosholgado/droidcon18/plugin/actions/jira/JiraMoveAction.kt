package com.marcosholgado.droidcon18.plugin.actions.jira

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.marcosholgado.droidcon18.plugin.actions.jira.di.DaggerJiraComponent
import com.marcosholgado.droidcon18.plugin.actions.jira.di.JiraModule
import com.marcosholgado.droidcon18.plugin.actions.jira.network.Transition
import javax.inject.Inject
import javax.swing.JFrame

class JiraMoveAction : AnAction() {
    private val panel = JiraMovePanel()
    private var project: Project? = null
    @Inject
    lateinit var presenter: JiraMoveActionPresenter


    override fun actionPerformed(event: AnActionEvent) {

        project = event.project

        DaggerJiraComponent.builder()
                .jiraModule(JiraModule(this, project!!))
                .build().inject(this)

        val frame = JFrame()
        frame.contentPane = panel.mainPanel
        frame.pack()
        frame.isVisible = true

        presenter.getBranch()
    }

    fun setBranch(branch: String) {
        panel.branchField?.text = branch
    }

    fun addTransition(transition: Transition) {
        panel.transitionCombo?.addItem(transition)
    }
}