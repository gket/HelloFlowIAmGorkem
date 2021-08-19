package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.data.Favorite
import com.gk.helloflowiamgorkem.database.dao.FavoriteDao

class PhotoDetailRepository(private val favoriteDao: FavoriteDao) : BaseRepository() {
    suspend fun addPhotoToFavorite(favorite: Favorite) {
        favoriteDao.addToFavorite(favorite)
    }

    fun isPhotoInFavList(id: String) = favoriteDao.isPhotoInFav(id)

    suspend fun removePhotoFromFavorite(favorite: Favorite) {
        favoriteDao.deleteFavorite(favorite)
    }
}