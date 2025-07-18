package com.jjventures.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jjventures.calculator.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Get the ViewModel instance. viewModel() automatically handles creation and retention.
                    val viewModel = viewModel<CalculatorViewModel>()
                    // Observe the state from the ViewModel
                    val state = viewModel.state
                    // Pass the state and the onAction event handler to the CalculatorScreen
                    CalculatorScreen(
                        state = state,
                        onAction = viewModel::onAction // Updated to CalculatorActions
                    )
                }
            }
        }
    }
}