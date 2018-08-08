package com.marcosholgado.droidcon18.plugin.actions.jira.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface JiraService {

    @GET("issue/{issueId}/transitions")
    fun getTransitions(@Header("Authorization") authKey: String,
                       @Path("issueId") section: String): Single<TransitionsResponse>
}