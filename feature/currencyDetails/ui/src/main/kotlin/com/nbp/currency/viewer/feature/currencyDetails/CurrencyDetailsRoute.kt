package com.nbp.currency.viewer.feature.currencyDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbp.currency.viewer.core.ui.ErrorMessageView
import com.nbp.currency.viewer.feature.currencyDetails.model.CurrencyDetails
import com.nbp.currency.viewer.feature.currencyDetails.semantics.CurrencyDetailsSemantics.textColor
import com.nbp.currency.viewer.feature.detail.ui.R

@Composable
fun CurrencyDetailsRoute(
    onBackClicked: () -> Unit,
    viewModel: CurrencyDetailsViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }

    CurrencyDetailsView(
        viewState = viewState,
        onBackClicked = onBackClicked,
        onCloseError = viewModel::closeError,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyDetailsView(
    onBackClicked: () -> Unit,
    onCloseError: () -> Unit,
    viewState: CurrencyDetailsViewState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.history_page_title, viewState.currencyCode)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable { onBackClicked() }
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
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (viewState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    viewState.ratesData.forEach {
                        Column(
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Text(
                                text = it.date
                            )
                            Text(
                                modifier = Modifier.semantics {
                                    this.testTag = "Price_${it.price}"
                                    this.textColor =
                                        if (it.shouldHighlightPrice(viewState.currentPrice))
                                            "red"
                                        else
                                            "black"
                                },
                                text = it.price.toString(),
                                color = if (it.shouldHighlightPrice(viewState.currentPrice))
                                    Color.Red
                                else
                                    Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyDetailsViewPreview() {
    CurrencyDetailsView(
        onBackClicked = {},
        onCloseError = {},
        viewState = CurrencyDetailsViewState(
            isLoading = false,
            isError = false,
            ratesData = listOf(
                CurrencyDetails(price = 4.5, date = "10.04.2025"),
                CurrencyDetails(price = 4.0, date = "09.04.2025"),
                CurrencyDetails(price = 4.1, date = "08.04.2025"),
                CurrencyDetails(price = 2.3, date = "07.04.2025"),
            ),
            currentPrice = 2.3,
            currencyCode = "PLN"
        )
    )
}