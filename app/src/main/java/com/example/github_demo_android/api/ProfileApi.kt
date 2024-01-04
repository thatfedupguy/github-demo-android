package com.example.github_demo_android.api

import android.media.MediaRouter.UserRouteInfo
import com.example.github_demo_android.data.responseModels.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ProfileApi {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): User
}