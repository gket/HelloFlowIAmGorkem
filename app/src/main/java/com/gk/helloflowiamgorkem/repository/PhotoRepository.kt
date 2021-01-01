package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhotoRepository(private val unsplashApiService: UnsplashApiService) {
    fun getRandomPhoto(): Flow<Resource<List<UnsplashPhoto>>> =
        flow {
            emit(Resource.Loading())
            val response = unsplashApiService.getRandomPhotos(5)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
            } else {
                emit(Resource.Error(response.message()))
            }
        }
}