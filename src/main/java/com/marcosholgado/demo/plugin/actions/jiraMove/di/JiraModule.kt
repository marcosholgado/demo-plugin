package com.marcosholgado.demo.plugin.actions.jiraMove.di

import com.intellij.openapi.project.Project
import com.marcosholgado.demo.plugin.actions.jiraMove.JiraMoveDialog
import com.marcosholgado.demo.plugin.actions.jiraMove.network.JiraService
import com.marcosholgado.demo.plugin.components.JiraComponent
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class JiraModule(private val view: JiraMoveDialog, val project: Project) {

    @Provides
    fun provideView() : JiraMoveDialog = view

    @Provides
    fun provideProject() : Project = project

    @Provides
    fun providesJiraService() : JiraService {
        val jiraURL = JiraComponent.getInstance(project).jiraUrl

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(jiraURL)
                .build()

        return retrofit.create(JiraService::class.java)
    }
}