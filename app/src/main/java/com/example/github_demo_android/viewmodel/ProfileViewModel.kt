package com.example.github_demo_android.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_demo_android.api.ApiResult
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.repo.ProfileRepo
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel @Inject constructor(
    private val profileRepo: ProfileRepo
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun getUser(login: String){
        _uiState.update {
            it.copy(
                isLoading = true,
                isError = false
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            when(val userResponse = profileRepo.getUser(login)){
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
    val user: User ?= null,
)