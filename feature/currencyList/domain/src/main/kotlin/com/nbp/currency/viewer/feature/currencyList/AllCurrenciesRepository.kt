package com.nbp.currency.viewer.feature.currencyList

import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.model.CurrencyData

interface AllCurrenciesRepository {
    suspend fun getAllCurrencies(): UiResult<CurrencyData>
}