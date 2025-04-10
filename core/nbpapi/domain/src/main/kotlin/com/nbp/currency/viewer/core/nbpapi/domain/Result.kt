package com.nbp.currency.viewer.core.nbpapi.domain

sealed interface Result<out T> {
    data class Success<T>(val value: T) : Result<T>
    data class Failure(
        val resultCode: Int? = null,
        val throwable: Throwable? = null,
    ) : Result<Nothing>
}