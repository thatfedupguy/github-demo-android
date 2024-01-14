package com.example.github_demo_android

import android.app.Application
import com.example.github_demo_android.di.AppModule
import com.example.github_demo_android.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
            .builder()
            .appModule(AppModule(this))
            .build().inject(this)
    }
    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }
}