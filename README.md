### Cat Breeds App

### Architecture
The app follows the MVVM (Model-View-ViewModel) architecture, ensuring separation of concerns and a maintainable codebase:

Model: Represents the data models.
ViewModel: Manages UI-related data and business logic in a lifecycle-aware manner.
View (Jetpack Compose): The UI layer is entirely built using Jetpack Compose.
Repository: Acts as an intermediary between the ViewModel and the data sources (remote and local storage)

### Used Libraries 
* Kotlin
* Jetpack Compose for ui.
* Navigation Component
* Hilt for Dependency Injection
* Retrofit for network communication
* Room Database for local persistence and offline support
* Kotlin Coroutines & Flow
* MockK,JUnit5 for testing
* Glide for loading images.

### Key Implementations
* Offline Support: Implemented using Room.
* Error Handling
* State Management: Using StateFlow.
* Dependency Injection: Using Hilt.

### Testing Strategy
* Unit Tests: Test business logic using JUnit and MockK.
* Instrumented Tests: Tests for the Room Database.
* UI Tests: Verify the behavior of the UI using Espresso and Compose Test.

