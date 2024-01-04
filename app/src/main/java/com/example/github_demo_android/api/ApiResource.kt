package com.example.github_demo_android.api

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T): ApiResult<T>()
    data class Error(val code: Int?=null, val message: String): ApiResult<Nothing>()
}