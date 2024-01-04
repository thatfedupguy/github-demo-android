package com.example.github_demo_android.repo

import com.example.github_demo_android.api.ApiResult
import com.example.github_demo_android.data.responseModels.User

interface ProfileRepo {
    suspend fun getUser(username: String): ApiResult<User>
}