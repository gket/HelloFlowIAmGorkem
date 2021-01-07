package com.gk.helloflowiamgorkem.di

import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.database.dao.UnsplashDao
import com.gk.helloflowiamgorkem.repository.PhotoDetailRepository
import com.gk.helloflowiamgorkem.repository.PhotoLibraryRepository
import com.gk.helloflowiamgorkem.repository.RandomPhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
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
    fun providePhotoDetailRep(unsplashDao: UnsplashDao): PhotoDetailRepository {
        return PhotoDetailRepository((unsplashDao))
    }
}