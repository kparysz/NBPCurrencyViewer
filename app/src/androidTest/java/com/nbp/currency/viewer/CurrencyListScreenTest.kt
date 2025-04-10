package com.nbp.currency.viewer

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.nbp.currency.viewer.feature.currencyList.CurrencyListView
import com.nbp.currency.viewer.feature.currencyList.CurrencyListViewState
import com.nbp.currency.viewer.feature.model.RatesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrencyListScreenTest {

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
    fun topBar_shows_title_and_errorMessage_when_error() {
        var errorClosed = false

        composeTestRule.setContent {
            CurrencyListView(
                navigateToCurrencyDetails = {_, _ ->},
                onCloseError = { errorClosed = true },
                viewState = CurrencyListViewState(
                    isLoading = false,
                    isError = true,
                    ratesData = listOf()
                )
            )
        }

        composeTestRule
            .onNodeWithTag("MainPageToolbar")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("ErrorMessage")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("CloseErrorButton")
            .performClick()

        assert(errorClosed)
    }

    @Test
    fun show_currency_list() {
        composeTestRule.setContent {
            CurrencyListView(
                navigateToCurrencyDetails = {_, _ ->},
                onCloseError = { },
                viewState = CurrencyListViewState(
                    isLoading = false,
                    isError = false,
                    ratesData = listOf(
                        RatesData(currency = "nominavi", code = "omnesque", mid = 0.1),
                        RatesData(currency = "noluisse", code = "magna", mid = 2.3)
                    )
                )
            )
        }

        composeTestRule
            .onNodeWithTag("MainPageToolbar")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("nominavi").assertIsDisplayed()
        composeTestRule.onNodeWithText("0.1").assertIsDisplayed()

        composeTestRule.onNodeWithText("noluisse").assertIsDisplayed()
        composeTestRule.onNodeWithText("2.3").assertIsDisplayed()
    }
}