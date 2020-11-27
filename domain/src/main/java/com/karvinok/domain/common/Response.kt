package com.karvinok.domain.common

inline fun <R> proceedResponse(requestBlock: () -> Either.Success<R>): Either<BaseError, R> {
    return try {
        requestBlock.invoke()
    } catch (e: Exception) {
        e.printStackTrace()
        when (e) {
            is BaseError -> Either.Failure(e)
            is BaseError.ServerError -> Either.Failure(e)
            is BaseError.OtherError -> Either.Failure(e)
            else -> Either.Failure(BaseError.OtherError(e.localizedMessage))
        }
    }
}