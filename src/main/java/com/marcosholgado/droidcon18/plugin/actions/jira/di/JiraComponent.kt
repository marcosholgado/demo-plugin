package com.marcosholgado.droidcon18.plugin.actions.jira.di

import com.marcosholgado.droidcon18.plugin.actions.jira.JiraMoveDialog
import dagger.Component

@Component(modules = [JiraModule::class])
interface JiraComponent {
    fun inject(jiraMoveDialog: JiraMoveDialog)
}