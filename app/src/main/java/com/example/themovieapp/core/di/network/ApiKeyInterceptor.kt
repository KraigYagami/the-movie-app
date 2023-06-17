package com.example.themovieapp.core.di.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    // TODO: Move this to a secure place
    @Suppress("MaxLineLength")
    private val apiKey =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzOTI1MmM0OGE5MTIxZTAwYmRlZThiN2M3MWM2MjBhZSIsInN1YiI6IjYwNTY4N2I0NDA4M2IzMDA1NjQ3ZmFlOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XfBSWCRepz00xzHGrFPRhuYn7gU1ofMtuDGHMteFlcU" // ktlint-disable max-line-length

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().apply {
                addHeader("accept", "application/json")
                addHeader("Authorization", "Bearer $")
            }.build()
        )
    }
}
