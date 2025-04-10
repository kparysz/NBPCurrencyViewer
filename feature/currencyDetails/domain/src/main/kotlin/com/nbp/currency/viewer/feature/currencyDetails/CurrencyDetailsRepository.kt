package com.nbp.currency.viewer.feature.currencyDetails

import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.currencyDetails.model.CurrencyDetails

interface CurrencyDetailsRepository {
    suspend fun getCurrencyDetail(code: String): UiResult<List<CurrencyDetails>>
}