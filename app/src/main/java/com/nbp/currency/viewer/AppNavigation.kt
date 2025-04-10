package com.nbp.currency.viewer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nbp.currency.viewer.core.navigation.Route
import com.nbp.currency.viewer.feature.currencyDetails.CurrencyDetailsNavigation
import com.nbp.currency.viewer.feature.currencyDetails.currencyDetailsGraph
import com.nbp.currency.viewer.feature.currencyList.currencyListGraph

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val currencyDetailsNavigation = remember(navController) {
        CurrencyDetailsNavigation(navController)
    }

    NavHost(navController = navController, startDestination = Route.CurrencyList) {
        currencyListGraph(
            navigateToCurrencyDetails = { code, price ->
                currencyDetailsNavigation.navigateToCurrencyDetails(code, price)
            }
        )
        currencyDetailsGraph(navController = navController)
    }
}
