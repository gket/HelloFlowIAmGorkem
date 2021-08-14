package com.gk.helloflowiamgorkem.ui.photo_detail

import androidx.lifecycle.viewModelScope
import com.gk.helloflowiamgorkem.base.BaseViewModel
import com.gk.helloflowiamgorkem.data.Favorite
import com.gk.helloflowiamgorkem.repository.PhotoDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val repository: PhotoDetailRepository
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<PhotoDetailViewState>(PhotoDetailViewState.Loading)
    val viewState: StateFlow<PhotoDetailViewState> = _viewState

    fun addFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.isPhotoInFavList(favorite.id).take(1).collect {
                if (favoriteControl(it)) {
                    repository.removePhotoFromFavorite(it!!)
                } else {
                    repository.addPhotoToFavorite(favorite)
                }
            }
        }
    }

    fun checkIsFavorited(id: String) {
        viewModelScope.launch {
            repository.isPhotoInFavList(id).collect {
                _viewState.value = PhotoDetailViewState.IsFavorited(favoriteControl(it))
            }
        }
    }

    private fun favoriteControl(favorite: Favorite?): Boolean {
        return favorite != null
    }

}


