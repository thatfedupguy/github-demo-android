package com.example.github_demo_android.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.github_demo_android.data.enums.UserListType
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.di.IoDispatcher
import com.example.github_demo_android.repo.UserListRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repo: UserListRepo,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val type: UserListType = savedStateHandle["type"] ?: UserListType.FOLLOWERS
    private val username: String = savedStateHandle["username"] ?: ""
    var users: Flow<PagingData<User>> = when(type){
        UserListType.FOLLOWERS -> {
            repo.getFollowers(username).flowOn(ioDispatcher).cachedIn(viewModelScope)
        }
        UserListType.FOLLOWING -> {
            repo.getFollowingList(username).flowOn(ioDispatcher).cachedIn(viewModelScope)
        }
    }
}

