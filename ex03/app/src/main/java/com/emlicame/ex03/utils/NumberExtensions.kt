package com.emlicame.ex03.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.abs

// The maximum number of characters we want the result to take up,
// similar to the ViewModel's constant.
private const val MAX_RESULT_LENGTH = 12

// Create the symbols for the US locale once, to enforce '.' as the decimal separator.
private val decimalSymbols = DecimalFormatSymbols(Locale.US)

// Formatter for scientific notation (e.g., 1.2345E7)
// Apply the US symbols to the formatter.
private val scientificFormatter = DecimalFormat("0.#####E0", decimalSymbols)

// Formatter for regular decimal numbers to avoid trailing zeros.
// Apply the US symbols to this formatter as well.
private val decimalFormatter = DecimalFormat("#.##########", decimalSymbols)


/**
 * Converts a Double to a display-friendly calculator string.
 * - Removes ".0" for whole numbers.
 * - Uses scientific notation for very large or very small numbers.
 * - Trims unnecessary precision for standard decimals.
 */
fun Double.toCalculatorString(): String {
    // Handle non-finite numbers like Infinity or NaN
    if (!this.isFinite()) {
        return "Error"
    }

    val absoluteValue = abs(this)

    // Condition to use scientific notation:
    // 1. If the number is very large (e.g., >= 1 trillion)
    // 2. OR if the number is very small (but not zero).
    val useScientific = absoluteValue >= 1_000_000_000_000.0 || (absoluteValue < 0.000001 && absoluteValue > 0.0)

    val standardDecimalString = decimalFormatter.format(this)

    // Use scientific notation if the number is outside our logical bounds 
    // OR if the resulting string is physically too long for the display.
    if (useScientific || standardDecimalString.length > MAX_RESULT_LENGTH) {
        return scientificFormatter.format(this)
    }

    // If the number is not too large/small and its string representation is not too long,
    // return the standard formatted string.
    return standardDecimalString
}
