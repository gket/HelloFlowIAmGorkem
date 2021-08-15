package com.gk.helloflowiamgorkem.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gk.helloflowiamgorkem.base.BaseViewModel
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.repository.PhotoSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositorySearch: PhotoSearchRepository
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<PagingData<UnsplashPhoto>>(PagingData.empty())
    val viewState: StateFlow<PagingData<UnsplashPhoto>> = _viewState

    fun getSearchedPhotos(query: String?) {
        viewModelScope.launch {
            repositorySearch.getSearchResult(true, query).cachedIn(viewModelScope).collect {
                _viewState.value = it
            }
        }
    }

}