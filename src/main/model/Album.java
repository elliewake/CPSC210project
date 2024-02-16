package model;

import java.util.ArrayList;

public class Album {
    private ArrayList<Song> tracklist;
    private int releaseYear;
    private int numSongs;
    private int length;

    public Album(ArrayList<Song> tracklist, int releaseYear, int numSongs, int length) {
        this.tracklist = tracklist;
        this.releaseYear = releaseYear;
        this.numSongs = numSongs;
        this.length = length;
    }

    // EFFECTS: return tracklist
    public ArrayList<Song> getTracklist() {
        return tracklist;
    }

    // EFFECTS: return release date
    public int getReleaseYear() {
        return releaseYear;
    }

    // EFFECTS: return number of songs on album
    public int getNumSongs() {
        return numSongs;
    }

    // EFFECTS: return length of album, in minutes
    public int getLength() {
        return length;
    }
}
