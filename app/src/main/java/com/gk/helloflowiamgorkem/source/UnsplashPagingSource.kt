package com.gk.helloflowiamgorkem.source

import android.util.Log
import androidx.paging.PagingSource
import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private const val UNSPLASH_PHOTOS_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val service: UnsplashApiService,
    private val isSearchProcess: Boolean,
    private val query : String?
) : PagingSource<Int, UnsplashPhoto>() {

    lateinit var response : Response<List<UnsplashPhoto>>
    lateinit var data : List<UnsplashPhoto>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: UNSPLASH_PHOTOS_STARTING_PAGE_INDEX

        return try {
            if(isSearchProcess){
                response = service.searchPhotos(query ?: "", position, params.loadSize)
                Log.d("RESPONSEEE:::", response.message())
                data = response.body() ?: emptyList()
            }

            else{
                response = service.getPhotos(position, params.loadSize)
                data = response.body() ?: emptyList()
            }

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