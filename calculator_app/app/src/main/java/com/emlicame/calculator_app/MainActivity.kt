package com.emlicame.calculator_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.emlicame.calculator_app.ui.CalculatorScreen
import com.emlicame.calculator_app.ui.theme.CalculatorAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                CalculatorScreen()
            }
        }
    }
}
