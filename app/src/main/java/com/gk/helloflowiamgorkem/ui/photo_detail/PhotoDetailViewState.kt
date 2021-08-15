package com.gk.helloflowiamgorkem.ui.photo_detail

sealed class PhotoDetailViewState {
    object Loading : PhotoDetailViewState()
    data class Error(val error: String?) : PhotoDetailViewState()
    data class IsFavorited(val isFavorited: Boolean) : PhotoDetailViewState()
}