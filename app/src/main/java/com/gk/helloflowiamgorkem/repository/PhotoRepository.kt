package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class PhotoRepository(private val unsplashApiService: UnsplashApiService) {
    suspend fun getRandomPhoto(): Flow<Response<UnsplashPhoto>> =
        flow {
            emit(unsplashApiService.getRandomPhotos())
        }
}