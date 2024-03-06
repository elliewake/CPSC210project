package ui;

import model.Artist;
import model.Playlist;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlaylistApp {
    private Scanner input;
    private Playlist playlist;

    private static final String JSON_STORE = "./data/playlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public PlaylistApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        playlist = new Playlist("User playlist");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPlaylist();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPlaylist() {
        boolean keepGoing = true;
        String key;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            key = input.next().toLowerCase();

            if (key.equals("q")) {
                keepGoing = false;
            } else {
                processKey(key);
            }
        }

        System.out.println("\nQuitting playlist app. Goodbye!!");
    }

    private void displayMenu() {
        System.out.println("\nSelect what you would like to do:");
        System.out.println("\t+ -> add song to playlist");
        System.out.println("\tr -> remove song from playlist");
        System.out.println("\tv -> view current playlist");
        System.out.println("\ts -> save playlist to file");
        System.out.println("\tl -> load playlist from file");
        System.out.println("\tq -> quit");
    }

    private void processKey(String input) {
        if (input.equals("+")) {
            addNewSong();
        } else if (input.equals("r")) {
            removeSong();
        } else if (input.equals("v")) {
            viewPlaylist();
        } else if (input.equals("s")) {
            savePlaylist();
        } else if (input.equals("l")) {
            loadPlaylist();
        } else {
            System.out.println("Please select a key from above");
        }
    }

    private Artist getArtist() {
        System.out.println("Please choose the artist of your song");

        int menuLabel = 1;
        for (Artist a : Artist.values()) {
            System.out.println(menuLabel + ": " + a);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return Artist.values()[menuSelection - 1];
    }

    private int getLength() {
        System.out.println("Please enter length of song, in minutes");

        // Loop until a valid integer is entered
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer for the length.");
            // Consume the invalid input to avoid an infinite loop
            input.next();
        }

        return input.nextInt();
    }

    private void addNewSong() {
        Artist artist = getArtist();
        int length = getLength();

        System.out.println("Please enter song title");
        String title = input.next();
        playlist.addSong(new Song(title, length, artist));
    }

    private void removeSong() {
        System.out.println("Please select which song you wish to remove:");

        int i = 1;
        for (Song s : playlist.getPlaylist()) {
            System.out.println(i + ": " + s);
            i++;
        }

        int songToRemove = input.nextInt();
        playlist.removeSong(playlist.getPlaylist().get(songToRemove - 1));
    }

    private void viewPlaylist() {
        System.out.println("Current Playlist:");
        ArrayList<Song> songs = playlist.getPlaylist();

        for (Song song : songs) {
            System.out.println(song.getTitle() + " by " + song.getArtist());
        }

        System.out.println("Your playlist is " + playlist.getLengthMins() + " minutes long.");
    }

    // EFFECTS: saves the playlist to file
    private void savePlaylist() {
        try {
            jsonWriter.open();
            jsonWriter.write(playlist);
            jsonWriter.close();
            System.out.println("Saved " + playlist.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads playlist from file
    private void loadPlaylist() {
        try {
            playlist = jsonReader.read();
            System.out.println("Loaded " + playlist.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
