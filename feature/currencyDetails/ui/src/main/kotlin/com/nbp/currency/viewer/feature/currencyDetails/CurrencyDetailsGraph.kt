package com.nbp.currency.viewer.feature.currencyDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nbp.currency.viewer.core.navigation.Route

fun NavGraphBuilder.currencyDetailsGraph(
    navController: NavController,
) {
    composable<Route.CurrencyDetail> {
        CurrencyDetailsRoute(
            onBackClicked = navController::navigateUp
        )
    }
}