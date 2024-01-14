package com.example.github_demo_android.di

import com.example.github_demo_android.fragments.SearchFragment
import com.example.github_demo_android.repo.SearchRepo
import com.example.github_demo_android.repo.repoImpl.SearchRepoImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchUserRepoModule {
    @Binds
    abstract fun provideSearchRepo(searchRepoImpl: SearchRepoImpl): SearchRepo
}

@Module
abstract class SearchUserFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesSearchUserFragmentInjector() : SearchFragment
}