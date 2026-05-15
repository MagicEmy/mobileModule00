package com.emlicame.calculator_app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.objecthunter.exp4j.ExpressionBuilder
import com.emlicame.calculator_app.utils.toCalculatorString

private const val TAG = "CalculatorViewModel"
private const val MAX_EXPRESSION_LENGTH = 20

class CalculatorViewModel : ViewModel() {

    // PRIVATE mutable state = internal state (only ViewModel controls state)
    private val _expression = MutableStateFlow("")
    private val _result = MutableStateFlow("")
    private val _justCalculated = MutableStateFlow(false)

    // PUBLIC immutable state = exposed to UI (UI can only read)
    val expression: StateFlow<String> = _expression.asStateFlow()
    val result: StateFlow<String> = _result.asStateFlow()


    private val operatorRegex = Regex("[+\\-*/]")

    fun onButtonClick(label: String) {
        when (label) {
            "AC" -> handleClearAll()
            "C" -> handleClearLast()
            "=" -> handleEquals()
            in setOf("+", "-", "*", "/") -> handleOperator(label)
            "." -> handleDecimal()
            else -> handleNumber(label)
        }
        Log.d(TAG, "Expression: ${_expression.value}, Result: ${_result.value}")
    }

    /**
     * AC button: Clear everything
     */
    private fun handleClearAll() {
        _expression.value = ""
        _result.value = ""
        _justCalculated.value = false
    }

    /**
     * C button: Delete last character
     */
    private fun handleClearLast() {
        _justCalculated.value = false
        if (_expression.value.isNotEmpty()) {
            _expression.value = _expression.value.dropLast(1)
        }
    }

    private fun handleEquals() {
        if (_expression.value.isEmpty() || endsWithOperator(_expression.value)) {
            _result.value = "Incomplete expression"
            return
        }

        try {
            val calculation = ExpressionBuilder(_expression.value)
                .build()
                .evaluate()

            _result.value = calculation.toCalculatorString()
            _justCalculated.value = true

        } catch (e: Exception) {
            _result.value = if (e.message?.contains("Division by zero") == true) {
                "Cannot divide by 0"
            } else {
                "Error"
            }
            Log.e(TAG, "Calculation error: ${e.message}")
        }
    }

    private fun handleOperator(operator: String) {
        if (_expression.value.length >= MAX_EXPRESSION_LENGTH && !_justCalculated.value) {
            return // Do nothing
        }
        
        val currentExpr = _expression.value
        
        when {
            // Continue from previous result
            _justCalculated.value -> {
                if (_result.value != "Error" && _result.value.isNotEmpty()) {
                    _expression.value = _result.value + operator
                    _justCalculated.value = false
                }
            }

            currentExpr.isEmpty() -> {
                if (operator == "-") {
                    _expression.value = operator
                }
            }

            // Handle consecutive operators
            endsWithOperator(currentExpr) -> {
                val lastChar = currentExpr.last()
                
                if (operator == "-" && (lastChar == '*' || lastChar == '/')) {
                    _expression.value += operator
                } 
                // if 2 operators (e.g. "*-") and user presses a third, replace both
                else if (currentExpr.length >= 2 && endsWithOperator(currentExpr.dropLast(1))) {
                    _expression.value = currentExpr.dropLast(2) + operator
                }
                else {
                    _expression.value = currentExpr.dropLast(1) + operator
                }
            }

            else -> {
                _expression.value += operator
            }
        }
    }

    private fun handleDecimal() {
        if (_expression.value.length >= MAX_EXPRESSION_LENGTH) {
            return
        }
        when {
            _expression.value.isEmpty() -> {
                _expression.value = "0."
            }
            currentNumberHasDecimal() -> {
                // Ignore - already has decimal
            }
            endsWithOperator(_expression.value) -> {
                _expression.value += "0."
            }
            else -> {
                _expression.value += "."
            }
        }
    }

    private fun handleNumber(number: String) {
        if (_expression.value.length >= MAX_EXPRESSION_LENGTH && !_justCalculated.value) {
            return // Do nothing
        }
        when {
            // Start fresh after calculation
            _justCalculated.value -> {
                _expression.value = number
                _result.value = ""
                _justCalculated.value = false
            }

            // Just append
            else -> {
                _expression.value += number
            }
        }
    }

    // HELPER FUNCTIONS

    private fun endsWithOperator(text: String): Boolean {
        if (text.isEmpty()) return false
        return text.last() in setOf('+', '-', '*', '/')
    }

    private fun currentNumberHasDecimal(): Boolean {
        return _expression.value
            .split(operatorRegex)
            .lastOrNull()
            ?.contains(".") == true
    }
}
