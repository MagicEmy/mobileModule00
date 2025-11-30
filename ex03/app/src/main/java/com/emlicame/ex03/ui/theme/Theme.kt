package com.emlicame.ex03.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Charcoal,
    surface = Charcoal,
    surfaceContainer = SlightlyLighterGray, // A color for containers on top of the background
    onPrimary = Purple20,
    onSecondary = Purple20,
    onTertiary = Purple20,
    onBackground = White, // Text color on background
    onSurface = White // Text color on surfaces
)
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = Cream,
    tertiary = Pink40,
    background = Cream,
    surface = Cream,
    surfaceContainer = Almond,
    onPrimary = Almond,
    onSecondary = Almond,
    onTertiary = Cream,
    onBackground = Black,
    onSurface = Black
)


@Composable
fun Ex03Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),

    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)

            // WindowCompat.setDecorFitsSystemWindows: below
            // Make the system bars transparent.
            // This is now done by telling the app to draw behind them.
            WindowCompat.setDecorFitsSystemWindows(window, false)

            //Control the icon colors for both system bars.
            insetsController.isAppearanceLightStatusBars = !darkTheme
            insetsController.isAppearanceLightNavigationBars = !darkTheme

        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/**
 * 1.WindowCompat.setDecorFitsSystemWindows(window, false):
 * This is the master switch. It tells the system,
 * "My app will handle drawing its own background in the system bar areas."
 * By default, with this set to false,
 * the backgrounds of the system bars become transparent.
 * 2.insetsController.isAppearanceLight...:
 * These lines are crucial.
 * They control the content of the system bars
 * (the clock, Wi-Fi icon, navigation buttons)
 * to ensure they have a contrasting color (light or dark)
 * against the UI that is now being drawn behind them.
 */