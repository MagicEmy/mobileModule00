package com.emlicame.ex03.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        "AC", "C" -> Color(0xFFD9531E)        // Burnt Orange
        "+", "-", "*", "/", "=" -> Color(0xFFFCA311)  // Marigold Yellow
        else -> Color(0xFF6A040F)             // Deep Purple
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { onClick(label) },
            shape = CircleShape,
            modifier = Modifier
                .padding(2.dp)
                .aspectRatio(1f)
                .fillMaxSize(),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 2.dp
            ),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
        ) {
            Text(
                text = label,
                fontSize = 20.sp
            )
        }
    }
}
