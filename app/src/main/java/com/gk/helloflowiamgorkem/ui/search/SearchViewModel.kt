package com.gk.helloflowiamgorkem.ui.search

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gk.helloflowiamgorkem.base.BaseViewModel
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.repository.PhotoSearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repositorySearch: PhotoSearchRepository
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<PagingData<UnsplashPhoto>>(PagingData.empty())
    val viewState: StateFlow<PagingData<UnsplashPhoto>> = _viewState

    fun getFavoritePhotos(query: String?) {
        viewModelScope.launch {
            repositorySearch.getSearchResult(true, query).collect {
                Log.d("GETPHOTOS:::", "I am collected")
                _viewState.value = it
            }
        }

    }

}