package com.emlicame.ex02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.emlicame.ex02.ui.CalculatorScreen
import com.emlicame.ex02.ui.theme.Ex02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex02Theme {
                CalculatorScreen()
            }
        }
    }
}
