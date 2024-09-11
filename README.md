# AidRay: First Aid Chatbot

## Overview

AidRay is a first aid chatbot application developed for Android. It provides users with emergency first aid information based on their input. The chatbot uses a predefined JSON file containing intents, patterns, and responses to generate relevant answers. The app operates entirely offline, making it accessible in any situation. This tool is especially useful for college and school students, providing them with quick and reliable first aid guidance right at their fingertips.
AidRay is designed to assist college and school students by providing easy access to first aid information. It's a practical tool for learning and applying first aid knowledge in emergencies, making it an excellent resource for educational settings.

## Features

- **First Aid Assistance**: Get instant first aid advice based on user input.
- **Offline Functionality**: Operates completely offline using a local `intents.json` file.
- **Multi-language Support**: Includes English, Tamil, and Hindi languages for a more personalized experience.
- **Emergency Call**: Provides an option to call emergency services directly from the app.
## Welcome Page
<img src="https://github.com/Nidharshana0325/AidRay/blob/main/welcome%20page.jpg?raw=true" alt="Welcome Page" width="45%" style="display:inline-block; margin:10px;"/>

## Language Options
<img src="https://github.com/Nidharshana0325/AidRay/blob/main/language.jpg?raw=true" alt="Language Options" width="45%" style="display:inline-block; margin:10px;"/>

## Hindi
<img src="https://github.com/Nidharshana0325/AidRay/blob/main/hindi.jpg?raw=true" alt="Hindi" width="45%" style="display:inline-block; margin:10px;"/>

## English
<img src="https://github.com/Nidharshana0325/AidRay/blob/main/english.jpg?raw=true" alt="English" width="45%" style="display:inline-block; margin:10px;"/>

## Prerequisites

- **Android Studio**: For building and running the app.
- **Java 8 or higher**: Ensure you have Java 8 or a newer version installed.

## Installation

1. **Clone the Repository**
    ```bash
    git clone https://github.com/your-username/aidray-chatbot.git
    cd aidray-chatbot
    ```

2. **Open the Project**
    - Open Android Studio.
    - Select `File` > `Open` and navigate to the cloned repository.

3. **Sync Gradle**
    - Click on `Sync Project with Gradle Files` to ensure all dependencies are resolved.

4. **Run the App**
    - Connect an Android device or start an emulator.
    - Click on the `Run` button in Android Studio.

## Usage

- **Welcome Screen**
    - Choose your preferred language from the language selection screen.
    - Tap on "Start Chat" to begin interacting with the chatbot.

- **Chat Interface**
    - Type your query related to first aid into the input field and tap "Send."
    - The chatbot will provide responses based on the patterns defined in `intents.json`.

- **Emergency Call**
    - Tap on the "Emergency Call" button to initiate a call to emergency services.

## File Structure

- **`app/src/main/assets/intents.json`**: Contains the intents, patterns, and responses used by the chatbot.
- **`app/src/main/res/layout/activity_chat.xml`**: Layout for the chat interface.
- **`app/src/main/res/layout/activity_main.xml`**: Layout for the main welcome screen.
- **`app/src/main/java/com/example/aidray_bot/ChatActivity.java`**: Java class handling chat interactions.
- **`app/src/main/java/com/example/aidray_bot/MainActivity.java`**: Java class for the welcome screen and language selection.


## Acknowledgments

- Thanks to [Android Developers](https://developer.android.com/) for providing the tools and resources to build Android apps.


