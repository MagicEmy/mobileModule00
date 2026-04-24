package com.emlicame.ex02.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emlicame.ex02.ui.components.CalculatorButton

private const val TAG = "ex02"

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
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Calculator",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                CompactDisplayField(
                    label = "Expression",
                    value = "0",
                    fontSize = 16.sp
                )
            }

            item {
                CompactDisplayField(
                    label = "Result",
                    value = "0",
                    fontSize = 24.sp,
                    isBold = true
                )
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            items(calculatorKeys.chunked(4)) { rowKeys ->
                Row(
                    modifier = Modifier.widthIn(max = 600.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    rowKeys.forEach { key ->
                        Box(modifier = Modifier.weight(1f)) {
                            CalculatorButton(
                                label = key,
                                onClick = { label ->
                                    Log.d(TAG, "Button pressed: $label")
                                }
                            )
                        }
                    }
                    // Filling empty space for rows with fewer than 4 buttons
                    if (rowKeys.size < 4) {
                        repeat(4 - rowKeys.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CompactDisplayField(label: String, value: String, fontSize: TextUnit, isBold: Boolean = false) {
    Column(modifier = Modifier.widthIn(max = 600.dp).fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Surface(
            modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            shape = MaterialTheme.shapes.extraSmall
        ) {
            BasicTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    textAlign = TextAlign.End,
                    fontSize = fontSize,
                    fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp)
            )
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

@Preview(showBackground = true, name = "Landscape", widthDp = 800, heightDp = 400)
@Composable
private fun PreviewCalculatorScreenLandscape() {
    MaterialTheme {
        CalculatorScreen()
    }
}
