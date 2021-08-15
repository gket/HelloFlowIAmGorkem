package com.gk.helloflowiamgorkem.ui.favorite

import com.gk.helloflowiamgorkem.data.Favorite

sealed class FavoriteViewState {
    object Loading : FavoriteViewState()
    data class FavoritePhotos(val list: List<Favorite>) : FavoriteViewState()
    data class Error(val error: String?) : FavoriteViewState()
}