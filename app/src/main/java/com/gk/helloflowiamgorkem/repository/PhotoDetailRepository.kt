package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.data.Favorite
import com.gk.helloflowiamgorkem.database.dao.FavoriteDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PhotoDetailRepository(private val favoriteDao: FavoriteDao) : BaseRepository() {
    suspend fun addPhotoToFavorite(favorite: Favorite) {
        favoriteDao.addToFavorite(favorite)
    }

    suspend fun isPhotoInFavList(id: String) = flow {
        favoriteDao.isPhotoInFav(id).collect {
            emit(it)
        }
    }


    suspend fun removePhotoFromFavorite(favorite: Favorite) {
        favoriteDao.deleteFavorite(favorite)
    }
}