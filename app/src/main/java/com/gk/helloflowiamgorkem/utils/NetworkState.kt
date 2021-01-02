package com.gk.helloflowiamgorkem.utils

sealed class NetworkState<out T> {
    class Success<out T>(val response: T) : NetworkState<T>()
    class Error(
        val error: Throwable? = null,
        val code: Int? = null,
        val message: String? = null
    ) : NetworkState<Nothing>()

    object Loading : NetworkState<Nothing>()
}