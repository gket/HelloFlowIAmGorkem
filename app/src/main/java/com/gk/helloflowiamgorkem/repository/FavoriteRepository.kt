package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.database.dao.FavoriteDao

class FavoriteRepository(private val dao: FavoriteDao) : BaseRepository() {

    fun getFavorites() = dao.getFavorites()

}