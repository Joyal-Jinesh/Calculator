package com.jjventures.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjventures.calculator.ui.theme.CalculatorAppTheme
import com.jjventures.calculator.ui.theme.DarkGray
import com.jjventures.calculator.ui.theme.Orange

@Composable
fun CalculatorButton(
    symbol: String, // The text/symbol to display on the button
    modifier: Modifier = Modifier, // Modifier for custom styling
    textColor: Color = Color.White, // Text color of the button
    onClick: () -> Unit // Click listener for the button
) {
    Box(
        contentAlignment = Alignment.Center, // Center the symbol within the button
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp)) // Apply rounded corners to the button
            .clickable { onClick() } // Make the box clickable
            .then(modifier) // Apply any additional modifiers passed in
    ) {
        Text(
            text = symbol, // Display the symbol
            fontSize = 36.sp, // Font size for the symbol
            color = textColor // Text color
        )
    }
}

// --- Simple Preview Code Below ---

@Preview(showBackground = true)
@Composable
fun CalculatorButtonSimplePreview() {
    CalculatorAppTheme(darkTheme = true) { // Apply the app's dark theme for accurate preview
        Surface(
            // Use a smaller, fixed size for a simple preview
            modifier = Modifier.padding(16.dp),
            color = DarkGray // Use the app's background color for the preview surface
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // A single, basic number button
                CalculatorButton(
                    symbol = "8",
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer) // Darker gray
                        .size(65.dp), // Fixed size for consistency with app
                    onClick = { /* Do nothing for preview */ }
                )

                Spacer(Modifier.height(16.dp)) // Add some vertical space

                // A single, basic operation button
                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier
                        .background(Orange) // Orange color
                        .size(65.dp), // Fixed size for consistency with app
                    onClick = { /* Do nothing for preview */ }
                )
            }
        }
    }
}