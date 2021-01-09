package com.gk.helloflowiamgorkem.ui.photo_detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.gk.helloflowiamgorkem.base.BaseViewModel

class PhotoDetailViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    fun manageFavorite(url : String){
        
    }

}