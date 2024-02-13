package model;

import java.util.ArrayList;

public class Playlist {
    private ArrayList<Song> playlist;

    // Creates a playlist with no songs
    public Playlist() {
        this.playlist = new ArrayList<>();
    }

    // REQUIRES: song is not already in playlist
    // MODIFIES: this
    // EFFECTS: adds song to end playlist
    public void addSong(Song song) {
        playlist.add(song);
    }

    // REQUIRES: song is currently in playlist
    // MODIFIES: this
    // EFFECTS: removes song from playlist
    public void removeSong(Song song) {
        playlist.remove(song);
    }

    // EFFECTS: returns number of songs currently in playlist
    public int getNumSongs() {
        return playlist.size();
    }

    // EFFECTS: returns length of playlist, in minutes
    public int getLengthMins() {
        int i = 0;
        int length = 0;
        while (i <= playlist.size()) {
            length = length + playlist.get(i).getLength();
            i = i++;
        }
        return length;
    }

    // EFFECTS: return playlist
    public ArrayList<Song> getPlaylist() {
        return playlist;
    }
}
