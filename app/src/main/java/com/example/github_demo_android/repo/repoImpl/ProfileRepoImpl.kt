package com.example.github_demo_android.repo.repoImpl

import com.example.github_demo_android.api.ApiResult
import com.example.github_demo_android.api.ApiService
import com.example.github_demo_android.api.ProfileApi
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.repo.ProfileRepo
import javax.inject.Inject

class ProfileRepoImpl @Inject constructor(
    private val service: ApiService
): ProfileRepo {

    val api = service.buildService(ProfileApi::class.java)

    override suspend fun getUser(username: String): ApiResult<User> {
        return service.getApiResult {
            api.getUser(username)
        }
    }
}