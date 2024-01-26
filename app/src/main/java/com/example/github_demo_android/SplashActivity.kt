package com.example.github_demo_android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.github_demo_android.ui.theme.backgroundColor
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }
    }

    @Preview
    @Composable
    fun SplashScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            var flag by remember { mutableIntStateOf(0) }
            LaunchedEffect(key1 = Unit, block = {
                flag = 1
                delay(1000)
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            })
            val size by animateDpAsState(
                targetValue = if (flag == 0) 50.dp else 100.dp,
                label = "",
                animationSpec = tween(
                    durationMillis = 1000
                )
            )
            AsyncImage(
                modifier = Modifier.size(size),
                model = R.drawable.ic_github,
                contentDescription = "Github Logo"
            )
        }
    }
}
