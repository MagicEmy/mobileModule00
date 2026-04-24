package com.emlicame.ex02.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    label: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonColor = when (label) {
        "AC", "C" -> MaterialTheme.colorScheme.error
        "+", "-", "*", "/", "=" -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.secondary
    }

    Button(
        onClick = { onClick(label) },
        modifier = modifier
            .sizeIn(minWidth = 40.dp, maxWidth = 80.dp, minHeight = 40.dp, maxHeight = 80.dp)
            .fillMaxSize(),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        )
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}