package com.nbp.currency.viewer.core.nbpapi.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class NbpCurrencyRatesResponse(
    val currency: String? = null,
    val code: String? = null,
    val effectiveDate: String? = null,
    val no: String? = null,
    val mid: Double? = null,
)