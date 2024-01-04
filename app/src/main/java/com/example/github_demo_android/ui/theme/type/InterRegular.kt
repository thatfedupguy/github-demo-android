package com.example.github_demo_android.ui.theme.type

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.github_demo_android.ui.theme.inter
import com.example.github_demo_android.ui.theme.secondaryColor

val interRegular = Typography(
    headlineLarge = TextStyle(
        fontSize = 24.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    headlineMedium = TextStyle(
        fontSize = 20.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    headlineSmall = TextStyle(
        fontSize = 18.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    titleLarge = TextStyle(
        fontSize = 18.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    labelLarge = TextStyle(
        fontSize = 16.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp, fontFamily = inter, color = secondaryColor,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
)