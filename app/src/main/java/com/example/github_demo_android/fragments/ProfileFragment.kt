package com.example.github_demo_android.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.navArgs
import com.example.github_demo_android.data.enums.UserListType
import com.example.github_demo_android.directions.ProfileDirections
import com.example.github_demo_android.navigateBack
import com.example.github_demo_android.navigateForward
import com.example.github_demo_android.screens.ProfileScreen
import com.example.github_demo_android.viewmodel.ProfileViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ProfileFragment: Fragment() {

    private val args by navArgs<ProfileFragmentArgs>()
    @Inject lateinit var viewModel: ProfileViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ProfileScreen(
                isLoading = uiState.isLoading,
                isError = uiState.isError,
                user = uiState.user,
                navigate = {
                    when(it){
                        ProfileDirections.FollowingList -> {
                            navigateForward(ProfileFragmentDirections.actionProfileFragmentToUserListFragment(type = UserListType.FOLLOWING, username = args.login))
                        }
                        ProfileDirections.FollowersList -> {
                            navigateForward(ProfileFragmentDirections.actionProfileFragmentToUserListFragment(type = UserListType.FOLLOWERS, username = args.login))
                        }
                        ProfileDirections.Back -> {
                            navigateBack()
                        }
                    }
                }
            )
        }
    }
}