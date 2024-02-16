package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class PlaylistTest {
    private Playlist testPlaylist;
    private Song comeTogether;
    private Song something;
    private Artist theBeatles;
    private Album abbeyRoad;
    private ArrayList<Album> beatlesAlbums;
    private ArrayList<Song> abbeyRoadSongs;

    @BeforeEach
    public void runBefore() {
        testPlaylist = new Playlist();
        comeTogether = new Song("Come Together", 4, theBeatles, abbeyRoad, "Rock");
        something = new Song("Something", 3, theBeatles, abbeyRoad, "Rock");
        beatlesAlbums = new ArrayList<>();
        beatlesAlbums.add(abbeyRoad);
        abbeyRoadSongs = new ArrayList<>();
        abbeyRoadSongs.add(comeTogether);
        abbeyRoadSongs.add(something);
        abbeyRoad = new Album(abbeyRoadSongs, 1969, 17, 47);
        theBeatles = new Artist("The Beatles", beatlesAlbums, "Rock");
    }

    @Test
    public void testAddSong() {
        testPlaylist.addSong(comeTogether);
        assertEquals(testPlaylist.getNumSongs(), 1);
        testPlaylist.addSong(something);
        assertEquals(testPlaylist.getNumSongs(), 2);
    }

    @Test
    public void testRemoveSong() {
        testPlaylist.addSong(comeTogether);
        assertEquals(testPlaylist.getNumSongs(), 1);
        testPlaylist.removeSong(comeTogether);
        assertEquals(testPlaylist.getNumSongs(), 0);
    }

    @Test
    public void testGetLength() {
        testPlaylist.addSong(something);
        testPlaylist.addSong(comeTogether);
        assertEquals(testPlaylist.getLengthMins(), 7);
    }

    @Test
    public void testGetPlaylist() {
        testPlaylist.addSong(something);
        testPlaylist.addSong(comeTogether);
        assertEquals(testPlaylist.getPlaylist().get(1), comeTogether);
    }
}