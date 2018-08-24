package com.marcosholgado.droidcon18.plugin.actions.jiraMove.di

import com.marcosholgado.droidcon18.plugin.actions.jiraMove.JiraMoveDialog
import dagger.Component

@Component(modules = [JiraModule::class])
interface JiraComponent {
    fun inject(jiraMoveDialog: JiraMoveDialog)
}