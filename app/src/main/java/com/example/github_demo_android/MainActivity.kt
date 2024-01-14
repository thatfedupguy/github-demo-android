package com.example.github_demo_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = CustomProgressDialog(this)
        window.statusBarColor = ContextCompat.getColor(this,R.color.black);
    }

    fun showLoader(){
        progressDialog.show()
        progressDialog.setCancelable(false)
    }

    fun hideLoader(){
        progressDialog.hide()
    }
}

