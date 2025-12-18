package com.example.et_clearapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = VerdeFondo,
    onPrimary = VerdeClaroTitulo,
    background = CremaTexto,
    surface = CremaTexto,
    onSurface = Color(0xFF1F1F1F),
    error = Color(0xFFB00020)
)

private val DarkColors = darkColorScheme(
    primary = VerdeFondo,
    onPrimary = VerdeClaroTitulo,

    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFEDEDED),

    error = Color(0xFFFFB4AB)
)

@Composable
fun ClearTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}