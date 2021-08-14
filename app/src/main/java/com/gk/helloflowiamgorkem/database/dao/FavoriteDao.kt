package com.gk.helloflowiamgorkem.database.dao

import androidx.room.*
import com.gk.helloflowiamgorkem.data.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<Favorite>?>

    @Query("SELECT * FROM favorites WHERE id = :photoId")
    fun isPhotoInFav(photoId: String): Flow<Favorite?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)


}