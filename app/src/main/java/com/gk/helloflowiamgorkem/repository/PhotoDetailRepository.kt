package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.database.dao.UnsplashDao

class PhotoDetailRepository(private val unsplashDao: UnsplashDao) : BaseRepository() {
    suspend fun addPhotoToFavorite(unsplashPhoto: UnsplashPhoto) {
        unsplashDao.addPhoto(unsplashPhoto)
    }
}