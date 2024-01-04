package com.example.github_demo_android.di

import com.example.github_demo_android.repo.SearchRepo
import com.example.github_demo_android.repo.repoImpl.SearchRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchUserRepoModule {
    @Binds
    abstract fun provideSearchRepo(searchRepoImpl: SearchRepoImpl): SearchRepo
}