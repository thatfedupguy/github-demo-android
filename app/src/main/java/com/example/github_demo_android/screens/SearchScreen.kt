package com.example.github_demo_android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.github_demo_android.R
import com.example.github_demo_android.composables.HorizontalDivider
import com.example.github_demo_android.composables.ToolbarView
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.directions.SearchUserDirections
import com.example.github_demo_android.orDefault
import com.example.github_demo_android.ui.theme.backgroundColor
import com.example.github_demo_android.ui.theme.secondaryColor
import com.example.github_demo_android.ui.theme.type.interMedium
import com.example.github_demo_android.viewmodel.SearchEvents

@Composable
fun SearchScreen(
    isLoading: Boolean,
    userNotFound: Boolean,
    query: String,
    suggestions: List<User>?,
    setEvent: (SearchEvents) -> Unit,
    navigate: (SearchUserDirections) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
    ) {
        ToolbarView(
            modifier = Modifier.padding(16.dp),
            title = "Search",
            showBack = false
        )
        SearchView(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = query,
            onValueChange = {
                setEvent(SearchEvents.EditQuery(it))
            }
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            suggestions?.let {
                it.forEachIndexed { index, user ->
                    UserComposable(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navigate(SearchUserDirections.UserProfile(user.login.orDefault()))
                            },
                        user = user
                    )
                    if (index != it.size - 1) {
                        HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
            if (isLoading) {
                Loader()
            }
            if (userNotFound && query.isNotEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    text = "Uh-oH!, No user found",
                    style = interMedium.titleMedium.copy(
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator(
            modifier = modifier.padding(vertical = 16.dp),
            color = colorResource(id = R.color.white),
        )
    }
}

@Composable
fun UserComposable(
    modifier: Modifier = Modifier,
    user: User
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(2.dp, color = Color.White, shape = CircleShape),
            model = user.avatar_url,
            contentDescription = "User Profile"
        )
        Text(
            modifier = Modifier.weight(1f),
            text = user.login.orDefault(),
            style = interMedium.titleMedium
        )
        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "User Profile"
        )
    }
}

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        cursorBrush = SolidColor(Color.White),
        modifier = modifier.fillMaxWidth(),
        textStyle = interMedium.titleMedium,
        value = text,
        onValueChange = {
            onValueChange(it)
        }
    ) { innerTextField ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = android.R.drawable.ic_menu_search),
                    contentDescription = "Search View"
                )
                innerTextField()
            }
        }
    }
}