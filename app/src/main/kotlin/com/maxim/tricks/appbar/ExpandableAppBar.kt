package com.maxim.tricks.appbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxim.tricks.appbar.components.BackButton
import com.maxim.tricks.appbar.components.RefreshButton
import com.maxim.tricks.isScreenReaderOn
import com.maxim.tricks.ui.theme.AccessibleTopBarsTheme
import com.maxim.tricks.ui.theme.dimens
import com.maxim.tricks.ui.theme.topBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableAppBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollStateOfMainContent: ScrollState
) {
    val context = LocalContext.current
    var isTitleExpanded by remember { mutableStateOf(false) }
    var wasTextOverflowingInitially by remember { mutableStateOf(false) } //Was text initially overflowing, when AppBar was in collapsed state?

    LaunchedEffect(scrollStateOfMainContent.value) {
        if (scrollStateOfMainContent.value > 0 && isTitleExpanded) {
            isTitleExpanded = false
        }
    }

    val isTopBarTitleClickable by remember {
        derivedStateOf {
            wasTextOverflowingInitially && !context.isScreenReaderOn() //We do not want TalkBack to say "Double tap to activate" because by default it reads the whole title anyway
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .animateContentSize(),
            color = MaterialTheme.colorScheme.inverseSurface
        ) {
            Row(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimens.defaultSpace)
                    .heightIn(min = TopAppBarDefaults.MediumAppBarCollapsedHeight)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigationIcon()

                AnimatedContent(
                    targetState
                    = isTitleExpanded,
                    label = "AnimatedTitleText",
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 16.dp)
                        .semantics(mergeDescendants = true) {
                            this.contentDescription = title
                            heading()
                        }
                        .clickable(
                            enabled = isTopBarTitleClickable,
                            indication = null, //Disable ripple effect
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            isTitleExpanded = !isTitleExpanded
                        }
                        .wrapContentHeight()
                        .fillMaxWidth(),
                ) { expanded ->

                    Text(
                        text = title,
                        maxLines = if (expanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.topBarText,
                        onTextLayout = { textLayoutResult ->
                            val overflowing = textLayoutResult.hasVisualOverflow
                            if (overflowing && !wasTextOverflowingInitially) { //When expanded the text won't overflow anymore, therefore we need to check the collapsed state
                                wasTextOverflowingInitially = true
                            }
                        },
                        modifier = Modifier.clearAndSetSemantics {  }
                    )

                }
                actions()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpandableAppBarPreview() {
    AccessibleTopBarsTheme {
        ExpandableAppBar(
            title = "Weltraumschildkrötenabwehrsystem",
            navigationIcon = {
                BackButton(onClick = {})
            },
            actions = {
                RefreshButton(onClick = {})
            },
            scrollStateOfMainContent = rememberScrollState()
        )
    }
}