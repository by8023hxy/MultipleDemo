package com.baiyu.basearchitecture.event


sealed class RequestState<out T> {
    object Loading : RequestState<Nothing>()
    data class Success<out T>(val data: T?) : RequestState<T>()
    data class Error(val code: String? = null, val error: Exception? = null) :
        RequestState<Nothing>()
}

