package com.example.github_demo_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.github_demo_android.directions.SearchUserDirections
import com.example.github_demo_android.navigateBack
import com.example.github_demo_android.navigateForward
import com.example.github_demo_android.screens.SearchScreen
import com.example.github_demo_android.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SearchScreen(
                query = uiState.query,
                isLoading = uiState.isLoading,
                suggestions = uiState.suggestions,
                userNotFound = uiState.userNotFound,
                setEvent = viewModel::setEvents,
                navigate = {
                    when(it){
                        is SearchUserDirections.UserProfile -> {
                            navigateForward(
                                directions = SearchFragmentDirections.actionSearchFragmentToProfileFragment(it.login)
                            )
                        }

                        SearchUserDirections.Back -> {
                            navigateBack()
                        }
                    }
                }
            )
        }
    }
}