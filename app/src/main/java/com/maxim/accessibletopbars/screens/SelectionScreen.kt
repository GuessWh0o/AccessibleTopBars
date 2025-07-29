package com.maxim.accessibletopbars.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maxim.accessibletopbars.mainactivity.Route
import com.maxim.accessibletopbars.ui.theme.AccessibleTopBarsTheme
import com.maxim.accessibletopbars.ui.theme.dimens

@Composable
internal fun SelectionScreen(navHostController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.defaultSpace, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                navHostController.navigate(Route.Expandable.routeName)
            }) {
                Text("Expandable App Bar")
            }

            Button(onClick = {
                navHostController.navigate(Route.Tooltip.routeName)

            }) {
                Text("Tooltip App Bar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectionScreenPreview() {
    AccessibleTopBarsTheme {
        SelectionScreen(navHostController = rememberNavController())
    }
}