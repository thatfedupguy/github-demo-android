package com.example.github_demo_android.di

import com.example.github_demo_android.CustomProgressDialog
import com.example.github_demo_android.MainActivity
import com.example.github_demo_android.fragments.ProfileFragment
import com.example.github_demo_android.repo.ProfileRepo
import com.example.github_demo_android.repo.repoImpl.ProfileRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ProfileRepoModule {
    @Binds
    abstract fun provideProfileRepo(profileRepoImpl: ProfileRepoImpl): ProfileRepo
}

@Module
abstract class ProfileFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesProfileFragmentInjector(): ProfileFragment
}

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivityInjector(): MainActivity

}