package com.maxim.accessibletopbars.mainactivity

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maxim.accessibletopbars.screens.ScreenWithExpandableAppBar
import com.maxim.accessibletopbars.screens.ScreenWithTooltipAppBar
import com.maxim.accessibletopbars.screens.SelectionScreen

@Composable
internal fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Route.Selection.routeName) {
        composable(route = Route.Selection.routeName) {
            SelectionScreen(navHostController = navHostController)
        }
        composable(route = Route.Expandable.routeName) {
            ScreenWithExpandableAppBar(
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(route = Route.Tooltip.routeName) {
            ScreenWithTooltipAppBar(
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}

internal enum class Route(val routeName: String) {
    Selection("selection"),
    Expandable("expandable"),
    Tooltip("tooltip")
}