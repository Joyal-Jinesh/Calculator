package com.jjventures.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class CalculatorViewModel : ViewModel() {

    // Mutable state to hold the current calculator data
    var state by mutableStateOf(CalculatorState())
        private set // Private setter to ensure state is only modified within the ViewModel

    // Constants for maximum number length for internal number storage
    // Display will now handle wrapping, so this is more about preventing excessively long internal strings
    companion object {
        private const val MAX_NUM_LENGTH = 30 // Increased max length for internal storage
    }

    // Function to handle various calculator actions
    fun onAction(action: CalculatorActions) { // Updated to CalculatorActions
        when (action) {
            is CalculatorActions.Number -> enterNumber(action.number.toString()) // Updated to CalculatorActions
            is CalculatorActions.DoubleZero -> enterNumber("00") // Updated to CalculatorActions
            is CalculatorActions.Decimal -> enterDecimal() // Updated to CalculatorActions
            is CalculatorActions.Clear -> clear() // Updated to CalculatorActions
            is CalculatorActions.Operation -> enterOperation(action.operation) // Updated to CalculatorActions
            is CalculatorActions.Calculate -> calculate() // Updated to CalculatorActions
            is CalculatorActions.Backspace -> backspace() // Updated to CalculatorActions
        }
    }

    // Handles number input (now also handles "00")
    private fun enterNumber(input: String) {
        // If an error is displayed, clear it before entering a new number
        if (state.currentInputDisplay == "Error") {
            clear()
        }

        val targetNumber = if (state.operation == null) state.number1 else state.number2

        // Prevent exceeding max length for internal storage
        if (targetNumber.length >= MAX_NUM_LENGTH) {
            return
        }

        val newNumber: String
        if (targetNumber == "0" && input != "0" && input != "00") {
            // Replace "0" with the new non-zero digit
            newNumber = input
        } else if (targetNumber == "0" && input == "00") {
            // If current is "0" and input is "00", it should still be "0"
            newNumber = "0"
        } else if (targetNumber.isBlank() && input == "00") {
            // If current is blank and input is "00", it should become "0"
            newNumber = "0"
        } else {
            // Append the input
            newNumber = targetNumber + input
        }

        if (state.operation == null) {
            state = state.copy(number1 = newNumber, currentInputDisplay = newNumber)
        } else {
            state = state.copy(number2 = newNumber, currentInputDisplay = newNumber)
        }
    }


    // Handles decimal point input
    private fun enterDecimal() {
        // If an error is displayed, clear it before entering a new decimal
        if (state.currentInputDisplay == "Error") {
            clear()
        }

        val targetNumber = if (state.operation == null) state.number1 else state.number2

        // If number already contains a decimal, do nothing
        if (targetNumber.contains(".")) {
            return
        }

        val newNumber: String = if (targetNumber.isBlank()) "0." else targetNumber + "."

        if (state.operation == null) {
            state = state.copy(number1 = newNumber, currentInputDisplay = newNumber)
        } else {
            state = state.copy(number2 = newNumber, currentInputDisplay = newNumber)
        }
    }


    // Handles clearing the calculator state
    private fun clear() {
        state = CalculatorState() // Reset to initial empty state
    }

    // Handles selecting an operation
    private fun enterOperation(operation: CalculatorOperation) {
        // If an error is displayed, clear it before entering a new operation
        if (state.currentInputDisplay == "Error") {
            clear()
        }

        // Handle percentage operation separately
        if (operation == CalculatorOperation.Percentage) {
            handlePercentage()
            return
        }

        // If number1 is not empty, set the operation
        if (state.number1.isNotBlank()) {
            // If number2 exists and an operation is already set, calculate the intermediate result
            if (state.number2.isNotBlank() && state.operation != null) {
                calculate() // Calculate the previous operation first
                // Then set the new operation using the result as number1
                state = state.copy(
                    operation = operation,
                    historyDisplay = state.number1 + operation.symbol,
                    currentInputDisplay = "0", // Reset current input display for new number2
                    number2 = "" // Clear number2 for new input
                )
            } else {
                // If no number2 or no operation, just set the operation
                state = state.copy(
                    operation = operation,
                    historyDisplay = state.number1 + operation.symbol,
                    currentInputDisplay = "0", // Reset current input display for new number2
                    number2 = "" // Clear number2 for new input
                )
            }
        }
    }

    // Handles percentage calculation
    private fun handlePercentage() {
        val number1Double = state.number1.toDoubleOrNull()
        val number2Double = state.number2.toDoubleOrNull()

        if (number1Double == null) {
            return // Cannot do percentage without number1
        }

        if (state.operation == null) {
            // Case: 50% -> 0.5
            val result = number1Double / 100.0
            val formattedResult = formatResult(result)
            state = state.copy(
                number1 = formattedResult,
                historyDisplay = state.number1 + "%", // Show original number with %
                currentInputDisplay = formattedResult,
                number2 = "",
                operation = null
            )
        } else if (number2Double != null) {
            // Case: 100 + 10% -> 100 + (10% of 100)
            // Calculate number2 as a percentage of number1
            val percentageValue = (number1Double * number2Double) / 100.0
            val formattedPercentageValue = formatResult(percentageValue)
            state = state.copy(
                number2 = formattedPercentageValue,
                historyDisplay = state.number1 + state.operation?.symbol.orEmpty() + state.number2 + "%", // Show full expression with %
                currentInputDisplay = formattedPercentageValue
            )
        }
    }


    // Performs the calculation
    private fun calculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        val currentOperation = state.operation // Capture the delegated property

        if (number1 == null || number2 == null || currentOperation == null) { // Use local 'currentOperation'
            return
        }

        val result = when (currentOperation) { // Use local 'currentOperation' here
            CalculatorOperation.Add -> number1 + number2
            CalculatorOperation.Subtract -> number1 - number2
            CalculatorOperation.Multiply -> number1 * number2
            CalculatorOperation.Divide -> {
                if (number2 == 0.0) {
                    state = state.copy(
                        historyDisplay = "",
                        currentInputDisplay = "Error",
                        number1 = "0", // Reset number1 to allow new input after error
                        number2 = "",
                        operation = null
                    )
                    return
                }
                number1 / number2
            }
            CalculatorOperation.Percentage -> {
                // This case should ideally be handled by handlePercentage before calculate is called directly.
                // If it's hit here, it means percentage was the last operation pressed before '=',
                // and number2 might not have been fully entered or percentage was applied to number1 directly.
                // For a standard calculator, percentage is usually applied to the current number or as part of a calculation.
                // We'll treat it as number1 / 100.0 if number2 is not involved, or use the already calculated percentageValue if number2 was there.
                number1 / 100.0
            }
        }

        val formattedResult = formatResult(result)

        state = state.copy(
            number1 = formattedResult,
            number2 = "",
            operation = null,
            historyDisplay = state.number1 + currentOperation.symbol + state.number2 + "=", // Use local 'currentOperation'
            currentInputDisplay = formattedResult // Update display with result
        )
    }

    // Helper function to format the result for display
    private fun formatResult(result: Double): String {
        val decimalFormat = DecimalFormat("#.##########") // Up to 10 decimal places
        return decimalFormat.format(result)
    }


    // Handles backspace (deleting one character)
    private fun backspace() {
        // If an error is displayed, clear it on backspace
        if (state.currentInputDisplay == "Error") {
            clear()
            return
        }

        when {
            // If number2 is not empty, remove last char from number2
            state.number2.isNotBlank() -> {
                val newNumber2 = state.number2.dropLast(1)
                state = state.copy(
                    number2 = newNumber2,
                    currentInputDisplay = newNumber2.ifBlank { "0" } // Display "0" if number2 becomes empty
                )
            }
            // If number2 is empty but an operation exists, clear the operation
            state.operation != null -> {
                state = state.copy(
                    operation = null,
                    historyDisplay = "", // Clear history when operation is removed
                    currentInputDisplay = state.number1 // Revert current display to number1
                )
            }
            // If number1 is not empty, remove last char from number1
            state.number1.isNotBlank() -> {
                val newNumber1 = state.number1.dropLast(1)
                state = state.copy(
                    number1 = newNumber1,
                    currentInputDisplay = newNumber1.ifBlank { "0" } // Display "0" if number1 becomes empty
                )
            }
        }
    }
}