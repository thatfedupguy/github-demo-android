package com.example.github_demo_android.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.github_demo_android.MainActivity
import com.example.github_demo_android.R
import com.example.github_demo_android.ui.theme.type.interSemiBold
import dagger.hilt.android.internal.managers.ViewComponentManager

@Composable
fun ShowToast(
    text: String
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit, block = {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    })
}

@Composable
fun ShowLoader() {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit, block = {
        context.showLoader()
    })
}

@Composable
fun HideLoader() {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit, block = {
        context.hideLoader()
    })
}

fun Context.showLoader(){
    if(this is MainActivity){
        this.showLoader()
    }
    if(this is ViewComponentManager.FragmentContextWrapper){
        if(baseContext is MainActivity){
            (baseContext as MainActivity).showLoader()
        }
    }
}

fun Context.hideLoader(){
    if(this is MainActivity){
        this.hideLoader()
    }
    if(this is ViewComponentManager.FragmentContextWrapper){
        if(baseContext is MainActivity){
            (baseContext as MainActivity).hideLoader()
        }
    }
}

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray
) {
    Divider(modifier = modifier, thickness = 1.dp, color = color)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ToolbarView(
    modifier: Modifier = Modifier,
    title: String,
    showBack: Boolean = true,
    backClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(showBack) {
            val keyboardController = LocalSoftwareKeyboardController.current
            IconButton(onClick = {
                keyboardController?.hide()
                backClicked()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_white),
                    contentDescription = null
                )
            }
        }
        Text(text = title, style = interSemiBold.titleLarge, modifier = Modifier.weight(1f))
    }
}