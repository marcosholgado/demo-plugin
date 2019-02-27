package com.marcosholgado.demo.plugin.actions.jiraNewTicket.di

import com.marcosholgado.demo.plugin.actions.jiraNewTicket.JiraNewTicketDialog
import dagger.Component

@Component(modules = [JiraNewTicketModule::class])
interface JiraNewTicketComponent {
    fun inject(jiraNewTicketDialog: JiraNewTicketDialog)
}