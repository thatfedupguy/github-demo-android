package com.example.github_demo_android

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun String?.orDefault(): String{
    return this ?: "-"
}

fun Int?.orDefault(): Int{
    return this ?: 0
}

fun Fragment.navigateForward(directions: NavDirections) = view?.post {
    if(isAdded){
        findNavController().navigate(directions)
    }
}

fun Fragment.navigateBack() = view?.post {
    if(isAdded){
        findNavController().popBackStack()
    }
}

fun Context.showToast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}