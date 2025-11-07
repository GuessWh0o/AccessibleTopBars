package com.maxim.tricks.focusablebutton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AccessibilityFocusButton(
    isVisible: Boolean,
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    var isComposedAndCanBeFocusedOn by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = isVisible
    ) {
        Button(
            modifier = modifier
                .height(54.dp)
                .focusRequester(focusRequester)
                .focusable(interactionSource = interactionSource)
                .bringIntoViewRequester(bringIntoViewRequester)
                .onGloballyPositioned {
                    isComposedAndCanBeFocusedOn = true
                },
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = color
            ),
            interactionSource = interactionSource,
            onClick = onClick
        ) {
            Text(text = text.uppercase(), textAlign = TextAlign.Center, color = Color.White)
        }
    }
    LaunchedEffect(isVisible && isComposedAndCanBeFocusedOn) {
        if (isVisible) {
            delay(200)
            focusRequester.requestFocus()
        }
    }
}