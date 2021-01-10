package com.gk.helloflowiamgorkem.api

import com.gk.helloflowiamgorkem.data.SearchResponse
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("/photos/random")
    suspend fun getRandomPhotos(@Query("count") count: Int): Response<List<UnsplashPhoto>>

    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<UnsplashPhoto>>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchResponse>
}