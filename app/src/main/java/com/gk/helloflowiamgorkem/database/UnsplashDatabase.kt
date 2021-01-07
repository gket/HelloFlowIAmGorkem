package com.gk.helloflowiamgorkem.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.database.dao.UnsplashDao

@Database(entities = [UnsplashPhoto::class], version = 1, exportSchema = false)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun getUnsplashDao(): UnsplashDao
}