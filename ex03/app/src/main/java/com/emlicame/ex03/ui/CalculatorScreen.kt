package com.emlicame.ex03.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emlicame.ex03.ui.components.CalculatorButton
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.emlicame.ex03.ui.viewmodel.CalculatorViewModel

private val calculatorKeys: List<String> = listOf(
    "AC", "C", "/", "*",
    "7", "8", "9", "-",
    "4", "5", "6", "+",
    "1", "2", "3", "=",
    "0", "."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(  viewModel: CalculatorViewModel = viewModel() //inject VieModel
) {
    val expression by viewModel.expression.collectAsState()
    val result by viewModel.result.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            // Replace TopAppBar with CenterAlignedTopAppBar
            CenterAlignedTopAppBar(
                title = {
                    Text("Calculator")
                },
                // Optional: You can explicitly set colors to match your theme
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =  MaterialTheme.colorScheme.secondary,
                    titleContentColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    ) { innerPadding ->
        // Use BoxWithConstraints to get screen dimensions
        // 1. BoxWithConstraints starts its scope here.
        //    'maxHeight' is available in this lambda.
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),  // Cream
            contentAlignment = Alignment.TopCenter
        ) {
            // The `maxHeight` from `BoxWithConstraints` is available here.
            // We pass it as a parameter to our custom composable below.
            // We are inside the BoxWithConstraintsScope, so we can access its properties.
            // Before there was column, and checking maxHeight inside columns was not possible
            CalculatorContent(
                modifier = Modifier.widthIn(max = 600.dp),
                maxHeight = this.maxHeight, // Pass maxHeight down
                expression = expression,
                result = result,
                onButtonClick = { key -> viewModel.onButtonClick(key) }
            )
        }
    }
}

@Composable
private fun CalculatorContent(
    modifier: Modifier = Modifier,
    maxHeight: androidx.compose.ui.unit.Dp, // Receive maxHeight
    expression: String,
    result: String,
    onButtonClick: (String) -> Unit
) {
    // The main column that holds everything
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Conditionally display layout based on height
        if (maxHeight > 400.dp) { // Portrait or tall screen
            PortraitDisplay(expression = expression, result = result)
        } else { // Landscape or short screen
            LandscapeDisplay(expression = expression, result = result)
        }

        // Button grid takes the remaining space
        LazyVerticalGrid(
            columns = GridCells.Adaptive(80.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Added for consistency
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(calculatorKeys) { key ->
                CalculatorButton(
                    label = key,
                    onClick = onButtonClick
                )
            }
        }
    }
}


@Composable
private fun PortraitDisplay(expression: String, result: String) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
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
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun LandscapeDisplay(expression: String, result: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Expression in the first half
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Expression",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            TextField(
                value = expression,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.End),
                modifier = Modifier.fillMaxWidth()
            )
        }
        // Result in the second half
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Result",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            TextField(
                value = result,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineSmall.copy(
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
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

@Preview(showBackground = true, name = "Tablet Small/Zoomed", widthDp = 80, heightDp = 1280)
@Composable
private fun PreviewCalculatorScreenTabletSmall() {
    MaterialTheme {
        CalculatorScreen()
    }
}

// Small/Zoomed Landscape
@Preview(showBackground = true, name = "Tablet Small Landscape", widthDp = 800, heightDp = 400)
@Composable
private fun PreviewCalculatorScreenTabletSmallLandscape() {
    MaterialTheme {
        CalculatorScreen()
    }
}