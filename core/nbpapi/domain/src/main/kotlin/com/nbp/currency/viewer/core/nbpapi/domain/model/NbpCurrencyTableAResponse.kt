package com.nbp.currency.viewer.core.nbpapi.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class NbpCurrencyTableAResponse(
    val table: String? = null,
    val no: String? = null,
    val effectiveDate: String? = null,
    val rates: List<NbpCurrencyRatesResponse>? = null,
)

