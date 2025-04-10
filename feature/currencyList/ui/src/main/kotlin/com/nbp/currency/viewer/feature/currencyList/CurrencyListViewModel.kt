package com.nbp.currency.viewer.feature.currencyList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.GetAllCurrenciesUseCase
import com.nbp.currency.viewer.feature.model.RatesData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val getAllCurrencies: GetAllCurrenciesUseCase
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(CurrencyListViewState())
    val state: StateFlow<CurrencyListViewState> = mutableStateFlow.asStateFlow()

    init {
        mutableStateFlow.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            runCatching {
                getAllCurrencies()
            }.onSuccess { currencyList ->
                when (currencyList) {
                    is UiResult.Error -> {
                        mutableStateFlow.update {
                            it.copy(
                                isLoading = false,
                                isError = true
                            )
                        }
                    }

                    is UiResult.Success -> {
                        mutableStateFlow.update {
                            it.copy(
                                isError = false,
                                isLoading = false,
                                ratesData = currencyList.data.rates ?: emptyList()
                            )
                        }
                    }
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

data class CurrencyListViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val ratesData: List<RatesData> = emptyList()
)
