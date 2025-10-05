package com.emlicame.ex02.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emlicame.ex02.ui.components.CalcButton

private const val TAG = "Ex02"

// Ordered roughly like a typical calculator layout
private val calculatorKeys: List<String> = listOf(
    "AC", "C", "/", "*",
    "7", "8", "9", "-",
    "4", "5", "6", "+",
    "1", "2", "3", "=",
    "0", ".",              // final row adapts; grid is responsive
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculator") }, // page 8 requirement
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Expression",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            TextField(
                value = "0",
                onValueChange = {}, // disabled for now
                readOnly = true,
                singleLine = true
            )

            // Result TextField (read-only for Ex02; shows "0")
            Text(
                text = "Result",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            TextField(
                value = "0",
                onValueChange = {},
                readOnly = true,
                singleLine = true
            )

            // Keypad - responsive grid (page 8–9)
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 80.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp)
            ) {
                items(calculatorKeys) { key ->
                    CalcButton(
                        label = key,
                        onClick = { label ->
                            // Page 9: log every button press to debug console
                            Log.d(TAG, "Pressed: $label")
                        },
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Phone")
@Composable
private fun PreviewCalculatorScreen() {
    CalculatorScreen()
}
