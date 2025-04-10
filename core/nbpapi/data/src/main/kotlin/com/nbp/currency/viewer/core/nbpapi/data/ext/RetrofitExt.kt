package com.nbp.currency.viewer.core.nbpapi.data.ext

import com.nbp.currency.viewer.core.nbpapi.domain.Result
import okhttp3.internal.EMPTY_RESPONSE
import retrofit2.Response

suspend fun <T> getNetworkResponse(
    call: suspend () -> Response<T>,
): Result<T> = try {
    val response = call()
    if (response.isSuccessful) {
        Result.Success(response.body() ?: EMPTY_RESPONSE as T)
    } else {
        Result.Failure(resultCode = response.code())
    }
} catch (e: Exception) {
    e.printStackTrace()
    Result.Failure(throwable = e)
}