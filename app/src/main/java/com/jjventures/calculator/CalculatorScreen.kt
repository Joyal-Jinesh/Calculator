package com.jjventures.calculator

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjventures.calculator.ui.theme.CalculatorAppTheme
import com.jjventures.calculator.ui.theme.DarkGray
import com.jjventures.calculator.ui.theme.LightGray
import com.jjventures.calculator.ui.theme.Orange

@Composable
fun CalculatorScreen(
    state: CalculatorState, // The current state of the calculator
    onAction: (CalculatorActions) -> Unit, // Callback for user actions
) {
    val fixedButtonSize = 65.dp // Fixed size for all buttons
    val buttonSpacing = 8.dp // Spacing for buttons

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray) // Set the background color for the entire screen
            .padding(16.dp) // Overall padding for the calculator layout
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight() // Make the main column fill available height
                .align(Alignment.BottomCenter), // Align the content to the bottom
            verticalArrangement = Arrangement.Bottom // Push content to the bottom of the column
        ) {
            // Calculator Display Area (Two lines) - Takes up available space with weight
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Display takes up 1 unit of vertical space
                    .padding(vertical = 24.dp), // Padding for the display text
                horizontalAlignment = Alignment.End, // Align text to the end (right)
                verticalArrangement = Arrangement.Bottom // Push content to the bottom of its allocated space
            ) {
                // History/Previous calculation line
                Text(
                    text = state.historyDisplay,
                    fontSize = 30.sp, // Smaller font for history
                    color = Color.White.copy(alpha = 0.7f), // Slightly faded
                    textAlign = TextAlign.End,
                    maxLines = 1, // Ensure it stays on one line
                    overflow = TextOverflow.Ellipsis, // Add ellipsis if it overflows
                    fontWeight = FontWeight.Light
                )
                // Current input/Result line - Allows horizontal scrolling for full number
                Text(
                    text = state.currentInputDisplay,
                    fontSize = 75.sp, // Main display font size
                    // Removed lineHeight as it's no longer wrapping
                    color = Color.White,
                    textAlign = TextAlign.End,
                    maxLines = 1, // Crucial: Set to 1 to force horizontal scrolling
                    modifier = Modifier.horizontalScroll(rememberScrollState()), // Enable horizontal scrolling
                    // Removed TextOverflow.Ellipsis to show full number via scrolling
                    fontWeight = FontWeight.Light
                )
            }

            // Buttons Grid - Takes up only the space it needs, anchored at the bottom
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing) // Spacing between rows of buttons
            ) {
                // First Row of Buttons (C, DEL, %, ÷) - All uniform fixed size
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround // Distribute fixed buttons
                ) {
                    CalculatorButton(
                        symbol = "C",
                        modifier = Modifier
                            .background(LightGray) // Light gray background for clear button
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Clear) } // Clear action
                    )
                    CalculatorButton(
                        symbol = "⌫", // Backspace button
                        modifier = Modifier
                            .background(LightGray)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Backspace) } // Backspace action
                    )
                    CalculatorButton(
                        symbol = CalculatorOperation.Percentage.symbol, // Percentage button
                        modifier = Modifier
                            .background(LightGray)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Operation(CalculatorOperation.Percentage)) }
                    )
                    CalculatorButton(
                        symbol = CalculatorOperation.Divide.symbol, // Using symbol from enum
                        modifier = Modifier
                            .background(Orange) // Orange background for operation button
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Operation(CalculatorOperation.Divide)) } // Divide operation
                    )
                }

                // Second Row of Buttons (7, 8, 9, x) - All uniform fixed size
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CalculatorButton(
                        symbol = "7",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer) // Darker gray for number buttons
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(7)) }
                    )
                    CalculatorButton(
                        symbol = "8",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(8)) }
                    )
                    CalculatorButton(
                        symbol = "9",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(9)) }
                    )
                    CalculatorButton(
                        symbol = CalculatorOperation.Multiply.symbol, // Using symbol from enum
                        modifier = Modifier
                            .background(Orange)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Operation(CalculatorOperation.Multiply)) }
                    )
                }

                // Third Row of Buttons (4, 5, 6, -) - All uniform fixed size
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CalculatorButton(
                        symbol = "4",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(4)) }
                    )
                    CalculatorButton(
                        symbol = "5",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(5)) }
                    )
                    CalculatorButton(
                        symbol = "6",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(6)) }
                    )
                    CalculatorButton(
                        symbol = CalculatorOperation.Subtract.symbol, // Using symbol from enum
                        modifier = Modifier
                            .background(Orange)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Operation(CalculatorOperation.Subtract)) }
                    )
                }

                // Fourth Row of Buttons (1, 2, 3, +) - All uniform fixed size
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CalculatorButton(
                        symbol = "1",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(1)) }
                    )
                    CalculatorButton(
                        symbol = "2",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(2)) }
                    )
                    CalculatorButton(
                        symbol = "3",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(3)) }
                    )
                    CalculatorButton(
                        symbol = CalculatorOperation.Add.symbol, // Using symbol from enum
                        modifier = Modifier
                            .background(Orange)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Operation(CalculatorOperation.Add)) }
                    )
                }

                // Fifth Row of Buttons (0, 00, ., =) - All uniform fixed size
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CalculatorButton(
                        symbol = "0",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Number(0)) }
                    )
                    CalculatorButton(
                        symbol = "00", // New "00" button
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.DoubleZero) } // New DoubleZero action
                    )
                    CalculatorButton(
                        symbol = ".",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Decimal) }
                    )
                    CalculatorButton(
                        symbol = "=",
                        modifier = Modifier
                            .background(Orange)
                            .size(fixedButtonSize), // Fixed size
                        onClick = { onAction(CalculatorActions.Calculate) }
                    )
                }
            }
        }
    }
}

// Preview for CalculatorScreen
@Preview(showBackground = true, device = "id:pixel_5", showSystemUi = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorAppTheme(darkTheme = true) {
        CalculatorScreen(
            state = CalculatorState(
                historyDisplay = "12345678901234567890+",
                currentInputDisplay = "98765432109876543210"
            ),
            onAction = { /* Do nothing for preview */ }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5", showSystemUi = true)
@Composable
fun CalculatorScreenPreviewWithOperation() {
    CalculatorAppTheme(darkTheme = true) {
        CalculatorScreen(
            state = CalculatorState(
                historyDisplay = "123+",
                currentInputDisplay = "0"
            ),
            onAction = { /* Do nothing for preview */ }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5", showSystemUi = true)
@Composable
fun CalculatorScreenPreviewWithSecondNumber() {
    CalculatorAppTheme(darkTheme = true) {
        CalculatorScreen(
            state = CalculatorState(
                historyDisplay = "123+",
                currentInputDisplay = "45"
            ),
            onAction = { /* Do nothing for preview */ }
        )
    }
}
