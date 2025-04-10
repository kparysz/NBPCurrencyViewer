package com.nbp.currency.viewer.feature.currencyList

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nbp.currency.viewer.core.navigation.Route

fun NavGraphBuilder.currencyListGraph(
    navigateToCurrencyDetails: (String, Double) -> Unit,
) {
    composable<Route.CurrencyList> {
        CurrencyListRoute(
            navigateToCurrencyDetails = navigateToCurrencyDetails
        )
    }
}