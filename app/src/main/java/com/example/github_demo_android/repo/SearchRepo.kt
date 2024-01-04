package com.example.github_demo_android.repo

import com.example.github_demo_android.api.ApiResult
import com.example.github_demo_android.data.responseModels.SearchResponseModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchRepo {
    suspend fun searchUserRepo(params: MutableMap<String, Any?>): ApiResult<SearchResponseModel>
}