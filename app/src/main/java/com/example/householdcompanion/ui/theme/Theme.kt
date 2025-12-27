package com.example.householdcompanion.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
        primary = EmberOrange,
        onPrimary = NightSky,
        secondary = ForestGreen,
        onSecondary = MistSilver,
        tertiary = BattleRed,
        background = NightSky,
        surface = IronSteel,
        onSurface = MistSilver,
        onBackground = MistSilver,
        surfaceVariant = IronSteel,
        onSurfaceVariant = MistSilver
)

private val LightColorScheme = lightColorScheme(
        primary = EmberOrange,
        onPrimary = NightSky,
        secondary = ForestGreen,
        onSecondary = NightSky,
        tertiary = BattleRed,
        background = MistSilver,
        surface = ColorPalette.surfaceLight,
        onSurface = NightSky,
        onBackground = NightSky,
        surfaceVariant = ColorPalette.surfaceVariantLight,
        onSurfaceVariant = IronSteel
)

private object ColorPalette {
    val surfaceLight = MistSilver
    val surfaceVariantLight = Color(0xFFE8EDF5)
}

@Composable
fun HouseholdCompanionTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
    )
}
