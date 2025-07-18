package com.jjventures.calculator

// Data Class to represent the current state of the calculator
data class CalculatorState(
    val number1: String = "0",  // First number in the calculation.
    val number2: String = "",   // The Second number in the calculation.
    val operation: CalculatorOperation? = null,  // The selected arithmetic operations.
    val historyDisplay: String = "", // The text currently shown on the calculator display
    val currentInputDisplay: String = "0"  // For showing the current number being typed.
)