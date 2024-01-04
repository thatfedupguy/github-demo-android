package com.example.github_demo_android.di

import com.example.github_demo_android.repo.ProfileRepo
import com.example.github_demo_android.repo.repoImpl.ProfileRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProfileRepoModule {
    @Binds
    abstract fun provideProfileRepo(profileRepoImpl: ProfileRepoImpl): ProfileRepo
}