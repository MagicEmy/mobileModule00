package com.emlicame.ex01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.emlicame.ex01.ui.HomeScreen
import com.emlicame.ex01.ui.theme.Ex01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex01Theme {
                HomeScreen()
            }
        }

    }
}
