package com.example.github_demo_android

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import java.net.InetAddress

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

fun isInternetAvailable(): Boolean{
    try{
        val inet = InetAddress.getAllByName("www.google.com")
        return !inet.equals("")
    }
    catch (e: Exception){
        e.printStackTrace()
    }
    return false
}