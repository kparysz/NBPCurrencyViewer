package com.nbp.currency.viewer.core.nbpapi.data

import com.nbp.currency.viewer.core.nbpapi.domain.model.NpbCurrencyDetailsResponse
import com.nbp.currency.viewer.core.nbpapi.domain.model.NbpCurrencyTableAResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NbpService {
    @GET("api/exchangerates/tables/A")
    @Headers("Accept: application/json")
    suspend fun getAllCurrencies(): Response<List<NbpCurrencyTableAResponse>>

    @GET("api/exchangerates/rates/A/{code}/last/100/")
    @Headers("Accept: application/json")
    suspend fun getCurrencyDetail(@Path("code") code: String): Response<NpbCurrencyDetailsResponse>
}
