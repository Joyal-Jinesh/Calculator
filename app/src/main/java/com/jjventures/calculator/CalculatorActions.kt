package com.jjventures.calculator

// Sealed class to represent all the possible actions of the calculator.
sealed class CalculatorActions{
    data class Number(val number: Int) : CalculatorActions()  // Input a digit.
    object DoubleZero : CalculatorActions()
    object Clear : CalculatorActions()  // To clear the input
    object Decimal : CalculatorActions()  // Input a decimal point.
    data class Operation(val operation: CalculatorOperation) : CalculatorActions()   // Select an operation.
    object Calculate : CalculatorActions()
    object Backspace : CalculatorActions()
}