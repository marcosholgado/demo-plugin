package com.marcosholgado.droidcon18.plugin.actions.jiraMove.network

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface JiraService {

    @GET("issue/{issueId}/transitions")
    fun getTransitions(@Header("Authorization") authKey: String,
                       @Path("issueId") issueId: String): Single<TransitionsResponse>

    @POST("issue/{issueId}/transitions")
    fun doTransition(@Header("Authorization") authKey: String,
                     @Path("issueId") issueId: String,
                     @Body transitionData: TransitionData): Completable

    @POST("issue/{issueId}/comment")
    fun comment(@Header("Authorization") authKey: String,
                     @Path("issueId") issueId: String,
                     @Body commentData: Comment): Completable

    @GET("search?expand=transitions")
    fun search(@Header("Authorization") authKey: String,
                       @Query("jql") jql: String): Single<SearchResponse>

    @PUT("issue/{issueId}/assignee")
    fun assign(@Header("Authorization") authKey: String,
               @Path("issueId") issueId: String,
               @Body assigneeData: AssigneeData): Completable
}