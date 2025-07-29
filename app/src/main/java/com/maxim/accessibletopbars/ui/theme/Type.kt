package com.maxim.accessibletopbars.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography: Typography
    @Composable
    get() = Typography(
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.textColor
        ),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.textColor
        ),
        /* Other default text styles to override
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
        */
    )

val Typography.topBarText: TextStyle
    @Composable
    get() = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.inverseOnSurface,
        letterSpacing = 1.2.sp,
        hyphens = Hyphens.Auto
    )
