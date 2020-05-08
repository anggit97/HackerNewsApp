package com.anggit97.hackernews.data.repository

import com.anggit97.hackernews.data.Comment
import com.anggit97.hackernews.data.TopStoryDetail
import com.anggit97.hackernews.domain.HackerNewsRepository
import com.anggit97.hackernews.network.HackerNewsApi
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
class HackerNewsRepositoryImpl @Inject constructor(
    private val apiService: HackerNewsApi
): HackerNewsRepository {

    override suspend fun getTopStories(): Response<List<Int>> {
        return apiService.getTopStories()
    }

    override suspend fun getStoryDetail(storyId: String): Response<TopStoryDetail> {
        return apiService.getStoryDetail(storyId)
    }

    override suspend fun getComment(commentId: String): Response<Comment> {
        return apiService.getComment(commentId)
    }
}