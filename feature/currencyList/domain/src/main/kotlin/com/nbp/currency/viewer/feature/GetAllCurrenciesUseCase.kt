package com.nbp.currency.viewer.feature

import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.currencyList.AllCurrenciesRepository
import com.nbp.currency.viewer.feature.model.CurrencyData
import javax.inject.Inject

class GetAllCurrenciesUseCase @Inject constructor(
    private val allCurrenciesRepository: AllCurrenciesRepository,
) {
    suspend operator fun invoke(): UiResult<CurrencyData> =
        allCurrenciesRepository.getAllCurrencies()
}