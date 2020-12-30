package com.gk.helloflowiamgorkem.api

import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = "v8GI-i7eZcVi5JvZthXGl885VLvICu2tls6NOtf2-gM"
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", apiKey)
        return chain.proceed(requestBuilder.build())
    }
}