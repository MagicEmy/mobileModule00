package com.emlicame.ex03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.emlicame.ex03.ui.CalculatorScreen
import com.emlicame.ex03.ui.theme.Ex03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex03Theme {
                CalculatorScreen()
            }
        }
    }
}
