package com.emlicame.ex03.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emlicame.ex03.ui.components.CalculatorButton


private const val TAG = "ex03"

private val calculatorKeys: List<String> = listOf(
    "AC", "C", "/", "*",
    "7", "8", "9", "-",
    "4", "5", "6", "+",
    "1", "2", "3", "=",
    "0", "."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen() {

    // STATE
    var expression by remember { mutableStateOf("0") }
    var result by remember { mutableStateOf("0") }

    // HELPER: Check if expression ends with operator
    fun endsWithOperator(text: String): Boolean {
        if (text.isEmpty()) return false
        val lastChar = text.last()
        return lastChar in setOf('+', '-', '*', '/')
    }

    // HELPER: Check if current number has decimal
    fun currentNumberHasDecimal(): Boolean {
        val lastNumber = expression.split(Regex("[+\\-*/]")).lastOrNull() ?: ""
        return lastNumber.contains(".")
    }

    // BUTTON HANDLER: Main logic for all buttons
    fun onButtonClick(label: String) {
        when (label) {
            "AC" -> {
                expression = "0"
                result = "0"
            }
            "C" -> {
                if (expression.length > 1) {
                    expression = expression.dropLast(1)
                } else {
                    expression = "0"
                }
            }
            "=" -> {
                result = "Coming soon!"
            }
            in setOf("+", "-", "*", "/") -> {
                if (expression == "0") {
                    if (label == "-") {
                        expression = label
                    }
                } else if (!endsWithOperator(expression)) {
                    expression += label
                }
            }
            "." -> {
                if (expression == "0") {
                    expression = "0."
                } else if (!currentNumberHasDecimal()) {
                    expression += "."
                }
            }
            else -> {
                // Numbers
                if (expression == "0") {
                    expression = label
                } else {
                    expression += label
                }
            }
        }
        Log.d(TAG, "Expression: $expression, Result: $result")
    }


    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Calculator") }
            )
        }
    ) { innerPadding ->
        // Box centers content and constrains width on tablets
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 600.dp)
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Expression TextField
                Text(
                    text = "Expression",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                TextField(
                    value = expression,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.headlineMedium.copy(
                        textAlign = TextAlign.End,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Result TextField
                Text(
                    text = "Result",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                TextField(
                    value = result,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.headlineLarge.copy(
                        textAlign = TextAlign.End,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Button grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),  // ← Changed from Adaptive to Fixed
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(calculatorKeys) { key ->
                        CalculatorButton(
                            label = key,
                            onClick = { onButtonClick(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewCalculatorScreen() {
    MaterialTheme {
        CalculatorScreen()
    }
}


@Preview(showBackground = true, name = "Tablet", widthDp = 800, heightDp = 1280)
@Composable
private fun PreviewCalculatorScreenTablet() {
    MaterialTheme {
        CalculatorScreen()
    }
}

@Preview(showBackground = true, name = "Tablet - TEST", widthDp = 800, heightDp = 1280)
@Composable
private fun PreviewCalculatorScreenTabletTEST() {
    MaterialTheme {
        // Add a visible marker to confirm code updated
        Column {
            Text("🔴 TEST - If you see this, preview is updated!")
            CalculatorScreen()
        }
    }
}