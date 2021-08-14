package com.gk.helloflowiamgorkem.ui.favorite

import androidx.lifecycle.viewModelScope
import com.gk.helloflowiamgorkem.base.BaseViewModel
import com.gk.helloflowiamgorkem.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FavoriteRepository) :
    BaseViewModel() {

    private val _viewState = MutableStateFlow<FavoriteViewState>(FavoriteViewState.Loading)
    val viewState: StateFlow<FavoriteViewState> = _viewState

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            repository.getFavorites().collect {
                if (it.isNullOrEmpty()) {
                    _viewState.value = FavoriteViewState.Error("EMPTY_LIST")
                } else {
                    _viewState.value = FavoriteViewState.FavoritePhotos(it)
                }
            }
        }
    }

}