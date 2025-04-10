package com.nbp.currency.viewer.core.nbpapi.data

import com.nbp.currency.viewer.core.nbpapi.data.ext.getNetworkResponse
import com.nbp.currency.viewer.core.nbpapi.domain.NbpRepository
import com.nbp.currency.viewer.core.nbpapi.domain.Result
import com.nbp.currency.viewer.core.nbpapi.domain.model.NpbCurrencyDetailsResponse
import com.nbp.currency.viewer.core.nbpapi.domain.model.NbpCurrencyTableAResponse
import javax.inject.Inject

class NetworkNbpRepository @Inject constructor(
    private val service: NbpService,
) : NbpRepository {
    override suspend fun getAllCurrencies(): Result<List<NbpCurrencyTableAResponse>> =
        getNetworkResponse(call = { service.getAllCurrencies() })

    override suspend fun getCurrencyDetail(code: String): Result<NpbCurrencyDetailsResponse> =
        getNetworkResponse(call = { service.getCurrencyDetail(code = code) })
}