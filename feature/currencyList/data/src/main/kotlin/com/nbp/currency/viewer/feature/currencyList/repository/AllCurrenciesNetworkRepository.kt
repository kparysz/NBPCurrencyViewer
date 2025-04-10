package com.nbp.currency.viewer.feature.currencyList.repository

import com.nbp.currency.viewer.core.nbpapi.domain.GetAllNbpCurrenciesUseCase
import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.currencyList.AllCurrenciesRepository
import com.nbp.currency.viewer.feature.currencyList.mapper.toUiData
import com.nbp.currency.viewer.feature.model.CurrencyData
import javax.inject.Inject

class AllCurrenciesNetworkRepository @Inject constructor(
    private val getAllNbpCurrencies: GetAllNbpCurrenciesUseCase,
) : AllCurrenciesRepository {
    override suspend fun getAllCurrencies(): UiResult<CurrencyData> =
        getAllNbpCurrencies().toUiData()
}

