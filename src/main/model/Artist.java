package model;

import java.util.ArrayList;

public class Artist {
    private String name;
    private ArrayList<Album> albums;
    private String genre;

    public Artist(String name, ArrayList<Album> albums, String genre) {
        this.name = name;
        this.albums = albums;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public String getGenre() {
        return genre;
    }
}
