package com.maxim.tricks.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.maxim.tricks.ui.theme.AccessibleTopBarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AccessibleTopBarsTheme {
                val navHostController = rememberNavController()

                NavGraph(navHostController)
            }
        }
    }
}