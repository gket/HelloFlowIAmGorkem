package com.gk.helloflowiamgorkem.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.gk.helloflowiamgorkem.base.BaseViewModel
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
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

    sealed class ViewState {
        object Loading : ViewState()
        data class UnSplashPhotos(val list: List<UnsplashPhoto>) : ViewState()
        data class Error(val error: String?) : ViewState()
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    fun getRandomPhoto() {
        viewModelScope.launch {
            repository.getRandomPhoto().collect {
                when (it) {
                    is NetworkState.Success -> {
                        // loading görmen için 2 sn bekletiyorum :)
                        delay(2000)
                        _viewState.value = ViewState.UnSplashPhotos(it.response)
                    }
                    is NetworkState.Error -> {
                        _viewState.value = ViewState.Error(it.message)
                    }
                    is NetworkState.Loading -> {
                        _viewState.value = ViewState.Loading
                    }
                }
            }
        }
    }

}