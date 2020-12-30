package com.gk.helloflowiamgorkem.api

import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import retrofit2.Response
import retrofit2.http.GET

interface UnsplashApiService {
    @GET("/photos/random")
    suspend fun getRandomPhotos() : Response<UnsplashPhoto>
}