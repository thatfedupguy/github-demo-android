package com.example.github_demo_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.github_demo_android.di.IoDispatcher
import com.example.github_demo_android.di.MainDispatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher


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

