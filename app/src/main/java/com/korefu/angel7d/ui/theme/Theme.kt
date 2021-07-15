package com.korefu.angel7d.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFFffccbc),
    primaryVariant = Color(0xFFa69b97),
    onPrimary = Color.Black,
    secondary = Color(0xFF8d6e63),
    onSecondary = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF8d6e63),
    primaryVariant = Color(0xFFa69b97),
    onPrimary = Color.White,
    secondary = Color(0xFFffccbc),
    onSecondary = Color.Black,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun Angel7DTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}