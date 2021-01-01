package com.gk.helloflowiamgorkem.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.repository.PhotoRepository
import com.gk.helloflowiamgorkem.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<Resource<List<UnsplashPhoto>>>(Resource.Loading())
    val uiState: StateFlow<Resource<List<UnsplashPhoto>>> = _uiState

    fun getRandomPhoto() {
        viewModelScope.launch {
            repository.getRandomPhoto().collect {
               _uiState.value = it
            }
        }
    }

}