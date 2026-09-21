# CST438 Project1

An Android app built with Kotlin and Jetpack Compose that lets users create an account, search for artists via the [Last.fm](https://www.last.fm/api) API, and view artist details such as their top album, bio, and tags.

## Features

- **Onboarding**: landing, login, and sign-up screens with account creation
- **Artist search**: query the Last.fm API for artists and view search results
- **Artist details**: top album artwork, name, bio excerpt, and top tags for a selected artist
- **Profile customization**: pick a profile picture from a set of preset avatars
- **Settings**: toggle dark mode, change password, and delete account
- **Local persistence**: user accounts stored on-device with Room

## Tech Stack

- [Kotlin](https://kotlinlang.org/)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) for in-app navigation
- [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/) for networking with the Last.fm API
- [Room](https://developer.android.com/training/data-storage/room) for local user data storage
- [Coil](https://coil-kt.github.io/coil/) for image loading
- [Detekt](https://detekt.dev/) for static code analysis

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (recent stable version)
- Android SDK with `compileSdk = 36` / `minSdk = 34`
- A [Last.fm API account](https://www.last.fm/api/account/create) (free) to obtain an API key and secret

### Setup

1. Clone the repository:
   ```bash
   git clone git@github.com:JormanGuadarrama/cst438project1.git
   cd cst438project1
   ```
2. Create a `local.properties` file in the project root (this file is git-ignored) with your Android SDK location and Last.fm credentials:
   ```properties
   sdk.dir=/path/to/your/Android/Sdk
   LASTFM_API_KEY=your_api_key
   LASTFM_API_SECRET=your_api_secret
   ```
3. Open the project in Android Studio and let Gradle sync, or build from the command line:
   ```bash
   ./gradlew assembleDebug
   ```
4. Run the app on an emulator or physical device from Android Studio, or install it directly:
   ```bash
   ./gradlew installDebug
   ```

## Testing

Run unit tests:
```bash
./gradlew test
```

Run instrumented UI tests (requires a connected device or emulator):
```bash
./gradlew connectedAndroidTest
```

Run static analysis:
```bash
./gradlew detekt
```
