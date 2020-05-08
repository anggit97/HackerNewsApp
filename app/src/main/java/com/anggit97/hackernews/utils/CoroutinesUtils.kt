package com.anggit97.hackernews.utils

import com.anggit97.hackernews.utils.state.ResultState
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
suspend fun <T : Any> safeApiCall(apiCall: suspend () -> ResultState<T>): ResultState<T> {
    return try {
        apiCall.invoke()
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultState.NetworkError
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = throwable.message()
                ResultState.GeneralError(code, errorResponse, null)
            }
            is ConnectException -> ResultState.NetworkError
            else -> ResultState.GeneralError(null, null, null)
        }
    }
}

fun <T : Any> safeApiErrorHandling(response: Response<T>): ResultState<T> {
    val errorResponse = response.message()
    return when (response.code()) {
        else -> {
            val errorMessageFromApi = response.errorBody()?.let { decodeErrorMessage(it, "message") }

            when {
                errorMessageFromApi.isNullOrEmpty().not() -> {
                    ResultState.GeneralError(response.code(), errorMessageFromApi, response.errorBody())
                }
                else -> {
                    ResultState.GeneralError(response.code(), errorResponse, response.errorBody())
                }
            }
        }
    }
}

private fun decodeErrorMessage(body: ResponseBody, key: String): String? {
    return JSONObject(body.string()).getString(key)
}