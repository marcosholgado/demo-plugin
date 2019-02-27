package com.marcosholgado.demo.plugin.actions.jiraMove.di

import com.marcosholgado.demo.plugin.actions.jiraMove.JiraMoveDialog
import dagger.Component

@Component(modules = [JiraModule::class])
interface JiraComponent {
    fun inject(jiraMoveDialog: JiraMoveDialog)
}