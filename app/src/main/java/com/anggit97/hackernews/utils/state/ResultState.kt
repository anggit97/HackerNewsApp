package com.anggit97.hackernews.utils.state

import okhttp3.ResponseBody

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
sealed class ResultState<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultState<T>()
    data class GeneralError(val code: Int?, val error: String?, val errorBody: ResponseBody?) : ResultState<Nothing>()
    object NetworkError : ResultState<Nothing>()
}