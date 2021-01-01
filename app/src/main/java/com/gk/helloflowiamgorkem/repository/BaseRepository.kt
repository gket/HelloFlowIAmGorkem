package com.gk.helloflowiamgorkem.repository

import com.gk.helloflowiamgorkem.utils.NetworkState
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseRepository {

    protected suspend fun <T : Any> apiCall(call: suspend () -> T): NetworkState<T> {
        return try {
            val response = call()
            NetworkState.Success(response)
        } catch (ex: Throwable) {
            NetworkState.Error(ex)
        }
    }

    protected suspend fun <T : Any> apiCallResponse(call: suspend () -> Response<T>): NetworkState<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    NetworkState.Success(body)
                else
                    NetworkState.Error(code = response.code(), message = response.message())
            } else {
                NetworkState.Error(code = response.code(), message = response.message())
            }
        } catch (ex: Throwable) {
            NetworkState.Error(error = ex, message = ex.getErrorMessage())
        }
    }

}