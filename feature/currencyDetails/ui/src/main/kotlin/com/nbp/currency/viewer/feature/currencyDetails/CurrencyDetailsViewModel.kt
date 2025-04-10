package com.nbp.currency.viewer.feature.currencyDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.nbp.currency.viewer.core.navigation.Route
import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.currencyDetails.model.CurrencyDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCurrencyDetail: GetCurrencyDetailUseCase
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(
        CurrencyDetailsViewState(
            currencyCode = savedStateHandle.toRoute<Route.CurrencyDetail>().currencyCode,
            currentPrice = savedStateHandle.toRoute<Route.CurrencyDetail>().currentPrice
        )
    )
    val state: StateFlow<CurrencyDetailsViewState> = mutableStateFlow.asStateFlow()

    fun loadHistory() {
        mutableStateFlow.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            runCatching {
                getCurrencyDetail(mutableStateFlow.value.currencyCode)
            }.onSuccess { currencyDetails ->
                when (currencyDetails) {
                    is UiResult.Error -> {
                        mutableStateFlow.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is UiResult.Success -> {
                        mutableStateFlow.update {
                            it.copy(
                                isLoading = false,
                                ratesData = currencyDetails.data
                            )
                        }
                    }
                }

            }.onFailure {
                mutableStateFlow.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun closeError() {
        mutableStateFlow.update {
            it.copy(isError = false)
        }
    }
}

data class CurrencyDetailsViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val currentPrice: Double = 0.0,
    val currencyCode: String = "",
    val ratesData: List<CurrencyDetails> = emptyList()
)
