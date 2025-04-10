package com.nbp.currency.viewer.feature.currencyDetails

import com.nbp.currency.viewer.core.nbpapi.domain.GetNbpCurrencyDetailUseCase
import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.currencyDetails.model.CurrencyDetails
import javax.inject.Inject

class CurrencyDetailsNetworkRepository @Inject constructor(
    private val getNbpCurrencyDetail: GetNbpCurrencyDetailUseCase,
) : CurrencyDetailsRepository {
    override suspend fun getCurrencyDetail(code: String): UiResult<List<CurrencyDetails>> =
        getNbpCurrencyDetail(code = code).toUiData()
}