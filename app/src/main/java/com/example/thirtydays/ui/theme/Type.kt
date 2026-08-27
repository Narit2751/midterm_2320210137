package com.example.thirtydays.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thirtydays.R

val jua = FontFamily(
    Font(R.font.jua)
)

val mochi = FontFamily(
    Font(R.font.mochi)
)

val Typography = Typography(

    // App title
    displayLarge = TextStyle(
        fontFamily = jua,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),

    // Training title
    displayMedium = TextStyle(
        fontFamily = jua,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),

    // Day number
    titleMedium = TextStyle(
        fontFamily = jua,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),

    // Description
    bodyLarge = TextStyle(
        fontFamily = mochi,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    // Small labels
    labelSmall = TextStyle(
        fontFamily = mochi,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)