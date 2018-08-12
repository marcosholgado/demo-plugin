package com.marcosholgado.droidcon18.plugin.actions.jira.di

import com.intellij.openapi.project.Project
import com.marcosholgado.droidcon18.plugin.actions.jira.JiraMoveAction
import com.marcosholgado.droidcon18.plugin.actions.jira.network.JiraService
import com.marcosholgado.droidcon18.plugin.components.JiraComponent
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class JiraModule(val view: JiraMoveAction, val project: Project) {

    @Provides
    fun provideView() : JiraMoveAction = view

    @Provides
    fun provideProject() : Project = project

    @Provides
    fun providesJiraService() : JiraService {
        val jiraURL = JiraComponent.getInstance(project!!).jiraUrl

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(jiraURL)
                .build()

        return retrofit.create(JiraService::class.java)
    }
}