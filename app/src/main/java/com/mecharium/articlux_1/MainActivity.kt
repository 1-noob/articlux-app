package com.mecharium.articlux_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mecharium.articlux_1.ui.screens.HomeScreen
import com.mecharium.articlux_1.ui.theme.Articlux_1Theme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Articlux_1Theme {
                HomeScreen()
            }
        }
    }
}