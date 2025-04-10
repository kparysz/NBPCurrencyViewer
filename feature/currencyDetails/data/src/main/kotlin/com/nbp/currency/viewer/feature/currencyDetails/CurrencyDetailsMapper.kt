package com.nbp.currency.viewer.feature.currencyDetails

import android.os.Build
import androidx.annotation.RequiresApi
import com.nbp.currency.viewer.core.nbpapi.domain.Result
import com.nbp.currency.viewer.core.nbpapi.domain.model.NpbCurrencyDetailsResponse
import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.currencyDetails.model.CurrencyDetails

@RequiresApi(Build.VERSION_CODES.GINGERBREAD)
fun Result<NpbCurrencyDetailsResponse>.toUiData() = when (this) {
    is Result.Failure -> {
        UiResult.Error(error = this.throwable?.message.orEmpty())
    }

    is Result.Success -> {
        UiResult.Success(
            this.value.rates
                ?.sortedByDescending { it.effectiveDate }
                ?.map {
                    CurrencyDetails(
                        price = it.mid ?: 0.0,
                        date = it.effectiveDate.orEmpty()
                    )
                } ?: emptyList()
        )
    }
}