package com.nbp.currency.viewer.core.nbpapi.domain

import com.nbp.currency.viewer.core.nbpapi.domain.model.NpbCurrencyDetailsResponse
import javax.inject.Inject

class GetNbpCurrencyDetailUseCase @Inject constructor(private val repository: NbpRepository) {
    suspend operator fun invoke(code: String): Result<NpbCurrencyDetailsResponse> =
        repository.getCurrencyDetail(code)
}