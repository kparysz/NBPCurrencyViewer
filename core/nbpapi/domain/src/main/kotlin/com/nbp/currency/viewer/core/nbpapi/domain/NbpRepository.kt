package com.nbp.currency.viewer.core.nbpapi.domain

import com.nbp.currency.viewer.core.nbpapi.domain.model.NpbCurrencyDetailsResponse
import com.nbp.currency.viewer.core.nbpapi.domain.model.NbpCurrencyTableAResponse

interface NbpRepository {

    suspend fun getAllCurrencies(): Result<List<NbpCurrencyTableAResponse>>
    suspend fun getCurrencyDetail(code: String): Result<NpbCurrencyDetailsResponse>
}