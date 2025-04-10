package com.nbp.currency.viewer.core.nbpapi.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class NpbCurrencyDetailsResponse(
    val table: String? = null,
    val currency: String? = null,
    val code: String? = null,
    val rates: List<NbpCurrencyRatesResponse>? = null,
)

