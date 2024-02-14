package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongTest {
    private Song comeTogether;
    private Song something;
    private Artist theBeatles;
    private Album abbeyRoad;
    private ArrayList<Album> beatlesAlbums;
    private ArrayList<Song> abbeyRoadSongs;

    @BeforeEach
    public void runBefore() {
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
    public void testGetTitle() {
        assertEquals(something.getTitle(), "Something");
    }

    @Test
    public void testGetLength() {
        assertEquals(something.getLength(), 3);
    }
}
