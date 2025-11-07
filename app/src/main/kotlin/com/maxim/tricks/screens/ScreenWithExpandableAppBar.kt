package com.maxim.tricks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maxim.tricks.appbar.ExpandableAppBar
import com.maxim.tricks.appbar.components.BackButton
import com.maxim.tricks.appbar.components.RefreshButton
import com.maxim.tricks.ui.theme.AccessibleTopBarsTheme

@Composable
internal fun ScreenWithExpandableAppBar(onBack: () -> Unit) {
    AccessibleTopBarsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                ExpandableAppBar(
                    title = "Weltraumschildkrötenabwehrsystem",
                    navigationIcon = {
                        BackButton(onClick = onBack)
                    },
                    actions = {
                        RefreshButton(onClick = {})
                    },
                    scrollStateOfMainContent = rememberScrollState()
                )
            }) { contentPadding ->
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenWithExpandableAppBarPreview() {
    AccessibleTopBarsTheme {
        ScreenWithExpandableAppBar(
            onBack = {}
        )
    }
}