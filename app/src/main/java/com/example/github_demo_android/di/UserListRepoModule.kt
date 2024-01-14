package com.example.github_demo_android.di

import com.example.github_demo_android.fragments.UserListFragment
import com.example.github_demo_android.repo.UserListRepo
import com.example.github_demo_android.repo.repoImpl.UserListRepoImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserListRepoModule {
    @Binds
    abstract fun provideUserListRepo(userListRepoImpl: UserListRepoImpl): UserListRepo
}

@Module
abstract class UserListFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesUserFragmentInjector(): UserListFragment
}