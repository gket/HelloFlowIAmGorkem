package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.utils.NetworkState
import com.gk.helloflowiamgorkem.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhotoRepository(private val unSplashApiService: UnsplashApiService) : BaseRepository() {

    fun getRandomPhoto(): Flow<NetworkState<List<UnsplashPhoto>>> = flow {
        emit(apiCallResponse { unSplashApiService.getRandomPhotos(5) })
    }
}