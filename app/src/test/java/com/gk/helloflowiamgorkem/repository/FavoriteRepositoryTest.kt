package com.gk.helloflowiamgorkem.repository

import app.cash.turbine.test
import com.gk.helloflowiamgorkem.data.Favorite
import com.gk.helloflowiamgorkem.database.dao.FavoriteDao
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime

@ExperimentalTime
@RunWith(MockitoJUnitRunner::class)
class FavoriteRepositoryTest {

    lateinit var SUT: FavoriteRepository

    @Mock
    lateinit var dao: FavoriteDao

    var favorite = Favorite("256", "", "barandroid")

    @Before
    fun setUp() {
        SUT = FavoriteRepository(dao)
    }

    @Test
    fun whenFavoritesNullRepositoryShouldReturnNull() = runBlocking {
        `when`(dao.getFavorites()).thenReturn(flow { emit(null) })
        SUT.getFavorites().test {
            assertNull(awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun whenFavoritesIncludeItemRepositoryShouldReturnItem() = runBlocking {
        `when`(dao.getFavorites()).thenReturn(flow { emit(listOf(favorite)) })
        SUT.getFavorites().test {
            assertEquals(awaitItem()?.first(), favorite)
            awaitComplete()
        }
    }

    @Test
    fun whenFavoriteThrowsExceptionRepositoryThrowsException() = runBlocking {
        val except = RuntimeException("boom")
        `when`(dao.getFavorites()).thenThrow(except)
        try {
            SUT.getFavorites()
            assert(false)
        } catch (exception: Exception) {
            assertEquals(exception, except)
        }
    }

}