package com.example.kinopoiskpopularmovies.data.remote

import com.example.kinopoiskpopularmovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("X-API-Key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}