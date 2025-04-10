package com.nbp.currency.viewer.feature.currencyList

import app.cash.turbine.test
import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.GetAllCurrenciesUseCase
import com.nbp.currency.viewer.feature.model.CurrencyData
import com.nbp.currency.viewer.feature.model.RatesData
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CurrencyListViewModelTest {

    private lateinit var viewModel: CurrencyListViewModel
    private val mockGetAllCurrenciesUseCase: GetAllCurrenciesUseCase = mock {
        onBlocking { invoke() } doReturn UiResult.Success(
            CurrencyData(
                rates = listOf(
                    RatesData(currency = "USD", code = "", mid = 1.0),
                    RatesData("EUR", code = "", mid = 0.9)
                )
            )
        )
    }

    @Test
    fun `load success data`() = runTest {
        viewModel = CurrencyListViewModel(
            getAllCurrencies = mockGetAllCurrenciesUseCase,
        )

        verify(mockGetAllCurrenciesUseCase).invoke()
        viewModel.state.test {
            val finalState = awaitItem()
            Assertions.assertThat(finalState.isLoading).isFalse()
            Assertions.assertThat(finalState.isError).isFalse()
            Assertions.assertThat(finalState.ratesData).hasSize(2)
        }
    }

    @Test
    fun `close error`() = runBlocking {
        viewModel = CurrencyListViewModel(
            getAllCurrencies = mockGetAllCurrenciesUseCase,
        )
        viewModel.closeError()

        viewModel.state.test {
            Assertions.assertThat(awaitItem().isError).isFalse()
        }
    }

    @Test
    fun `handle error`(): Unit = runBlocking {
        whenever(mockGetAllCurrenciesUseCase.invoke()).doReturn(UiResult.Error(""))
        viewModel = CurrencyListViewModel(
            getAllCurrencies = mockGetAllCurrenciesUseCase,
        )

        viewModel.state.test {
            val state = awaitItem()
            Assertions.assertThat(state.isError).isTrue()
            Assertions.assertThat(state.isLoading).isFalse()
        }
    }
}