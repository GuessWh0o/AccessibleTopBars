package com.maxim.tricks.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxim.tricks.focusablebutton.AccessibilityFocusButton
import com.maxim.tricks.ui.theme.AccessibleTopBarsTheme

@Composable
fun ScreenWithFocusableButton(onBack: () -> Unit) {
    var isButtonVisible by remember {
        mutableStateOf(false)
    }

    AccessibleTopBarsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    24.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                repeat(3) { index ->
                    Button(modifier = Modifier.height(54.dp), onClick = {
                        isButtonVisible = !isButtonVisible
                    }) {
                        Text("Click me to give away focus")
                    }
                }
                AccessibilityFocusButton(
                    isVisible = isButtonVisible,
                    text = "Button 1 is now focused",
                    color = Color.Green,
                    onClick = {
                        isButtonVisible = !isButtonVisible
                    })

                AccessibilityFocusButton(
                    isVisible = !isButtonVisible,
                    text = "Button 2 is now focused",
                    color = Color.Blue,
                    onClick = {
                        isButtonVisible = !isButtonVisible
                    })
            }

            BackHandler {
                onBack()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreenWithFocusableButton() {
    AccessibleTopBarsTheme {
        ScreenWithFocusableButton(onBack = {})
    }
}