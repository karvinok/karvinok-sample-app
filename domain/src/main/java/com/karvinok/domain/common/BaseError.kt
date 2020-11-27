package com.karvinok.domain.common

sealed class BaseError(message: String? = null) : Exception(message) {
    class NetworkError(val code: Int) : BaseError()
    class ServerError(val code: Int, message: String? = null) : BaseError(message)
    class OtherError(val e: String) : BaseError(message = e)
}

