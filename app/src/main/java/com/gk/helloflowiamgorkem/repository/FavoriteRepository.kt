package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.database.dao.FavoriteDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class FavoriteRepository(private val dao: FavoriteDao) : BaseRepository() {

    suspend fun getFavorites() = flow {
        dao.getFavorites().collect {
            emit(it)
        }
    }

}