package com.baiyu.basearchitecture.network

/**
 * @author Baiyu
 * @date :2020/6/13 3:33 PM June
 * @version: 1.0
 */
/** An interface for mapping [ApiResponse.Failure.Error] to custom error response model. */
interface ApiErrorModelMapper<V> {

    /** maps [ApiResponse.Failure.Error] to another model. */
    fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): V
}