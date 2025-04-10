package com.nbp.currency.viewer.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object CurrencyList : Route

    @Serializable
    data class CurrencyDetail(
        val currencyCode: String,
        val currentPrice: Double
    ) : Route
}