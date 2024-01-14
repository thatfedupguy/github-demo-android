package com.example.github_demo_android.di

import com.example.github_demo_android.BaseApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        ProfileFragmentModule::class,
        SearchUserFragmentModule::class,
        UserListFragmentModule::class,
        ProfileRepoModule::class,
        SearchUserRepoModule::class,
        UserListRepoModule::class
    ]
)
interface ApplicationComponent {
    fun inject(application: BaseApplication)
}