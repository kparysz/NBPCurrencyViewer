package com.nbp.currency.viewer.feature.currencyDetails

import androidx.navigation.NavHostController
import com.nbp.currency.viewer.core.navigation.Route

class CurrencyDetailsNavigation(navController: NavHostController) {

    val navigateToCurrencyDetails: (String, Double) -> Unit = { currencyCode, currentPrice ->
        navController.navigate(Route.CurrencyDetail(
            currencyCode = currencyCode,
            currentPrice = currentPrice))
    }
}