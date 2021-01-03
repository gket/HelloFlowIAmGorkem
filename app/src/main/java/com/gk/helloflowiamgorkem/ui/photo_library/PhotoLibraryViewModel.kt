package com.gk.helloflowiamgorkem.ui.photo_library

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gk.helloflowiamgorkem.base.BaseViewModel
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.repository.PhotoLibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PhotoLibraryViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repositoryPhotos: PhotoLibraryRepository
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<PagingData<UnsplashPhoto>>(PagingData.empty())
    val viewState: StateFlow<PagingData<UnsplashPhoto>> = _viewState

    fun getPhotos()  {
        viewModelScope.launch {
            repositoryPhotos.getPhotos().collect {
                Log.d("GETPHOTOS:::", "I am collected")
                _viewState.value = it
            }
        }

    }

}