package com.example.github_demo_android.data.responseModels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponseModel(
    val items: List<User>?,
)

@JsonClass(generateAdapter = true)
data class User(
    val login: String?,
    val id: Long?,
    val avatar_url: String?,
    val url: String?,
    val public_repos: Int?,
    val public_gists: Int?,
    val followers: Int?,
    val following: Int?,
    val name: String?,
    val bio: String?,
    // "Not found" for invalid username
    val message: String?
)
