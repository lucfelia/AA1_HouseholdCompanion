package com.example.householdcompanion.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val ColorOutline = Paper2.copy(alpha = 0.28f)

private val DarkColors = darkColorScheme(
    primary = Navy2,
    onPrimary = Paper,

    secondary = Navy,
    onSecondary = Paper,

    background = Ink,
    onBackground = Paper,

    surface = Stone,
    onSurface = Paper,

    surfaceVariant = Stone2,
    onSurfaceVariant = Paper2,

    outline = ColorOutline,
    error = Danger
)



@Composable
fun HouseholdCompanionTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = HouseholdTypography,
        content = content
    )
}