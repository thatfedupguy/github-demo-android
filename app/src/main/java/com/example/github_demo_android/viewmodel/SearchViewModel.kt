package com.example.github_demo_android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_demo_android.api.ApiResult
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.di.IoDispatcher
import com.example.github_demo_android.di.MainDispatcher
import com.example.github_demo_android.repo.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: SearchRepo,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()
    private var searchJob: Job ?= null

    fun setEvents(event: SearchEvents){
        when(event){
            is SearchEvents.EditQuery -> {
                search(event.text)
            }
        }
    }

    private fun search(query: String) {
        _uiState.update {
            it.copy(
                isLoading = true,
                userNotFound = false,
                suggestions = null,
                query = query
            )
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch(ioDispatcher) {
            delay(400)
            ensureActive()
            val params = mutableMapOf<String, Any?>().apply {
                put("q", query)
                put("per_page", 6)
            }
            when( val searchUserResponse = repo.searchUserRepo(params)){
                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            userNotFound = true
                        )
                    }
                }
                is ApiResult.Success -> {
                    val users = searchUserResponse.data.items
                    val userNotFound = users?.isEmpty()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            suggestions = users,
                            userNotFound = userNotFound ?: true
                        )
                    }
                }
            }
        }
    }


}

sealed interface  SearchEvents{
    data class EditQuery(val text: String): SearchEvents
}

data class SearchUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val userNotFound: Boolean = false,
    val suggestions: List<User> ?= null,
)