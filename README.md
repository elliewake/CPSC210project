# Playlist Maker

## Create, edit, and explore a playlist

This application will allow a user to create a playlist of arbitrary length. A user will be able to add songs to the 
playlist, with information stored about each song.

This application will be used by music enthusiasts, or anybody who wants to create a playlist to keep together songs 
they enjoy listening to. This project is of interest to me because I enjoy listening to music, creating playlists, and 
keeping track of information related to songs and artists.

**Class descriptions:**

Playlist:
- List of songs
- Length (# of songs)
- Length (in minutes)

Song features:
- Title
- Length (in minutes)
- Artist

Artist class:
- Four possible artists (The Beatles, Rolling Stones, Pixies, Bob Dylan)


## User Stories:

- As a user, I want to be able to add a song to my playlist
- As a user, I want to view my playlist
- As a user, I want to be able to remove a song from my playlist
- As a user, I want to be able to view a list of the songs in my playlist
- As a user, I want to be able to view the length of my playlist, in minutes

- As a user, I want to be able to choose to save my playlist when I decide to quit the application
- As a user, I want to be able to choose to load my playlist from file when I start the application

## Instructions for Grader
1. Generate first required action, "adding multiple Songs to Playlist" by clicking "Add Song" button
2. Generate second required action, "adding multiple Songs to Playlist" by selecting a song, then clicking "
Remove Song" button
3. My visual component is the Abbey Road image which is displayed to the right of the playlist panel for the duration
of the time the application is open
4. Save the state of the application by clicking "Save playlist" button
5. Reload the state of the application by closing the application, restarting, then clicking "Load playlist" button

## Phase 4: Task 2
*Sample of logged events, shown in form of quitting message:*

Quitting playlist app.

Tue Apr 02 20:28:03 PDT 2024
Added song: GoldenSlumbers.

Tue Apr 02 20:28:13 PDT 2024
Added song: Hey.

Tue Apr 02 20:28:24 PDT 2024
Removed song: GoldenSlumbers.

## Phase 4: Task 3
There are a number of ways in which my application could be refactored in order to improve the design.
First, I would split the PlaylistApp class into multiple subclasses. This would greatly increase cohesion and 
make the code easier to read. 

Another change I would consider making is implementing a Singleton design pattern applied to the Playlist class. 
This would make it so that there can only ever be one instance of a Playlist, which may simplify the design and improve 
clarity. However, this would be a tradeoff as there could not be multiple users each with their own distinct Playlist.

*Citations:*

- *JsonSerializationDemo used as reference for Json classes and method implementation.*
- *ListDemo from
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
used as reference for GUI*
- *Event and EventLog classes, LogPrinter interface, and LogException class from AlarmSystem application*

