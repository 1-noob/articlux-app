package com.mecharium.articlux_1.ui.theme

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

data class UiDimensions(
    val cardWidth: androidx.compose.ui.unit.Dp,
    val cardHeight: androidx.compose.ui.unit.Dp,
    val smallSpacing: androidx.compose.ui.unit.Dp,
    val mediumSpacing: androidx.compose.ui.unit.Dp,
    val largeSpacing: androidx.compose.ui.unit.Dp
)

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun rememberUiDimensions(): UiDimensions {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    return if (screenWidth < 600) {
        // PHONE
        UiDimensions(
            cardWidth = 280.dp,
            cardHeight = 125.dp,
            smallSpacing = 4.dp,
            mediumSpacing = 6.dp,
            largeSpacing = 8.dp
        )
    } else {
        // TABLET
        UiDimensions(
            cardWidth = 300.dp,
            cardHeight = 135.dp,
            smallSpacing = 2.dp, // Between title and category
            mediumSpacing = 7.dp, // Between stars and title
            largeSpacing = 16.dp
        )
    }
}