package com.emlicame.ex03.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

/**
 * Extension function to format Double values for calculator display.
 * Removes unnecessary decimal places while maintaining precision.
 */
fun Double.toCalculatorString(): String {
    return when {
        this.isInfinite() -> "Cannot divide by 0"

        // Check for NaN (Not a Number - undefined operations)
        this.isNaN() -> "Invalid operation"

        this % 1.0 == 0.0 -> this.toLong().toString()
        else -> {
            val symbols = DecimalFormatSymbols(Locale.US)
            val formatter = DecimalFormat("#.######", symbols)
            formatter.format(this)
        }
    }
}