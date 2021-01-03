package com.gk.helloflowiamgorkem.ui.photo_library

import com.gk.helloflowiamgorkem.data.UnsplashPhoto

sealed class PhotoLibraryViewState {
    object Loading : PhotoLibraryViewState()
    data class PhotoResult(val list: List<UnsplashPhoto>) : PhotoLibraryViewState()
    data class Error(val error: String?) : PhotoLibraryViewState()
}