package com.gk.helloflowiamgorkem.di

import android.content.Context
import androidx.room.Room
import com.gk.helloflowiamgorkem.R
import com.gk.helloflowiamgorkem.database.UnsplashDatabase
import com.gk.helloflowiamgorkem.database.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): UnsplashDatabase {
        return Room.databaseBuilder(
            appContext,
            UnsplashDatabase::class.java,
            appContext.getString(R.string.app_name)
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: UnsplashDatabase): FavoriteDao {
        return database.getFavoriteDao()
    }
}