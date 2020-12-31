package com.gk.helloflowiamgorkem.di

import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepository(unsplashApiService: UnsplashApiService): PhotoRepository {
        return PhotoRepository(unsplashApiService)
    }
}