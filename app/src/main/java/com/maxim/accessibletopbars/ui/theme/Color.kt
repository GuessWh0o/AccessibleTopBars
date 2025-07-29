package com.maxim.accessibletopbars.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val Purple20 = Color(0xFF45209E)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Grey40 = Color(0xFF3B3B3B)
val Grey80 = Color(0xFFBFBEBE)

val ColorScheme.textColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }