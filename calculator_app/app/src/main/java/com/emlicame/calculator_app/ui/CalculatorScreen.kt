package com.emlicame.calculator_app.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emlicame.calculator_app.R
import com.emlicame.calculator_app.ui.components.CalculatorButton
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.emlicame.calculator_app.ui.viewmodel.CalculatorViewModel

private val calculatorKeys: List<String> = listOf(
    "AC", "C", "/", "*",
    "7", "8", "9", "-",
    "4", "5", "6", "+",
    "1", "2", "3", "=",
    "0", "."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(  viewModel: CalculatorViewModel = viewModel()
) {
    val expression by viewModel.expression.collectAsState()
    val result by viewModel.result.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_title))
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =  MaterialTheme.colorScheme.surfaceContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.TopCenter
        ) {
            CalculatorContent(
                modifier = Modifier.widthIn(max = 600.dp),
                maxHeight = this.maxHeight,
                expression = expression,
                result = result,
                onButtonClick = { key ->
                    println("Button pressed : $key")
                    Log.d("CalculatorApp", "Button pressed : $key")
                    viewModel.onButtonClick(key)
                }
            )
        }
    }
}

@Composable
private fun CalculatorContent(
    modifier: Modifier = Modifier,
    maxHeight: androidx.compose.ui.unit.Dp,
    expression: String,
    result: String,
    onButtonClick: (String) -> Unit
) {
    if (maxHeight < 500.dp) {
        Row(
            modifier = modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PortraitDisplay(expression = expression, result = result)
            }

            ButtonGrid(
                modifier = Modifier.weight(1.2f),
                onButtonClick = onButtonClick
            )
        }
    } else {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PortraitDisplay(expression = expression, result = result)
            ButtonGrid(
                modifier = Modifier.weight(1f),
                onButtonClick = onButtonClick
            )
        }
    }
}

@Composable
private fun ButtonGrid(
    modifier: Modifier = Modifier,
    onButtonClick: (String) -> Unit
) {
    val rows = calculatorKeys.chunked(4)
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rows.forEach { rowKeys ->
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowKeys.forEach { key ->
                    CalculatorButton(
                        label = key,
                        onClick = onButtonClick,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowKeys.size < 4) {
                    repeat(4 - rowKeys.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun PortraitDisplay(expression: String, result: String) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(id = R.string.expression_label),
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

        Text(
            text = stringResource(id = R.string.result_label),
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        TextField(
            value = result,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            textStyle = MaterialTheme.typography.headlineLarge.copy(
                textAlign = TextAlign.End,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, name = "Tablet", widthDp = 800, heightDp = 1280)
@Composable
private fun PreviewCalculatorScreenTablet() {
    MaterialTheme {
        CalculatorScreen()
    }
}

@PreviewLightDark
@Composable
private fun PreviewCalculatorScreen() {
    MaterialTheme {
        CalculatorScreen()
    }
}

@Preview(showBackground = true, name = "Tablet Small/Zoomed", widthDp = 800, heightDp = 1280)
@Composable
private fun PreviewCalculatorScreenTabletSmall() {
    MaterialTheme {
        CalculatorScreen()
    }
}

@Preview(showBackground = true, name = "Tablet Small Landscape", widthDp = 800, heightDp = 400)
@Composable
private fun PreviewCalculatorScreenTabletSmallLandscape() {
    MaterialTheme {
        CalculatorScreen()
    }
}
