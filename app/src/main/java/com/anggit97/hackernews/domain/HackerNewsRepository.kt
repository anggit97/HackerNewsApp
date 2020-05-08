package com.anggit97.hackernews.domain

import com.anggit97.hackernews.data.Comment
import com.anggit97.hackernews.data.TopStoryDetail
import retrofit2.Response

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
interface HackerNewsRepository {

    /**
     * Get Top Stories
     */
    suspend fun getTopStories(): Response<List<Int>>

    /**
     * Get Story Detail
     */
    suspend fun getStoryDetail(storyId: String): Response<TopStoryDetail>

    /**
     * Get Comment
     */
    suspend fun getComment(commentId: String): Response<Comment>
}