# Android Calculator App

A modern, feature-rich, and responsive calculator application built using Kotlin and Jetpack Compose. This project showcases best practices in Android UI development, including MVVM architecture, robust state management, and adaptive layouts for a seamless user experience across different device orientations.

## Table of Contents

-   [Features](#features)
-   [Technologies Used](#technologies-used)
-   [Architecture](#architecture)
-   [Setup and Installation](#setup-and-installation)
-   [How to Run](#how-to-run)
-   [Screenshots](#screenshots)
-   [Future Enhancements](#future-enhancements)
-   [License](#license)
-   [Contact](#contact)

## Features

This calculator app provides a comprehensive set of functionalities with a focus on user experience:

* **Basic Arithmetic Operations**: Perform fundamental calculations including Addition (`+`), Subtraction (`-`), Multiplication (`X`), and Division (`÷`).
* **Percentage Calculation**: Full support for percentage operations.
    * `X%`: Calculates X divided by 100 (e.g., `50%` results in `0.5`).
    * `A + B%`: Calculates B as a percentage of A, then performs the operation (e.g., `100 + 10%` calculates `10% of 100` (which is `10`), then `100 + 10 = 110`).
* **Comprehensive Number Input**: Digits `0-9`, decimal point (`.`), and a dedicated double-zero (`00`) button for efficient double-zero entry. Initial zeros are automatically handled (e.g., `08` becomes `8`).
* **Clear Functionality**:
    * `C` button: Clears the entire calculation, resetting the display and internal state.
    * `DEL` (Backspace) button: Deletes the last entered digit or operator, allowing step-by-step correction.
* **Sign Change (`+/-`)**: Toggles the sign of the currently active number (from positive to negative and vice-versa).
* **Two-Line Display**: Features a clear display area with:
    * A **history line** showing the ongoing calculation (e.g., `123+`), with smaller, faded text.
    * A **main input/result line** showing the current number being typed or the final result with a larger font size.
* **Horizontal Scrolling Display**: For very long numbers, the main display automatically enables horizontal scrolling, ensuring the entire number is visible without truncation.
* **Fixed Button Sizes**: All calculator buttons maintain a consistent, fixed size in `dp` units, ensuring a uniform and aesthetically pleasing grid regardless of screen dimensions or content.
* **Keypad Fixed at Bottom**: The entire button grid is anchored firmly to the bottom of the screen, providing a stable and predictable layout.
* **Responsive UI**: The layout intelligently adapts between portrait and landscape orientations using `LocalConfiguration`, ensuring optimal usability and visual appeal on various devices.
* **Modern UI/UX**: Implements a sleek dark theme with uniformly sized, rounded buttons for a clean, intuitive, and professional look.
* **Custom Splash Screen**: A custom calculator image is displayed immediately upon app launch, enhancing perceived startup speed and providing a branded experience.

## Technologies Used

* **Kotlin**: The primary programming language for Android development.
* **Jetpack Compose**: Android's modern, declarative UI toolkit for building native user interfaces.
* **Android Architecture Components**:
    * **ViewModel**: Manages UI-related data in a lifecycle-conscious way, surviving configuration changes.
    * **State Management**: Utilizes Compose's observable state (`mutableStateOf`) for reactive UI updates.
* **Material Design 3**: For consistent and modern UI components, color schemes, and typography.
* **AndroidX Core Splash Screen API**: For implementing a modern, customizable splash screen.

## Architecture

The app adheres to the **MVVM (Model-View-ViewModel)** architectural pattern, promoting a clear separation of concerns:

* **Model**: Defined by `CalculatorState` (representing the UI's current data) and `CalculatorOperation` (defining arithmetic types).
* **View (`CalculatorScreen.kt`)**: The Composable UI layer. It observes changes in `CalculatorState` and dispatches user interactions as `CalculatorActions` to the ViewModel. It's responsible solely for rendering the UI.
* **ViewModel (`CalculatorViewModel.kt`)**: The "brain" of the application. It holds and manages the `CalculatorState`, processes incoming `CalculatorActions`, performs all business logic (calculations, input handling, formatting), and updates the state.

This architecture ensures the codebase is modular, testable, and maintainable, making it easier to scale and debug.

## Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/joeljoby5/Calculator.git](https://github.com/joeljoby5/Calculator.git)
    cd Calculator
    ```

2.  **Open in Android Studio:**
    * Launch Android Studio.
    * Select `Open an existing Android Studio project`.
    * Navigate to the cloned `Calculator` directory and click `Open`.

3.  **Sync Gradle:**
    * Android Studio will automatically try to sync the Gradle files. If it doesn't, click `File` > `Sync Project with Gradle Files`.
    * Ensure all necessary dependencies (including `androidx.core:core-splashscreen`) are correctly referenced in `libs.versions.toml` and applied in `app/build.gradle.kts`.

## How to Run

1.  **Connect an Android Device:** Ensure USB debugging is enabled on your device.
2.  **Start an Emulator:** Create and launch an Android Virtual Device (AVD) using the AVD Manager.
3.  **Run the App:** Click the `Run` button (green triangle) in the Android Studio toolbar.

The app will be installed and launched on your selected device or emulator, starting with your custom splash screen.

## Screenshots
![Image1](https://github.com/user-attachments/assets/744da166-2dcf-4d70-97c3-1f152081c983)   ![Image2](https://github.com/user-attachments/assets/9e16809a-b2f5-4157-8214-d597689849f4)


---

## Future Enhancements

* **Operator Precedence**: Enhance calculation logic to respect mathematical operator precedence (e.g., multiplication/division before addition/subtraction).
* **Parentheses Support**: Add functionality to handle expressions with parentheses.
* **Advanced Functions**: Implement scientific calculator features (e.g., trigonometric functions, logarithms, square roots).
* **History Log**: Implement a persistent log of previous calculations.
* **Theme Customization**: Allow users to select different themes or accent colors from within the app settings.
* **Error Handling Refinements**: More descriptive error messages and improved error recovery.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions, feedback, or collaborations, feel free to reach out:

* **Joel Joby**
* **GitHub**: [https://github.com/joeljoby5](https://github.com/joeljoby5)
* **Email**: [joeljoby724@gmail.com](mailto:joeljoby724@gmail.com)
