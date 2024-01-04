package com.example.github_demo_android.api

import com.example.github_demo_android.data.responseModels.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserListApi {

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String,
        @Query("per_page") pageSize: Int,
        @Query("page") page: Int
    ): Response<List<User>>

    @GET("users/{username}/following")
    suspend fun getFollowingList(
        @Path("username") username: String,
        @Query("per_page") pageSize: Int,
        @Query("page") page: Int
    ): Response<List<User>>
}