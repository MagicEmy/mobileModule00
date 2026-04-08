package com.emlicame.ex00

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.emlicame.ex00.ui.HomeScreen
import com.emlicame.ex00.ui.theme.Ex00Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex00Theme {
                HomeScreen()
            }
        }
    }
}
