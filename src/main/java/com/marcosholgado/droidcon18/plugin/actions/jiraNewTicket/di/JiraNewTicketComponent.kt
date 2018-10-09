package com.marcosholgado.droidcon18.plugin.actions.jiraNewTicket.di

import com.marcosholgado.droidcon18.plugin.actions.jiraNewTicket.JiraNewTicketDialog
import dagger.Component

@Component(modules = [JiraNewTicketModule::class])
interface JiraNewTicketComponent {
    fun inject(jiraNewTicketDialog: JiraNewTicketDialog)
}