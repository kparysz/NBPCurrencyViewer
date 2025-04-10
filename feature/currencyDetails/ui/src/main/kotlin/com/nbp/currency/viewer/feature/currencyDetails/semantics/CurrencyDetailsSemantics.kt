package com.nbp.currency.viewer.feature.currencyDetails.semantics

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

object CurrencyDetailsSemantics {
    val TextColorKey = SemanticsPropertyKey<String>("TextColor")
    var SemanticsPropertyReceiver.textColor by TextColorKey
}