package com.example.github_demo_android.repo.repoImpl

import com.example.github_demo_android.api.ApiResult
import com.example.github_demo_android.api.ApiService
import com.example.github_demo_android.api.SearchUserApi
import com.example.github_demo_android.data.responseModels.SearchResponseModel
import com.example.github_demo_android.repo.SearchRepo
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(
    private val service: ApiService
): SearchRepo {

    val api = service.buildService(SearchUserApi::class.java)
    override suspend fun searchUserRepo(params: MutableMap<String, Any?>): ApiResult<SearchResponseModel> {
        return service.getApiResult {
            api.searchUsers(params = params)
        }
    }
}