package com.marcosholgado.droidcon18.plugin.actions.jira

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.marcosholgado.droidcon18.plugin.actions.jira.di.DaggerJiraComponent
import com.marcosholgado.droidcon18.plugin.actions.jira.di.JiraModule
import com.marcosholgado.droidcon18.plugin.actions.jira.network.Transition
import javax.inject.Inject
import javax.swing.*


class JiraMoveDialog constructor(val project: Project) : DialogWrapper(true) {

    @Inject
    lateinit var presenter: JiraMoveDialogPresenter

    private val panel : JiraMovePanel = JiraMovePanel()

    init {
        DaggerJiraComponent.builder()
                .jiraModule(JiraModule(this, project))
                .build().inject(this)

        isModal = true
        presenter.load()

        init()
    }

    override fun createCenterPanel(): JComponent = panel

    override fun doOKAction() {
        presenter.doTransition(panel.getTransition(), "")
    }

    fun close() {
        close(DialogWrapper.OK_EXIT_CODE)
    }

    fun setBranch(branch: String) = panel.setBranch(branch)

    fun loadTransitions(transitionList: List<Transition>) {
        for(transition in transitionList) {
            panel.addTransition(transition)
        }
    }

}