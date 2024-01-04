package com.example.github_demo_android.directions

sealed interface SearchUserDirections {
    data class UserProfile(val login: String): SearchUserDirections
    data object Back: SearchUserDirections
}