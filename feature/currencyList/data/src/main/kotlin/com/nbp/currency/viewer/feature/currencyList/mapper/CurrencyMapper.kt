package com.nbp.currency.viewer.feature.currencyList.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.nbp.currency.viewer.core.nbpapi.domain.Result
import com.nbp.currency.viewer.core.nbpapi.domain.model.NbpCurrencyTableAResponse
import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.model.CurrencyData
import com.nbp.currency.viewer.feature.model.RatesData
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.GINGERBREAD)
fun Result<List<NbpCurrencyTableAResponse>>.toUiData() = when (this) {
    is Result.Failure -> {
        UiResult.Error(error = this.throwable?.message.orEmpty())
    }

    is Result.Success -> {
        UiResult.Success(
            CurrencyData(
                rates = this.value.map {
                    it.rates?.map { rate ->
                        RatesData(
                            rate.currency?.replaceFirstChar { char ->
                                if (char.isLowerCase()) char.titlecase(
                                    Locale.ROOT
                                ) else char.toString()
                            }.orEmpty(),
                            rate.code.orEmpty(),
                            rate.mid ?: 0.0
                        )
                    }
                }.firstOrNull() ?: emptyList()
            )
        )
    }
}