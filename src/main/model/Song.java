package model;

public class Song {
    private String title;
    private int length;
    private Artist artist;
    private Album album;
    private String genre;

    public Song(String title, int length, Artist artist, Album album, String genre) {
        this.title = title;
        this.length = length;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
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

    // EFFECTS: returns song album
    public Album getAlbum() {
        return album;
    }

    // EFFECTS: returns song genre
    public String getGenre() {
        return genre;
    }
}
