package com.gk.helloflowiamgorkem.ui.home

sealed class HomeUiDisplayer<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : HomeUiDisplayer<T>(data)
    class Error<T>(message: String?, data: T? = null) : HomeUiDisplayer<T>(data, message)
    class Loading<T> : HomeUiDisplayer<T>()
    class ShuffleClicking<T> : HomeUiDisplayer<T>()
}

