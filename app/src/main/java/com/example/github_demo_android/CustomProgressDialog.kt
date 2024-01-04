package com.example.github_demo_android

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.github_demo_android.databinding.LayoutCustomProgressBinding
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CustomProgressDialog @Inject constructor(
    @ActivityContext context: Context
): Dialog(context, R.style.Custom_Progress_Dialog) {

    private lateinit var binding: LayoutCustomProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutCustomProgressBinding.inflate(layoutInflater)
        window!!.requestFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
    }
}