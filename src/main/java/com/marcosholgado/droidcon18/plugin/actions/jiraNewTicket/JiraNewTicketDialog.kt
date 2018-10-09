package com.marcosholgado.droidcon18.plugin.actions.jiraNewTicket

import com.intellij.notification.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.marcosholgado.droidcon18.plugin.actions.jiraMove.network.Issue
import com.marcosholgado.droidcon18.plugin.actions.jiraNewTicket.di.DaggerJiraNewTicketComponent
import com.marcosholgado.droidcon18.plugin.actions.jiraNewTicket.di.JiraNewTicketModule
import com.marcosholgado.droidcon18.plugin.utils.DroidconBundle.message
import javax.inject.Inject
import javax.swing.*


class JiraNewTicketDialog constructor(val project: Project) : DialogWrapper(true) {

    @Inject
    lateinit var presenter: JiraNewTicketDialogPresenter

    private val panel : JiraNewTicketPanel = JiraNewTicketPanel()

    init {
        DaggerJiraNewTicketComponent.builder()
                .jiraNewTicketModule(JiraNewTicketModule(this, project))
                .build().inject(this)

        isModal = true
        presenter.load()

        init()
    }

    override fun createCenterPanel(): JComponent = panel

    override fun doOKAction() = presenter.workOnIssue(panel.getIssue())

    fun success(ticket: String) {
        close(DialogWrapper.OK_EXIT_CODE)

        ApplicationManager.getApplication().invokeLater {
            val stickyNotification = NotificationGroup("success", NotificationDisplayType.BALLOON, true)
            stickyNotification.createNotification(
                    message("dialog.success"),
                    message("jira.ticket.success", ticket),
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

    fun loadIssues(issuesList: List<Issue>) {
        for(issue in issuesList) {
            panel.addIssue(issue)
        }
    }

}