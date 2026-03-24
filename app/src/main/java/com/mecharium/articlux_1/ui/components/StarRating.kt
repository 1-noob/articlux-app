package com.mecharium.articlux_1.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StarRating(
    rating: Int,
    maxStars: Int = 5,
    spacing: Dp = 2.dp
) {
    Row {
        repeat(maxStars) { index ->
            Icon(
                imageVector = if (index < rating) {
                    Icons.Filled.Star
                } else {
                    Icons.Filled.StarBorder
                },
                contentDescription = null,
                tint = if (index < rating) {
                    Color(0xFFFFC107)
                } else {
                    Color.Gray
                }
            )
        }
    }
}