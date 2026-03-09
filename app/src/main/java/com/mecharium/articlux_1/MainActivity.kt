package com.mecharium.articlux_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


import com.mecharium.articlux_1.ui.theme.Articlux_1Theme
import com.mecharium.articlux_1.ui.screens.HomeScreen



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Articlux_1Theme {
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HelloWorldPreview() {
    Articlux_1Theme {
        HomeScreen()
    }
}