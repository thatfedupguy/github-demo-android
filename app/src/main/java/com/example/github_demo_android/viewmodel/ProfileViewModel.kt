package com.example.github_demo_android.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_demo_android.api.ApiResult
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.di.IoDispatcher
import com.example.github_demo_android.repo.ProfileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val profileRepo: ProfileRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()
    val username: String = savedStateHandle["login"] ?: ""

    init {
        if (username.isNotEmpty()) getUser() else _uiState.update {
            it.copy(
                isError = true
            )
        }
    }

    private fun getUser() {
        _uiState.update {
            it.copy(
                isLoading = true,
                isError = false
            )
        }
        viewModelScope.launch(ioDispatcher) {
            when (val userResponse = profileRepo.getUser(username)) {
                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }

                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            user = userResponse.data
                        )
                    }
                }
            }
        }
    }

}

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val user: User? = null,
)