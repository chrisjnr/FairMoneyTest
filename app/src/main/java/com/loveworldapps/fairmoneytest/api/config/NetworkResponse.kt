package com.loveworldapps.fairmoneytest.api.config

import com.loveworldapps.fairmoneytest.api.BaseResponse

/**
 * Created by manuelchris-ogar on 20/01/2021.
 */

public open class NetworkResponse <out T : Any, out U : Any>: BaseResponse() {
    /**
     * Success response with body
     */
    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    /**
     * Failure response with body
     */
    data class ApiError<U : Any>(val body: String, val code: String) : NetworkResponse<Nothing, U>()

    /**
     * No Intenet
     */
    object  NoInternet : NetworkResponse<Nothing, Nothing>()


    /**
     * Network error
     */
    data class NetworkError(val error: Exception) : NetworkResponse<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()


    /**
     * User's token is invalid, show error
     */
    object TokenInvalid : NetworkResponse<Nothing, Nothing>()
}