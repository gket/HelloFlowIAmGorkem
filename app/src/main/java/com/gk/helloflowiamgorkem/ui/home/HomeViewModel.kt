package com.gk.helloflowiamgorkem.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.repository.PhotoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiDisplayer<List<UnsplashPhoto>>>(HomeUiDisplayer.Loading())
    val uiState: StateFlow<HomeUiDisplayer<List<UnsplashPhoto>>> = _uiState
    private val _action =
        MutableStateFlow<HomeActionLayer>(HomeActionLayer.ShuffleClicked)
    private val actionState: StateFlow<HomeActionLayer> = _action

    init {
        getRandomPhoto()
    }

    private fun getRandomPhoto() {
        viewModelScope.launch {
            repository.getRandomPhoto().collect {
                _uiState.value = it
            }
        }
    }

    fun shuffle() {
        _uiState.value = HomeUiDisplayer.ShuffleClicking()
        getRandomPhoto()
    }

    // Will try for another process. It is unnecessary for the click
    private fun shuffleProcess(): Flow<HomeActionLayer> = flow {
        emit(HomeActionLayer.ShuffleClicked)
    }


}