package com.example.github_demo_android.di

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.github_demo_android.Constants
import com.example.github_demo_android.CustomProgressDialog
import com.example.github_demo_android.MainActivity
import com.example.github_demo_android.MoshiBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class AppModule(
    private val application: Application
) {

    @Provides
    @Singleton
    fun providesContext(): Context = application

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor = interceptor).build()

    @Provides
    @Singleton
    fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(MoshiBuilder.getInstance()))
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()
}