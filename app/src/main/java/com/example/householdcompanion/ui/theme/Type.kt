@file:OptIn(ExperimentalTextApi::class)

package com.example.householdcompanion.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.GoogleFont.Provider
import androidx.compose.ui.unit.sp
import com.example.householdcompanion.R

private val googleFontProvider = Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
)

@OptIn(ExperimentalTextApi::class)
private val displayFontFamily = FontFamily(
        Font(
                googleFont = GoogleFont("UnifrakturCook"),
                fontProvider = googleFontProvider,
                weight = FontWeight.Normal
        )
)

@OptIn(ExperimentalTextApi::class)
private val bodyFontFamily = FontFamily(
        Font(
                googleFont = GoogleFont("Inria Serif"),
                fontProvider = googleFontProvider,
                weight = FontWeight.Normal
        )
)

val AppTypography = Typography(
        titleLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                lineHeight = 28.sp
        ),
        bodyLarge = TextStyle(
                fontFamily = bodyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp
        )
)