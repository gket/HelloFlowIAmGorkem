package com.gk.helloflowiamgorkem.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.repository.PhotoRepository
import com.gk.helloflowiamgorkem.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<Resource<UnsplashPhoto>>(Resource.Loading())
    val uiState: StateFlow<Resource<UnsplashPhoto>> = _uiState

    fun getRandomPhoto(delayInterval: Long) {
        viewModelScope.launch {
            repository.getRandomPhoto().collect {
                delay(delayInterval)
                _uiState.value = Resource.Loading()
                handlePhotoResponse(it)
            }
        }
    }

    fun handlePhotoResponse(response: Response<UnsplashPhoto>) {
        if (response.isSuccessful) {
            response.body().let {
                _uiState.value = Resource.Success(it)
            }
        } else {
            _uiState.value = Resource.Error(response.message().toString())
        }
    }

}