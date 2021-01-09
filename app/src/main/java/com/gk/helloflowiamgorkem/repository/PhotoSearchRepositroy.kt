package com.gk.helloflowiamgorkem.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.source.UnsplashPagingSource
import kotlinx.coroutines.flow.Flow

class PhotoSearchRepository(private val unsplashApiService: UnsplashApiService) : BaseRepository() {
    fun getSearchResult(isSearch: Boolean, query: String?): Flow<PagingData<UnsplashPhoto>> {
        return (Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false,
            ), pagingSourceFactory = { UnsplashPagingSource(unsplashApiService, isSearch, query) }
        ).flow)
    }
}