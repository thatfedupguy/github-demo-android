package com.example.github_demo_android.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.github_demo_android.data.enums.UserListType
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.repo.UserListRepo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UserListViewModel @Inject constructor(
    private val repo: UserListRepo
): ViewModel() {
    private val type: UserListType = UserListType.FOLLOWERS
    private val username: String = ""
    var users: Flow<PagingData<User>> = when(type){
        UserListType.FOLLOWERS -> {
            repo.getFollowers(username)
        }
        UserListType.FOLLOWING -> {
            repo.getFollowingList(username)
        }
    }
}

