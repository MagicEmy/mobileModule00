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
import com.emlicame.ex03.utils.toCalculatorString
import net.objecthunter.exp4j.ExpressionBuilder

/*
 * Checklist:
 * - [ ] Equals (=) button evaluates expression and handles errors with different messages
 * - [ ] Equals (=) when expression is 0 shows "Enter an expression"-> better no action?
 * - [ ] allow 0 /0 to show "Cannot divide by 0"
 * - [ ] allow 0 + / * -
 * - [x] Prevents multiple operators in a row
 * - [x] Prevents multiple decimals in the current number
 * - [x] UI is responsive and works on tablets
 * - [x] Uses LazyVerticalGrid with fixed columns for consistent layout
 * - [x] Well-commented code for clarity
 * - [x] Preview functions for light/dark mode and tablet layouts
 */
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
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var justCalculated by remember { mutableStateOf(false) } // To track if last action was calculation

    // HELPER: Check if expression ends with operator
    fun endsWithOperator(text: String): Boolean {
        if (text.isEmpty()) return false
        val lastChar = text.last()
        return lastChar in setOf('+', '-', '*', '/')
    }

    // HELPER: Check if current number has decimal
    val operatorRegex = Regex("[+\\-*/]")
    fun currentNumberHasDecimal(): Boolean {
        return expression.split(operatorRegex).lastOrNull()?.contains(".") == true
    }

    // BUTTON HANDLER: Main logic for all buttons
    fun onButtonClick(label: String) {
        when (label) {
            "AC" -> {
                expression = ""
                result = ""
                justCalculated = false
            }
            "C" -> {
                justCalculated = false
                if (expression.length > 1) {
                    expression = expression.dropLast(1)
                } else {
                    expression = ""
                }
            }
            "=" -> {
                when {
//                    expression == "" -> {
//                        result = "Enter an expression"
//                        return
//                    }
                    endsWithOperator(expression) -> {
                        result = "Incomplete"
                        return
                    }
                }
                try {
                    // Create an expression from the string and evaluate it
                    val calculation = ExpressionBuilder(expression)
                        .build()
                        .evaluate()

                    result = calculation.toCalculatorString()

                    justCalculated = true

//                    if (result != "Error") {
//                        justCalculated = true
//                    }
                } catch (e: Exception) {
                    // If anything goes wrong, show "Error"
                    result = if (e.message?.contains("Division by zero") == true) {
                        "Cannot divide by 0"
                    } else {
                        "Error"
                    } // also Calculation error: Expression can not be empty
                    Log.e(TAG, "Calculation error: ${e.message}")
                }
            }
            in setOf("+", "-", "*", "/") -> {
                when {
                    // CASE 1: Just calculated - continue from result
                    justCalculated -> {
                        if (result != "Error") {
                            expression = result + label  // Continue: "8" + "+" = "8+"
                            justCalculated = false       // Clear the flag
                        }
                    }

                    // CASE 2: Starting fresh - only allow minus for negatives
                    expression == "" -> {
                        if (label == "-") {
                            expression = label
                        }
                    }

                    // CASE 3: Don't allow consecutive operators
                    endsWithOperator(expression) -> {
                        // Do nothing - ignore the new operator
                    }

                    // CASE 4: Valid addition of operator
                    else -> {
                        expression += label
                    }
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
                // Numbers (0-9)
                when {
                    // CASE 1: Just calculated - start fresh with new number
                    justCalculated -> {
                        expression = label
                        result = ""
                        justCalculated = false  // Reset the flag
                    }

                    // CASE 2: Replace initial "0"
                    expression == "" -> {
                        expression = label
                    }

                    // CASE 3: Append number
                    else -> {
                        expression += label
                    }
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