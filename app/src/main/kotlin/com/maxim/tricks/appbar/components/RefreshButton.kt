package com.maxim.tricks.appbar.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.maxim.tricks.ui.theme.AccessibleTopBarsTheme

@Composable
internal fun RefreshButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Refresh",
            tint = MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun RefreshButtonPreview() {
    AccessibleTopBarsTheme {
        RefreshButton(
            onClick = {}
        )
    }
}