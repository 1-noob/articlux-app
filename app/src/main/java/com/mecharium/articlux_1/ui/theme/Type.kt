package com.mecharium.articlux_1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

import com.mecharium.articlux_1.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)


// Tomorrow Medium Italic font
val TomorrowFont = FontFamily(
    Font(
        R.font.tomorrow_medium_italic,
        weight = FontWeight.Medium,
        style = FontStyle.Italic
    ),
    Font(
        R.font.tomorrow_semibold_italic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    )
)