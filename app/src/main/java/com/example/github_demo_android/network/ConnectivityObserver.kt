package com.example.github_demo_android.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun networkStatus(): Flow<Status>

    enum class Status {
        Available, Unavailable, Lost, Losing
    }
}