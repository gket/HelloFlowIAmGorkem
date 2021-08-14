package com.gk.helloflowiamgorkem.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gk.helloflowiamgorkem.data.Favorite
import com.gk.helloflowiamgorkem.database.dao.FavoriteDao


@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteDao
}
