package com.anggit97.hackernews.network

import com.anggit97.hackernews.data.Comment
import com.anggit97.hackernews.data.TopStoryDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
interface HackerNewsApi {

    /**
     * Get Top Stories
     */
    @Headers("Accept: application/json")
    @GET("topstories.json?print=pretty")
    suspend fun getTopStories(): Response<List<Int>>

    /**
     * Get Story Detail
     */
    @Headers("Accept: application/json")
    @GET("item/{story_id}.json?print=pretty")
    suspend fun getStoryDetail(@Path("story_id") storyId: String): Response<TopStoryDetail>

    /**
     * Get Comment
     */
    @Headers("Accept: application/json")
    @GET("item/{comment_id}.json?print=pretty")
    suspend fun getComment(@Path("comment_id") commentId: String): Response<Comment>
}