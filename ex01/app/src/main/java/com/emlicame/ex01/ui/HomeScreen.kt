package com.emlicame.ex01.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

private const val TAG = "ex01"

@Composable
fun HomeScreen(
    initialText: String = "Welcome!"
) {
    var showHello by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val text = if (showHello) "Hello World!" else initialText
            Text(text = text, style = MaterialTheme.typography.headlineMedium)

            Button(
                onClick = {
                    showHello = !showHello
                    Log.d(TAG, "Button clicked -> toggled to: $text")
                }
            ) {
                Text("Click me")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHome() {
    HomeScreen()
}