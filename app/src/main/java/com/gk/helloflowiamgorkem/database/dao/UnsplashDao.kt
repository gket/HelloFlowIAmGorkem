package com.gk.helloflowiamgorkem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gk.helloflowiamgorkem.data.UnsplashPhoto

@Dao
interface UnsplashDao {

    @Query("SELECT * FROM unsplashphoto")
    suspend fun getFavorites(): List<UnsplashPhoto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhoto(photo: UnsplashPhoto)


}