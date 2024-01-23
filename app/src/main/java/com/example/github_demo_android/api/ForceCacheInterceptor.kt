package com.example.github_demo_android.api

import android.app.Application
import android.content.Context
import com.example.github_demo_android.isInternetAvailable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class ForceCacheInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if(isInternetAvailable()){
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(builder.build())
    }
}

class CacheInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()
        return response.newBuilder().header("Cache-Control", cacheControl.toString()).build()
    }

}