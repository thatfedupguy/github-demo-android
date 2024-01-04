package com.example.github_demo_android.api

import javax.inject.Inject
import retrofit2.Retrofit

class ApiService @Inject constructor(
    private val retrofit: Retrofit,
) {
    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
    suspend fun <T> getApiResult(callApi: suspend () -> T): ApiResult<T> = try {
        ApiResult.Success(data = callApi())
    } catch (e: Exception){
        ApiResult.Error(message = e.message.toString())
    }
}