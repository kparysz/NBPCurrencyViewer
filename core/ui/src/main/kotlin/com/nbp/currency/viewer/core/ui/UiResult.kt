package com.nbp.currency.viewer.core.ui

sealed interface UiResult<out T> {
    data class Success<T>(val data: T) : UiResult<T>
    data class Error<T>(val error: String? = null) : UiResult<T>
}