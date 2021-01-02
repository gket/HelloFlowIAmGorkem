package com.gk.helloflowiamgorkem.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.gk.helloflowiamgorkem.base.BaseViewModel
import com.gk.helloflowiamgorkem.repository.PhotoRepository
import com.gk.helloflowiamgorkem.utils.NetworkState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repository: PhotoRepository
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val viewState: StateFlow<HomeViewState> = _viewState

    var isPending: Boolean = false

    fun getRandomPhoto() {
        viewModelScope.launch {
            repository.getRandomPhoto().collect {
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
                _viewState.value = HomeViewState.ShuttleState(false)
                getRandomPhoto()
                delay(30000)
                _viewState.value = HomeViewState.ShuttleState(true)
            }
        }
    }



}