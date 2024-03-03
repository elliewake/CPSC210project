package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Playlist implements Writable {
    private ArrayList<Song> playlist;
    private String name;

    // Creates a playlist with no songs
    public Playlist(String name) {
        this.playlist = new ArrayList<>();
        this.name = name;
    }


    public String getName() {
        return name;
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
        int length = 0;
        for (Song song : playlist) {
            length += song.getLength();
        }
        return length;
    }

    // EFFECTS: return playlist
    public ArrayList<Song> getPlaylist() {
        return playlist;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("songs", songsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : playlist) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
