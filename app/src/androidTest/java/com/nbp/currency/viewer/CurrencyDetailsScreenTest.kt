package com.nbp.currency.viewer

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.nbp.currency.viewer.feature.currencyDetails.CurrencyDetailsView
import com.nbp.currency.viewer.feature.currencyDetails.CurrencyDetailsViewState
import com.nbp.currency.viewer.feature.currencyDetails.model.CurrencyDetails
import com.nbp.currency.viewer.feature.currencyDetails.semantics.CurrencyDetailsSemantics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrencyDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun currency_price_red_if_difference_more_than_ten_percentage() {
        composeTestRule.setContent {
            CurrencyDetailsView(
                onBackClicked = { },
                onCloseError = { },
                viewState = CurrencyDetailsViewState(
                    isLoading = false,
                    isError = false,
                    currentPrice = 6.7,
                    currencyCode = "USD",
                    ratesData = listOf(
                        CurrencyDetails(price = 7.5, date = "10.04.2025"),
                        CurrencyDetails(price = 6.7, date = "11.04.2025"),
                        CurrencyDetails(price = 6.4, date = "12.04.2025"),
                    )

                )
            )
        }

        composeTestRule
            .onNodeWithTag("Price_7.5")
            .assert(SemanticsMatcher.expectValue(CurrencyDetailsSemantics.TextColorKey, "red"))

        composeTestRule
            .onNodeWithTag("Price_6.7")
            .assert(SemanticsMatcher.expectValue(CurrencyDetailsSemantics.TextColorKey, "black"))
    }
}