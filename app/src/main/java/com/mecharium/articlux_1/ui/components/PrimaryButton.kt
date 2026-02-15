package com.mecharium.articlux_1.ui.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.mecharium.articlux_1.ui.theme.Burbank

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    // Fortnite style button
    val slantedShape = GenericShape {size, _ ->
        moveTo(0f, 0f)
        lineTo(size.width * 0.95f, 0f)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }

    Box(
        modifier = modifier
            .width(200.dp)
            .height(55.dp)
            .clip(slantedShape)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf (
                        Color(0xFFFEFC41),
                        Color(0xFFFFFFC6)
                        )
                    )
                )
            .clickable{ onClick () },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = Burbank,
            fontSize = 38.sp,
            fontWeight = FontWeight.W600,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}