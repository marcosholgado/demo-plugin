package com.marcosholgado.droidcon18.plugin.actions.jira.di

import com.marcosholgado.droidcon18.plugin.actions.jira.JiraMoveAction
import dagger.Component

@Component(modules = [JiraModule::class])
interface JiraComponent {
    fun inject(jiraMoveAction: JiraMoveAction)
}