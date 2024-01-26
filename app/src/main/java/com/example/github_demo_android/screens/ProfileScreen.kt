package com.example.github_demo_android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.github_demo_android.R
import com.example.github_demo_android.basicClickable
import com.example.github_demo_android.composables.HideLoader
import com.example.github_demo_android.composables.ShowLoader
import com.example.github_demo_android.composables.ToolbarView
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.directions.ProfileDirections
import com.example.github_demo_android.orDefault
import com.example.github_demo_android.ui.theme.backgroundColor
import com.example.github_demo_android.ui.theme.ribbonBlue
import com.example.github_demo_android.ui.theme.type.interMedium
import com.example.github_demo_android.ui.theme.type.interSemiBold

@Composable
fun ProfileScreen(
    isLoading: Boolean,
    isError: Boolean,
    user: User?,
    navigate: (ProfileDirections) -> Unit
) {
    if (isLoading) {
        ShowLoader()
    } else {
        HideLoader()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isError) {
            Text(stringResource(id = R.string.uh_oh_something_went_wrong))
        } else if (user != null) {
            ToolbarView(
                title = "Profile",
                backClicked = {
                    navigate(ProfileDirections.Back)
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .background(ribbonBlue)
                )
                AsyncImage(
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .size(128.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .border(width = 2.dp, color = Color.White, shape = CircleShape),
                    model = user.avatar_url,
                    contentDescription = stringResource(R.string.user_avatar)
                )
            }
            Text(
                text = user.name.orDefault(),
                style = interSemiBold.titleLarge
            )
            Text(
                text = user.login.orDefault(),
                style = interMedium.titleMedium
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.user_dashboard),
                    style = interSemiBold.titleMedium.copy(color = Color.Black)
                )
                Text(
                    text = stringResource(R.string.bio, user.bio.orDefault()),
                    style = interMedium.titleMedium.copy(color = Color.Black)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = stringResource(R.string.public_repo, user.public_repos.orDefault()),
                        style = interMedium.bodyMedium.copy(color = Color.Black)
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = stringResource(R.string.public_gists, user.public_gists.orDefault()),
                        style = interMedium.bodyMedium.copy(
                            color = Color.Black,
                            textAlign = TextAlign.End
                        )
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .basicClickable {
                                navigate(ProfileDirections.FollowersList)
                            },
                        text = stringResource(R.string.followers, user.followers.orDefault()),
                        style = interMedium.bodyMedium.copy(color = ribbonBlue)
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .basicClickable {
                                navigate(ProfileDirections.FollowingList)
                            },
                        text = stringResource(R.string.following, user.following.orDefault()),
                        style = interMedium.bodyMedium.copy(
                            color = ribbonBlue,
                            textAlign = TextAlign.End
                        )
                    )
                }
            }
        }
    }
}
