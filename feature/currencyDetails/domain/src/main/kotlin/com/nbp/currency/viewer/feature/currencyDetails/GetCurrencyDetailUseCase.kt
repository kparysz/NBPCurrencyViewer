package com.nbp.currency.viewer.feature.currencyDetails

import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.currencyDetails.model.CurrencyDetails
import javax.inject.Inject

class GetCurrencyDetailUseCase @Inject constructor(
    private val currencyDetailsRepository: CurrencyDetailsRepository,
) {
    suspend operator fun invoke(code: String): UiResult<List<CurrencyDetails>> =
        currencyDetailsRepository.getCurrencyDetail(code)
}