package com.example.github_demo_android.directions

interface ProfileDirections {
    data object FollowersList: ProfileDirections
    data object FollowingList: ProfileDirections
    data object Back : ProfileDirections
}