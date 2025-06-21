# BillSplitter

A cross-platform mobile application built with Kotlin Multiplatform and Jetpack Compose to easily split bills among friends, roommates, or any group of people. The app helps calculate individual amounts including tax and tip, providing a fair and transparent way to share expenses.

## Features

- **Add Multiple People**: Add and remove people who are sharing the bill
- **Add Bill Items**: Add individual items with descriptions and amounts
- **Selective Sharing**: Choose which people share each item
- **Tip Calculation**: Set a tip percentage to be fairly distributed
- **Tax Management**: Add tax amount to be proportionally split
- **Real-time Calculations**: Instantly see how much each person owes
- **Reset Functionality**: Start over with a fresh bill when needed

## Architecture

The application follows Clean Architecture principles with a clear separation of concerns:

### Presentation Layer
- **UI**: Jetpack Compose UI components in `presentation/ui`
- **ViewModel**: Manages UI state and business logic in `presentation/viewmodel`
- **UI State**: Represents the current state of the UI in `presentation/model`

### Domain Layer
- **Use Cases**: Business logic operations in `domain/usecase`
- **Models**: Core data models in `domain/model`
- **Repository Interface**: Data access abstraction in `domain/repository`

### Data Layer
- **Repository Implementation**: Implements data operations in `data/repository`

## Project Structure

* `/composeApp` - Shared code across platforms using Compose Multiplatform
  * `commonMain` - Code shared across all platforms
  * `androidMain` - Android-specific implementations
  * `iosMain` - iOS-specific implementations

* `/iosApp` - iOS application entry point

## Key Components

### Models
- **Person**: Represents an individual sharing the bill
- **BillItem**: Represents an item on the bill with description, amount, and who's sharing it
- **BillSplit**: Core model that calculates totals and individual amounts

### UI Components
- **BillSplitScreen**: Main screen of the application
- **AddPeopleSection**: UI for adding people to the bill
- **AddItemSection**: UI for adding items to the bill
- **PersonCard**: Displays a person and their amount
- **ItemCard**: Displays a bill item and who's sharing it
- **TipAndTaxSection**: UI for setting tip percentage and tax amount
- **BillSummary**: Displays the bill totals

## Technology Stack

- **Kotlin Multiplatform**: For sharing code across platforms
- **Jetpack Compose**: For building the UI
- **Coroutines & Flow**: For asynchronous operations
- **Material 3**: For modern UI components

## Getting Started

### Prerequisites
- Intellij IDEA or Android Studio
- Xcode 16.4 or newer (for iOS development)
- JDK 17 or newer

### Building the Project

#### Android
```
./gradlew :composeApp:assembleDebug
```

#### iOS
```
./gradlew :composeApp:embedAndSignAppleFrameworkForXcode
```
Then open the Xcode project in the `iosApp` directory and run it.

## Learn More

- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)