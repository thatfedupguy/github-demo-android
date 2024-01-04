package com.example.github_demo_android.api

import com.example.github_demo_android.data.responseModels.SearchResponseModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchUserApi {
    @GET("search/users")
    suspend fun searchUsers(
        @QueryMap params: MutableMap<String, Any?>
    ): SearchResponseModel
}