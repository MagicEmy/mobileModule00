package com.emlicame.ex03.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    label: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
//    val buttonColor = when (label) {
//        "AC", "C" -> MaterialTheme.colorScheme.error
//        "+", "-", "*", "/", "=" -> MaterialTheme.colorScheme.tertiary
//        else -> MaterialTheme.colorScheme.secondary
//    }

    val buttonColor = when (label) {
        "AC", "C" -> Color(0xFFFF006E)        // Hot pink
        "+", "-", "*", "/", "=" -> Color(0xFF00F5FF)  // Cyan
        else -> Color(0xFF8338EC)             // Purple
    }

    Button(
        onClick = { onClick(label) },
        modifier = modifier
            .aspectRatio(1f)  // ← KEY CHANGE: Forces square shape (width = height)
            .sizeIn(minWidth = 64.dp, minHeight = 64.dp)
            .fillMaxSize(),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        )
    ) {
        Text(
            text = label,
            fontSize = 20.sp
        )
    }
}