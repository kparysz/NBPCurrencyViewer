package com.nbp.currency.viewer.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessageView(
    message: String,
    onCloseError: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .background(Color.Red)
    ) {
        Text(
            modifier = Modifier
                .testTag("ErrorMessage")
                .weight(1f)
                .padding(24.dp),
            color = Color.White,
            textAlign = TextAlign.Center,
            text = message
        )

        IconButton(
            modifier = Modifier
                .testTag("CloseErrorButton"),
            onClick = onCloseError
        ) {
            Icon(
                modifier = Modifier
                    .padding(8.dp),
                imageVector = Icons.Default.Close,
                tint = Color.White,
                contentDescription = null,
            )
        }
    }
}
