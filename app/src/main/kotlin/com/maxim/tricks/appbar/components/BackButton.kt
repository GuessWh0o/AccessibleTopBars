package com.maxim.tricks.appbar.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.maxim.tricks.ui.theme.AccessibleTopBarsTheme

@Composable
internal fun BackButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "Go back",
            tint = MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun BackButtonPreview() {
    AccessibleTopBarsTheme {
        BackButton(
            onClick = {}
        )
    }
}