package com.emlicame.ex01.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp

private const val TAG = "ex01"

@Composable
fun HomeScreen(
    initialText: String = "Welcome!"
) {
    // Centralized state
    var isHelloWorldVisible by rememberSaveable { mutableStateOf(false) }

    val displayText = if (isHelloWorldVisible) "Hello World!" else initialText

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = displayText,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    isHelloWorldVisible = !isHelloWorldVisible
                    
                    val newText = if (isHelloWorldVisible) "Hello World!" else initialText
                    Log.d(TAG, "Button clicked -> New state: $newText")
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

@Preview(showBackground = true, device = Devices.TABLET )
@Composable
private fun PreviewHomeTablet() {
    HomeScreen()
}

@Preview(
    name = "Custom Tablet Portrait",
    showBackground = true,
    widthDp = 600,
    heightDp = 960
)
@Composable
private fun PreviewHomeTabletPortrait() {
    HomeScreen()
}