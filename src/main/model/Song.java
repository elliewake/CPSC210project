package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Song with given title, length (in minutes) and Artist
public class Song implements Writable {
    private String title;
    private int length;
    private Artist artist;

    public Song(String title, int length, Artist artist) {
        this.title = title;
        this.length = length;
        this.artist = artist;
    }

    // EFFECTS: returns song title
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns length of song, in minutes
    public int getLength() {
        return length;
    }

    // EFFECTS: returns song artist
    public Artist getArtist() {
        return artist;
    }

    // EFFECTS: returns String representation of this Song
    public String toString() {
        return title + " by " + artist;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("length", length);
        json.put("artist", artist);
        return json;
    }
}
