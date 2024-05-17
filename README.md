# Word Dictionary App

Welcome to the Word Dictionary App! This application provides an interface to view and manage a list of words with their definitions, fetched from a REST API. Below you will find all necessary information to get started with the project.

## API

The root of the REST API used is `https://www.javiercarrasco.es/api/words/`. It has only one endpoint:

- **read**: This endpoint returns a list of words with their definitions and a word identifier.

## Application Description

### Features

1. **Custom Application Icon**: Add a custom icon to the application that fits its theme.

2. **Single-Activity Application**: The app is designed as a single-activity application.

3. **MaterialToolbar**: The toolbar is always visible and contains:
   - A menu option to toggle the sorting order of the words between A-Z and Z-A.
   - A button to show a random word and its definition in a dialog.

4. **RecyclerView**: Displays a list of words. Each item (defined in `item_word.xml`) shows:
   - The word.
   - An icon to mark it as a favorite.

5. **Word Dialog**: Tapping on any word shows its definition in a dialog.

6. **BottomNavigationView**: Located at the bottom of the activity, it includes:
   - **All Words**: Refreshes and displays the complete list of words fetched from the API.
   - **Favorites**: Displays only the words marked as favorites. If a word is unmarked as a favorite, it disappears from this view.

7. **SwipeRefreshLayout**: Allows manual refreshing of the word list from the API, available only in the "All Words" view.

### User Interface Components

- **MaterialToolbar**: Always visible, includes sorting and random word features.
- **RecyclerView**: Displays the list of words.
- **BottomNavigationView**: Allows navigation between "All Words" and "Favorites" views.
- **SwipeRefreshLayout**: Enables refreshing the word list.

### Functionalities

1. **Sorting**: Toggle the order of words between A-Z and Z-A.
2. **Random Word**: Display a random word and its definition from the current list (all words or favorites).
3. **Favorite Management**: Mark/unmark words as favorites. The favorites view updates dynamically.
4. **Data Refresh**: Refresh the list of words from the API when in the "All Words" view.

## Installation and Setup

1. Clone the repository.
2. Open the project in your preferred Android development environment.
3. Ensure you have an active internet connection for fetching data from the API.
4. Build and run the project on an Android device or emulator.

## Usage

- **Navigation**: Use the BottomNavigationView to switch between all words and favorites.
- **Sorting**: Use the menu in the MaterialToolbar to toggle sorting.
- **Refresh**: Swipe down to refresh the list when viewing all words.
- **Random Word**: Use the toolbar button to view a random word and its definition.
- **Favorites**: Tap the star icon on any word to mark/unmark it as a favorite.

## Screenshots

![image](https://github.com/Poganutrox/WordDictionary/assets/63597815/d757c1b1-63c8-41bf-89cb-c69f2e379567)

![image](https://github.com/Poganutrox/WordDictionary/assets/63597815/3909e907-be69-4c71-a173-f1d8f23f0a4e)

![image](https://github.com/Poganutrox/WordDictionary/assets/63597815/f96a7b7a-1f05-4bc5-a658-3cda9d88d12b)



