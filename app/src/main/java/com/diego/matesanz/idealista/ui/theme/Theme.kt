package com.diego.matesanz.idealista.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.diego.matesanz.idealista.ui.theme.Typography

private val DarkColorScheme = darkColorScheme(
    primary = Manz,
    onPrimary = Black,
    secondary = MediumRedViolet,
    onSecondary = White,
    tertiary = ScotchMist,
    onTertiary = MediumRedViolet,
    surface = Cararra,
    background = White,
    onBackground = Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Manz,
    onPrimary = Black,
    secondary = MediumRedViolet,
    onSecondary = White,
    tertiary = ScotchMist,
    onTertiary = MediumRedViolet,
    surface = Cararra,
    background = White,
    onBackground = Black,
)

@Composable
fun IdealistaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
