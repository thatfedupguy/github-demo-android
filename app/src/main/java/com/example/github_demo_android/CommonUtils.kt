package com.example.github_demo_android

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController


fun String?.orDefault(): String {
    return this ?: "-"
}

fun Int?.orDefault(): Int {
    return this ?: 0
}

fun Fragment.navigateForward(directions: NavDirections) = view?.post {
    if (isAdded) {
        val navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(R.anim.slide_in).setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in).setPopExitAnim(R.anim.slide_out)
        findNavController().navigate(directions, navOptions = navBuilder.build())
    }
}

fun Fragment.navigateBack() = view?.post {
    if (isAdded) {
        findNavController().popBackStack()
    }
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

@Composable
fun Modifier.basicClickable(
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    onClick: () -> Unit
) = this.clickable(
    interactionSource = interactionSource,
    indication = null,
    onClick = { onClick() }
)