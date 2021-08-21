package com.gk.helloflowiamgorkem.repository

import app.cash.turbine.test
import com.gk.helloflowiamgorkem.api.UnsplashApiService
import com.gk.helloflowiamgorkem.data.PhotoUrl
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.data.User
import com.gk.helloflowiamgorkem.utils.NetworkState
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.time.ExperimentalTime

@ExperimentalTime
@RunWith(MockitoJUnitRunner::class)
class RandomPhotoRepositoryTest {

    lateinit var SUT: RandomPhotoRepository

    private val photo = UnsplashPhoto("", "", "", PhotoUrl("", "", "", "", ""), User("", ""))

    @Mock
    lateinit var apiService: UnsplashApiService

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Int>

    @Before
    fun setUp() {
        SUT = RandomPhotoRepository(apiService)
    }

    @Test
    fun whenEmptyShouldReturnEmptyList() = runBlocking {
        Mockito.`when`(apiService.getRandomPhotos(anyInt()))
            .thenReturn(Response.success(listOf()))

        val randomPhotos = SUT.getRandomPhoto()

        randomPhotos.test {
            val awaitItem = awaitItem()
            assert((awaitItem as NetworkState.Success).response.isEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenCallRandomPhotosCaptorShouldFive() = runBlocking {
        SUT.getRandomPhoto().test {
            Mockito.verify(apiService).getRandomPhotos(argumentCaptor.capture())
            assert(argumentCaptor.value == 5)
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun whenNullShouldReturnError() = runBlocking {
        Mockito.`when`(apiService.getRandomPhotos(anyInt())).thenReturn(Response.success(null))
        SUT.getRandomPhoto().test {
            val awaitItem = awaitItem()
            assert(awaitItem is NetworkState.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenFilledShouldReturnSuccess() = runBlocking {
        Mockito.`when`(apiService.getRandomPhotos(anyInt())).thenReturn(
            Response.success(
                listOf(photo)
            )
        )
        SUT.getRandomPhoto().test {
            val awaitItem = awaitItem()
            assert(awaitItem is NetworkState.Success)
            assert((awaitItem as NetworkState.Success).response.isNotEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }

}