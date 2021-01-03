package com.gk.helloflowiamgorkem.source

import android.util.Log
import androidx.paging.PagingSource
import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_PHOTOS_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val service: UnsplashApiService
) : PagingSource<Int, UnsplashPhoto>() {
    
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: UNSPLASH_PHOTOS_STARTING_PAGE_INDEX

        return try {
            val response = service.getPhotos(position, params.loadSize)
            val data = response.body() ?: emptyList()
            LoadResult.Page(
                data = data,
                prevKey = if (position == UNSPLASH_PHOTOS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}