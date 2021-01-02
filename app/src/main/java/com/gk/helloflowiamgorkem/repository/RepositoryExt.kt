package com.gk.helloflowiamgorkem.repository

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

// todo: string ifadelerine ne yazılmak isteniyorsa artık yazılır ileride :)
fun Throwable.getErrorMessage(): String {
    return when (this) {
        is HttpException -> "error_unknown"
        is SocketTimeoutException -> "error_connection_timeout"
        is IOException -> "error_connection_not_found"
        is UnknownHostException -> "unknown_host_exception"
        else -> "error_unknown"
    }
}