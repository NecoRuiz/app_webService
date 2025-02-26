package com.example.app_webservice.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_webservice.R

// Set of Material typography styles to start with

val fredokaFmily = FontFamily(

Font(R.font.fredoka_medium, FontWeight.Medium),
Font(R.font.fredoka_regular, FontWeight.Normal),
Font(R.font.fredoka_bold, FontWeight.Bold),
Font(R.font.fredoka_semibold, FontWeight.SemiBold),
Font(R.font.fredoka_expanded_bold, FontWeight.ExtraBold),
Font(R.font.fredoka_expanded_light, FontWeight.ExtraLight)
)

val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = fredokaFmily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(

        fontFamily = fredokaFmily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp

    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)