package com.gk.helloflowiamgorkem.ui.photo_library

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gk.helloflowiamgorkem.base.BaseViewModel
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.repository.PhotoLibraryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoLibraryViewModel @Inject constructor(
    private val repositoryPhotos: PhotoLibraryRepository
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<PagingData<UnsplashPhoto>>(PagingData.empty())
    val viewState: StateFlow<PagingData<UnsplashPhoto>> = _viewState

    fun getPhotos() {
        viewModelScope.launch {
            repositoryPhotos.getPhotos(false, "").cachedIn(viewModelScope).collect {
                Log.d("GETPHOTOS:::", "I am collected")
                _viewState.value = it
            }
        }

    }

}