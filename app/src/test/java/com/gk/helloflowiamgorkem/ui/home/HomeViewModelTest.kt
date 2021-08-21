package com.gk.helloflowiamgorkem.ui.home

import app.cash.turbine.test
import com.gk.helloflowiamgorkem.data.PhotoUrl
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.data.User
import com.gk.helloflowiamgorkem.repository.RandomPhotoRepository
import com.gk.helloflowiamgorkem.utils.MainCoroutineRule
import com.gk.helloflowiamgorkem.utils.NetworkState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime


@ExperimentalCoroutinesApi
@ExperimentalTime
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var SUT: HomeViewModel

    @Mock
    lateinit var repository: RandomPhotoRepository

    private val photo = UnsplashPhoto("", "", "", PhotoUrl("", "", "", "", ""), User("", ""))

    @Before
    fun before() {
        SUT = HomeViewModel(repository)
    }

    @Test
    fun whenRandomPhotoSuccess() = runBlockingTest {
        Mockito.`when`(repository.getRandomPhoto())
            .thenReturn(
                flow {
                    emit(NetworkState.Loading)
                    emit(NetworkState.Success(listOf(photo)))
                })

        SUT.viewState.test {
            SUT.getRandomPhoto()
            val item = awaitItem()
            assert(item is HomeViewState.Loading)
            val secondItem = awaitItem()
            assert(secondItem is HomeViewState.UnSplashPhotos)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenRandomPhotoFail() = runBlockingTest {
        Mockito.`when`(repository.getRandomPhoto())
            .thenReturn(
                flow {
                    emit(NetworkState.Loading)
                    emit(NetworkState.Error(null, 0, ""))
                })

        SUT.viewState.test {
            SUT.getRandomPhoto()
            assert(awaitItem() is HomeViewState.Loading)
            assert(awaitItem() is HomeViewState.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }

}