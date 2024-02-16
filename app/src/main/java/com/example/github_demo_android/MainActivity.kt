package com.example.github_demo_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.github_demo_android.di.IoDispatcher
import com.example.github_demo_android.di.MainDispatcher
import com.example.github_demo_android.network.ConnectivityObserver
import com.example.github_demo_android.network.NetworkConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var progressDialog: CustomProgressDialog

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val networkConnectivityObserver = NetworkConnectivityObserver(applicationContext)
        networkConnectivityObserver.networkStatus().onEach {
            if (it != ConnectivityObserver.Status.Available) {
                throw Exception("No network Found")
            }
        }.launchIn(lifecycleScope)
    }

    fun showLoader() {
        progressDialog.run {
            show()
            setCancelable(false)
        }
    }

    fun hideLoader() {
        progressDialog.hide()
    }
}

