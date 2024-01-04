package com.example.github_demo_android.di

import com.example.github_demo_android.repo.UserListRepo
import com.example.github_demo_android.repo.repoImpl.UserListRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserListRepoModule {
    @Binds
    abstract fun provideUserListRepo(userListRepoImpl: UserListRepoImpl): UserListRepo
}