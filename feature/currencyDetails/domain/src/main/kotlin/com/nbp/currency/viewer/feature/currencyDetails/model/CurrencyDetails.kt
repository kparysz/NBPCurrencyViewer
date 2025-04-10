package com.nbp.currency.viewer.feature.currencyDetails.model

data class CurrencyDetails(
    val price: Double,
    val date: String,
) {
    fun shouldHighlightPrice(currentPrice: Double): Boolean = price >= currentPrice * 1.1
}
