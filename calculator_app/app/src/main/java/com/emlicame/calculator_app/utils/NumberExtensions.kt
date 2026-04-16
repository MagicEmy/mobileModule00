package com.emlicame.calculator_app.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.abs

private const val MAX_RESULT_LENGTH = 12

// Create the symbols to enforce '.' as the decimal separator.
private val decimalSymbols = DecimalFormatSymbols(Locale.US)

private val scientificFormatter = DecimalFormat("0.#####E0", decimalSymbols)

private val decimalFormatter = DecimalFormat("#.##########", decimalSymbols)

/**
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

    // Condition to use scientific notation: very large OR very small number(but not zero).
    val useScientific = absoluteValue >= 1_000_000_000_000.0 || (absoluteValue < 0.000001 && absoluteValue > 0.0)

    val standardDecimalString = decimalFormatter.format(this)

    //if the number is outside our logical bounds or physically too long to display
    if (useScientific || standardDecimalString.length > MAX_RESULT_LENGTH) {
        return scientificFormatter.format(this)
    }
    return standardDecimalString
}
