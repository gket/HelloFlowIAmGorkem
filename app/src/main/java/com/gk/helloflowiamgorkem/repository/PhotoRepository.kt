package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.ui.home.HomeUiDisplayer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhotoRepository(private val unsplashApiService: UnsplashApiService) {
    fun getRandomPhoto(): Flow<HomeUiDisplayer<List<UnsplashPhoto>>> =
        flow {
            emit(HomeUiDisplayer.Loading())
            val response = unsplashApiService.getRandomPhotos(5)
            if (response.isSuccessful) {
                emit(HomeUiDisplayer.Success(response.body()))
            } else {
                emit(HomeUiDisplayer.Error(response.message()))
            }
        }
}