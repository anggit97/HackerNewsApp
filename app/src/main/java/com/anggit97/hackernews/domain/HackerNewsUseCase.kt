package com.anggit97.hackernews.domain

import com.anggit97.hackernews.data.Comment
import com.anggit97.hackernews.data.TopStoryDetail
import com.anggit97.hackernews.utils.safeApiCall
import com.anggit97.hackernews.utils.safeApiErrorHandling
import com.anggit97.hackernews.utils.state.ResultState
import javax.inject.Inject

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
class HackerNewsUseCase @Inject constructor(
    private val repository: HackerNewsRepository
) {

    suspend fun getTopStories(): ResultState<List<Int>>{
        return safeApiCall {
            val response = repository.getTopStories()
            if (response.isSuccessful){
                ResultState.Success(response.body()!!)
            }else{
                safeApiErrorHandling(response)
            }
        }
    }

    suspend fun getTopStoryDetail(storyId: String): ResultState<TopStoryDetail>{
        return safeApiCall {
            val response = repository.getStoryDetail(storyId)
            if (response.isSuccessful){
                ResultState.Success(response.body()!!)
            }else{
                safeApiErrorHandling(response)
            }
        }
    }

    suspend fun getComment(commentId: String): ResultState<Comment>{
        return safeApiCall {
            val response = repository.getComment(commentId)
            if (response.isSuccessful){
                ResultState.Success(response.body()!!)
            }else{
                safeApiErrorHandling(response)
            }
        }
    }
}