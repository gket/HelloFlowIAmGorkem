package com.gk.helloflowiamgorkem.di

import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.database.dao.FavoriteDao
import com.gk.helloflowiamgorkem.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRandomRepo(unsplashApiService: UnsplashApiService): RandomPhotoRepository {
        return RandomPhotoRepository(unsplashApiService)
    }

    @Provides
    fun providePhotosRepo(unsplashApiService: UnsplashApiService): PhotoLibraryRepository {
        return PhotoLibraryRepository(unsplashApiService)
    }

    @Provides
    fun providePhotoDetailRepo(favoriteDao: FavoriteDao): PhotoDetailRepository {
        return PhotoDetailRepository(favoriteDao)
    }

    @Provides
    fun providePhotoSearchRepo(unsplashApiService: UnsplashApiService): PhotoSearchRepository {
        return PhotoSearchRepository(unsplashApiService)
    }

    @Provides
    fun provideFavoriteRepo(favoriteDao: FavoriteDao): FavoriteRepository {
        return FavoriteRepository(favoriteDao)
    }
}