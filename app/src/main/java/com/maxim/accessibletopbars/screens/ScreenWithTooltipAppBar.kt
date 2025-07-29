package com.maxim.accessibletopbars.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maxim.accessibletopbars.appbar.TooltipAppBar
import com.maxim.accessibletopbars.appbar.components.BackButton
import com.maxim.accessibletopbars.appbar.components.RefreshButton
import com.maxim.accessibletopbars.ui.theme.AccessibleTopBarsTheme

@Composable
fun ScreenWithTooltipAppBar(onBack: () -> Unit) {
    AccessibleTopBarsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TooltipAppBar(
                    title = "Weltraumschildkrötenabwehrsystem",
                    navigationIcon = {
                        BackButton(onClick = onBack)
                    },
                    actions = {
                        RefreshButton(onClick = {})
                    }
                )
            }) { contentPadding ->
            Box(modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenWithTooltipAppBarPreview() {
    AccessibleTopBarsTheme {
        ScreenWithTooltipAppBar(onBack = {})
    }
}