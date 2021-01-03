package com.gk.helloflowiamgorkem.api

import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = "5eCoz9p-h1QfniqJYWTApNzTlOV_DlJ9BWfsZ3o4rLQ"
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Client-ID $apiKey")
        return chain.proceed(requestBuilder.build())
    }
}