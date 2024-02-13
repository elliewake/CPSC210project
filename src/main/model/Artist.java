package model;

import java.util.ArrayList;

public class Artist {
    private ArrayList<Album> albums;
    private String genre;

    public Artist(ArrayList<Album> albums, String genre) {
        this.albums = albums;
        this.genre = genre;
    }
}
