package com.emlicame.ex02.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalcButton(
    label: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .sizeIn(minWidth = 56.dp, minHeight = 56.dp), // keeps things tappable
        onClick = { onClick(label) }
    ) {
        // Center the label
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = label)
        }
    }
}