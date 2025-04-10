package com.nbp.currency.viewer.feature.currencyList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbp.currency.viewer.core.ui.ErrorMessageView
import com.nbp.currency.viewer.feature.list.ui.R
import com.nbp.currency.viewer.feature.model.RatesData

@Composable
fun CurrencyListRoute(
    navigateToCurrencyDetails: (String, Double) -> Unit,
    viewModel: CurrencyListViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

    CurrencyListView(
        navigateToCurrencyDetails = navigateToCurrencyDetails,
        viewState = viewState,
        onCloseError = viewModel::closeError
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyListView(
    navigateToCurrencyDetails: (String, Double) -> Unit,
    onCloseError: () -> Unit,
    viewState: CurrencyListViewState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        modifier = Modifier.testTag("MainPageToolbar"),
                        text = stringResource(R.string.page_title)
                    )
                }
            )
        },
        bottomBar = {
            if (viewState.isError) {
                ErrorMessageView(
                    onCloseError = onCloseError,
                    message = stringResource(R.string.generic_error_message)
                )
            }
        }
    ) { paddingValues ->
        if (viewState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                viewState.ratesData.forEach {
                    RateRow(navigateToCurrencyDetails, it)
                }
            }

        }
    }
}

@Composable
private fun RateRow(
    navigateToCurrencyDetails: (String, Double) -> Unit,
    it: RatesData
) {
    Row(
        modifier = Modifier
            .padding(24.dp)
            .clickable {
                navigateToCurrencyDetails(it.code, it.mid)
            },
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = it.currency
        )
        Text(
            modifier = Modifier,
            text = it.mid.toString()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyListViewPreview() {
    CurrencyListView(
        navigateToCurrencyDetails = {_, _ ->},
        onCloseError = {},
        viewState = CurrencyListViewState(
            isLoading = false,
            isError = false,
            ratesData = listOf(
                RatesData(currency = "duis", code = "penatibus", mid = 2.3),
                RatesData(currency = "duis", code = "penatibus", mid = 2.3),
                RatesData(currency = "duis", code = "penatibus", mid = 2.3),
                RatesData(currency = "duis", code = "penatibus", mid = 2.3),
                RatesData(currency = "duis", code = "penatibus", mid = 2.3),
                RatesData(currency = "duis", code = "penatibus", mid = 2.3),
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CurrencyListViewLoadingPreview() {
    CurrencyListView(
        navigateToCurrencyDetails = {_, _ ->},
        onCloseError = {},
        viewState = CurrencyListViewState(
            isLoading = true,
            isError = false,
            ratesData = listOf()
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CurrencyListViewErrorPreview() {
    CurrencyListView(
        navigateToCurrencyDetails = {_, _ ->},
        onCloseError = {},
        viewState = CurrencyListViewState(
            isLoading = false,
            isError = true,
            ratesData = listOf()
        )
    )
}