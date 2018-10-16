package com.marcosholgado.droidcon18.plugin.actions.jiraMove.network

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface JiraService {

    @GET("3/issue/{issueId}/transitions")
    fun getTransitions(@Header("Authorization") authKey: String,
                       @Path("issueId") issueId: String): Single<TransitionsResponse>

    @POST("3/issue/{issueId}/transitions")
    fun doTransition(@Header("Authorization") authKey: String,
                     @Path("issueId") issueId: String,
                     @Body transitionData: TransitionData): Completable

    @POST("2/issue/{issueId}/comment")
    fun comment(@Header("Authorization") authKey: String,
                     @Path("issueId") issueId: String,
                     @Body commentData: Comment): Completable

    @GET("3/search?expand=transitions")
    fun search(@Header("Authorization") authKey: String,
                       @Query("jql") jql: String): Single<SearchResponse>

    @PUT("3/issue/{issueId}/assignee")
    fun assign(@Header("Authorization") authKey: String,
               @Path("issueId") issueId: String,
               @Body assigneeData: AssigneeData): Completable
}