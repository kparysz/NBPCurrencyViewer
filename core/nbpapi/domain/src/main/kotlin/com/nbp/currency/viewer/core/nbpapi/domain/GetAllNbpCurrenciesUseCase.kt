package com.nbp.currency.viewer.core.nbpapi.domain

import com.nbp.currency.viewer.core.nbpapi.domain.model.NbpCurrencyTableAResponse
import javax.inject.Inject

class GetAllNbpCurrenciesUseCase @Inject constructor(private val repository: NbpRepository) {
    suspend operator fun invoke(): Result<List<NbpCurrencyTableAResponse>> =
        repository.getAllCurrencies()
}