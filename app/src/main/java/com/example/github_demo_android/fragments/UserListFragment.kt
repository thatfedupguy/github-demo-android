package com.example.github_demo_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.github_demo_android.R
import com.example.github_demo_android.composables.HorizontalDivider
import com.example.github_demo_android.composables.ToolbarView
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.navigateBack
import com.example.github_demo_android.navigateForward
import com.example.github_demo_android.orDefault
import com.example.github_demo_android.screens.Loader
import com.example.github_demo_android.ui.theme.backgroundColor
import com.example.github_demo_android.ui.theme.type.interMedium
import com.example.github_demo_android.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel by viewModels<UserListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val users = viewModel.users.collectAsLazyPagingItems()
            val itemCount = users.itemCount
            val loadState = users.loadState
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = backgroundColor)
            ) {
                ToolbarView(
                    title = context.getString(R.string.users),
                    backClicked = {
                        navigateBack()
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if ((itemCount == 0) && loadState.append is LoadState.NotLoading && loadState.append.endOfPaginationReached) {
                        Text(
                            "No User Found!",
                            style = interMedium.titleMedium
                        )
                    } else {
                        LazyColumn(
                            state = rememberLazyListState(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize(),
                            content = {
                                items(count = itemCount) { index ->
                                    val user = users[index]
                                    user?.let {
                                        UserListItem(user = user)
                                        HorizontalDivider()
                                    }
                                }
                                item {
                                    PagingLoadingComposable(loadState = loadState)
                                }
                            })
                    }
                }
            }
        }
    }
    @Composable
    fun UserListItem(
        modifier: Modifier = Modifier,
        user: User
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .clickable {
                    navigateForward(
                        UserListFragmentDirections.actionUserListFragmentToProfileFragment(login = user.login.orDefault())
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .border(2.dp, color = Color.White, shape = CircleShape),
                model = user.avatar_url,
                contentDescription = stringResource(R.string.user_profile)
            )
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = user.login.orDefault(),
                    style = interMedium.titleMedium
                )
            }
        }
    }

    @Composable
    private fun PagingLoadingComposable(
        loadState: CombinedLoadStates?
    ) {
        when {
            loadState?.refresh is LoadState.Loading -> {
                Loader()
            }

            loadState?.append is LoadState.Loading -> {
                Loader()
            }

            loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error -> {
                Text(
                    stringResource(id = R.string.uh_oh_something_went_wrong),
                    style = interMedium.titleMedium
                )
            }
        }
    }
}