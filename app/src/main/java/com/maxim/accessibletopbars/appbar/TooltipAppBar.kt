package com.maxim.accessibletopbars.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.maxim.accessibletopbars.appbar.components.BackButton
import com.maxim.accessibletopbars.appbar.components.RefreshButton
import com.maxim.accessibletopbars.isScreenReaderOn
import com.maxim.accessibletopbars.ui.theme.AccessibleTopBarsTheme
import com.maxim.accessibletopbars.ui.theme.topBarText
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TooltipAppBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val tooltipState = rememberTooltipState()
    var isOverflowing by remember { mutableStateOf(false) }

    val isTopBarTitleClickable by remember {
        derivedStateOf {
            isOverflowing && !context.isScreenReaderOn() //We do not want TalkBack to say "Double tap to activate" because by default it reads the whole title anyway
        }
    }

    Column {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = MaterialTheme.colorScheme.inverseSurface
            ),
            navigationIcon = navigationIcon,
            actions = actions,
            title = {
                TooltipBox(
                    modifier = Modifier
                        .weight(1f)
                        .semantics(mergeDescendants = true) {
                            this.contentDescription = title
                            heading()
                        }
                        .clickable(isTopBarTitleClickable) {
                            coroutineScope.launch {
                                tooltipState.show()
                            }
                        },
                    enableUserInput = isTopBarTitleClickable,
                    positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
                    tooltip = {
                        PlainTooltip(
                            containerColor = TooltipDefaults.richTooltipColors().containerColor
                        ) {
                            Text(text = title, style = MaterialTheme.typography.bodyMedium)
                        }
                    },
                    state = tooltipState
                ) {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.topBarText,
                        onTextLayout = { textLayoutResult ->
                            isOverflowing = textLayoutResult.hasVisualOverflow
                        },
                        modifier = Modifier.clearAndSetSemantics { }
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpandableAppBarPreview() {
    AccessibleTopBarsTheme {
        TooltipAppBar(
            title = "Weltraumschildkrötenabwehrsystem",
            navigationIcon = {
                BackButton(onClick = {})
            },
            actions = {
                RefreshButton(onClick = {})
            }
        )
    }
}