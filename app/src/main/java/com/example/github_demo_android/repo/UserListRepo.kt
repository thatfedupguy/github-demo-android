package com.example.github_demo_android.repo

import androidx.paging.PagingData
import com.example.github_demo_android.data.responseModels.User
import kotlinx.coroutines.flow.Flow

interface UserListRepo {
    fun getFollowers(username: String): Flow<PagingData<User>>
    fun getFollowingList(username: String): Flow<PagingData<User>>
}