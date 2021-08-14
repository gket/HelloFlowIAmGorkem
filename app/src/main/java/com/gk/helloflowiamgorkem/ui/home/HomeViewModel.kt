package com.gk.helloflowiamgorkem.ui.home

import androidx.lifecycle.viewModelScope
import com.gk.helloflowiamgorkem.base.BaseViewModel
import com.gk.helloflowiamgorkem.repository.RandomPhotoRepository
import com.gk.helloflowiamgorkem.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryRandom: RandomPhotoRepository
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val viewState: StateFlow<HomeViewState> = _viewState

    var isPending: Boolean = false

    fun getRandomPhoto() {
        viewModelScope.launch {
            repositoryRandom.getRandomPhoto().collect {
                when (it) {
                    is NetworkState.Success -> {
                        _viewState.value = HomeViewState.UnSplashPhotos(it.response)
                    }
                    is NetworkState.Error -> {
                        _viewState.value = HomeViewState.Error(it.message)
                    }
                    is NetworkState.Loading -> {
                        _viewState.value = HomeViewState.Loading
                    }
                }
            }
        }
    }

    fun shuffle() {
        viewModelScope.launch {
            if (isPending) {
                _viewState.value = HomeViewState.ShowToast("Please Wait for new Shuffle")
                delay(2000)
                _viewState.value = HomeViewState.ClearToast
            } else {
                _viewState.value = HomeViewState.ShuffleState(false)
                getRandomPhoto()
                delay(30000)
                _viewState.value = HomeViewState.ShuffleState(true)
            }
        }
    }



}