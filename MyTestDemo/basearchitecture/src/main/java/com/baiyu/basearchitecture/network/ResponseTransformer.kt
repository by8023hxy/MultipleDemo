package com.baiyu.basearchitecture.network

import retrofit2.Call
import retrofit2.Callback

/**
 * @author Baiyu
 * @date :2020/6/13 3:34 PM June
 * @version: 1.0
 */
/**
 * Asynchronously send the request and notify [ApiResponse] of its response or if an error
 * occurred talking to the server, creating the request, or processing the response.
 */
inline fun <T> Call<T>.request(
    crossinline onResult: (response: ApiResponse<T>) -> Unit
) {
    enqueue(getCallbackFromOnResult(onResult))
}



/** get a response callback from onResult unit. */
inline fun <T> getCallbackFromOnResult(
    crossinline onResult: (response: ApiResponse<T>) -> Unit
): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
            onResult(ApiResponse.of(SandwichInitializer.successCodeRange) { response })
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            onResult(ApiResponse.error(throwable))
        }
    }
}

/**
 * A scope function for handling success response [ApiResponse.Success] a unit
 * block of code within the context of the response.
 */
fun <T> ApiResponse<T>.onSuccess(onResult: ApiResponse.Success<T>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) {
        onResult(this)
    }
    return this
}

/**
 * A scope function for handling failure response [ApiResponse.Failure] a unit
 * block of code within the context of the response.
 */
fun <T> ApiResponse<T>.onFailure(onResult: ApiResponse.Failure<*>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Failure<*>) {
        onResult(this)
    }
    return this
}

/**
 * A scope function for handling error response [ApiResponse.Failure.Error] a unit
 * block of code within the context of the response.
 */
fun <T> ApiResponse<T>.onError(onResult: ApiResponse.Failure.Error<T>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Failure.Error) {
        onResult(this)
    }
    return this
}

/**
 * A scope function for handling exception response [ApiResponse.Failure.Exception] a unit
 * block of code within the context of the response.
 */
fun <T> ApiResponse<T>.onException(onResult: ApiResponse.Failure.Exception<T>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Failure.Exception) {
        onResult(this)
    }
    return this
}

/** Map [ApiResponse.Failure.Error] to a customized error response model. */
fun <T, V> ApiResponse.Failure.Error<T>.map(converter: ApiErrorModelMapper<V>): V {
    return converter.map(this)
}

/** Map [ApiResponse.Failure.Error] to a customized error response model. */
fun <T, V> ApiResponse.Failure.Error<T>.map(
    converter: ApiErrorModelMapper<V>,
    onResult: V.() -> Unit
) {
    onResult(converter.map(this))
}

/** A message from the [ApiResponse.Failure.Error]. */
fun <T> ApiResponse.Failure.Error<T>.message(): String = toString()

/** A message from the [ApiResponse.Failure.Exception]. */
fun <T> ApiResponse.Failure.Exception<T>.message(): String = toString()