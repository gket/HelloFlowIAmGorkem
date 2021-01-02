package com.gk.helloflowiamgorkem.ui.home

import com.gk.helloflowiamgorkem.data.UnsplashPhoto

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class UnSplashPhotos(val list: List<UnsplashPhoto>) : HomeViewState()
    data class Error(val error: String?) : HomeViewState()
    data class ShuttleState(val isEnable: Boolean) : HomeViewState()
    data class ShowToast(val message: String) : HomeViewState()
    object ClearToast : HomeViewState()
}