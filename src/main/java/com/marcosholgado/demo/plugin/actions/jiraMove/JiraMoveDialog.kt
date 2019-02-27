package com.marcosholgado.demo.plugin.actions.jiraMove

import com.intellij.notification.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.marcosholgado.demo.plugin.actions.jiraMove.di.DaggerJiraComponent
import com.marcosholgado.demo.plugin.actions.jiraMove.di.JiraModule
import com.marcosholgado.demo.plugin.actions.jiraMove.network.Transition
import com.marcosholgado.demo.plugin.utils.DemoBundle.message
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

    override fun doOKAction() = presenter.doTransition(panel.getTransition(), panel.txtJiraTicket.text, panel.txtComment.text)

    fun success(transition: Transition, ticket: String) {
        close(DialogWrapper.OK_EXIT_CODE)

        ApplicationManager.getApplication().invokeLater {
            val stickyNotification = NotificationGroup("success", NotificationDisplayType.BALLOON, true)
            stickyNotification.createNotification(
                    message("dialog.success"),
                    message("jira.action.success", ticket, transition.name),
                    NotificationType.INFORMATION
                    , null
            ).notify(project)
        }
    }

    fun error(error: Throwable) {
        ApplicationManager.getApplication().invokeLater {
            val stickyNotification = NotificationGroup("error", NotificationDisplayType.BALLOON, true)
            stickyNotification.createNotification(message("dialog.error"), error.localizedMessage, NotificationType.ERROR, null).notify(project)
        }
    }

    fun setTicket(ticket: String) = panel.setTicket(ticket)

    fun loadTransitions(transitionList: List<Transition>) {
        for(transition in transitionList) {
            panel.addTransition(transition)
        }
    }

}