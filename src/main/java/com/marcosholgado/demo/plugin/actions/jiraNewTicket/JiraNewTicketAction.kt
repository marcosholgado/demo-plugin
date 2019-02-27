package com.marcosholgado.demo.plugin.actions.jiraNewTicket

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class JiraNewTicketAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val dialog = JiraNewTicketDialog(event.project!!)
        dialog.show()
    }
}