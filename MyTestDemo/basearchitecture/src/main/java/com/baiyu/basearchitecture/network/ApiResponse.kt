package com.baiyu.basearchitecture.network

import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * @author Baiyu
 * @date :2020/6/13 3:30 PM June
 * @version: 1.0
 */
sealed class ApiResponse<out T> {

    /**
     * API Success response class from retrofit.
     *
     * [data] is optional. (There are responses without data)
     */
    class Success<T>(val response: Response<T>) : ApiResponse<T>() {
        val statusCode: StatusCode = getStatusCodeFromResponse(response)
        val headers: Headers = response.headers()
        val raw: okhttp3.Response = response.raw()
        val data: T? = response.body()
        override fun toString() = "[ApiResponse.Success](data=$data)"
    }

    /**
     * API Failure response class.
     *
     * ## API format error case.
     * API communication conventions do not match or applications need to handle errors.
     * e.g. internal server error.
     *
     * ## API Exception error case.
     * Gets called when an unexpected exception occurs while creating the request or processing the response in client.
     * e.g. network connection error.
     */
    sealed class Failure<T> {
        class Error<T>(val response: Response<T>) : ApiResponse<T>() {
            val statusCode: StatusCode = getStatusCodeFromResponse(response)
            val headers: Headers = response.headers()
            val raw: okhttp3.Response = response.raw()
            val errorBody: ResponseBody? = response.errorBody()
            override fun toString(): String =
                "[ApiResponse.Failure.Error-$statusCode](errorResponse=$response)"
        }

        class Exception<T>(val exception: Throwable) : ApiResponse<T>() {
            val message: String? = exception.localizedMessage
            override fun toString(): String = "[ApiResponse.Failure.Exception](message=$message)"
        }
    }

    companion object {
        /**
         * ApiResponse error Factory.
         *
         * [Failure] factory function. Only receives [Throwable] as an argument.
         */
        fun <T> error(ex: Throwable) = Failure.Exception<T>(ex)

        /**
         * ApiResponse Factory.
         *
         * [f] Create [ApiResponse] from [retrofit2.Response] returning from the block.
         * If [retrofit2.Response] has no errors, it creates [ApiResponse.Success].
         * If [retrofit2.Response] has errors, it creates [ApiResponse.Failure.Error].
         * If [retrofit2.Response] has occurred exceptions, it creates [ApiResponse.Failure.Exception].
         */
        fun <T> of(successCodeRange: IntRange, f: () -> Response<T>): ApiResponse<T> = try {
            val response = f()
            if (response.raw().code in successCodeRange) {
                Success(response)
            } else {
                Failure.Error(response)
            }
        } catch (ex: Exception) {
            Failure.Exception(ex)
        }

        /** returns a status code from the [Response]. */
        fun <T> getStatusCodeFromResponse(response: Response<T>): StatusCode {
            return StatusCode.values().find { it.code == response.code() }
                ?: StatusCode.Unknown
        }
    }
}