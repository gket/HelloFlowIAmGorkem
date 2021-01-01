package com.gk.helloflowiamgorkem.api

import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("/photos/random")
    suspend fun getRandomPhotos(@Query("count") count : Int) : Response<List<UnsplashPhoto>>
}