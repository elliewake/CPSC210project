package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlbumTest {
    private Playlist testPlaylist;
    private Song comeTogether;
    private Song something;
    private Artist theBeatles;
    private Album abbeyRoad;
    private ArrayList<Song> abbeyRoadSongs;

    @BeforeEach
    public void runBefore() {
        comeTogether = new Song("Come Together", 4, theBeatles, abbeyRoad, "Rock");
        something = new Song("Something", 3, theBeatles, abbeyRoad, "Rock");
        abbeyRoadSongs = new ArrayList<>();
        abbeyRoadSongs.add(comeTogether);
        abbeyRoadSongs.add(something);
        abbeyRoad = new Album(abbeyRoadSongs, 1969, 17, 47);
    }

    @Test
    public void testGetTracklist() {
        assertEquals(abbeyRoad.getTracklist().get(0), comeTogether);
        assertEquals(abbeyRoad.getTracklist().get(1), something);
    }

    @Test
    public void testGetReleaseYear() {
        assertEquals(abbeyRoad.getReleaseYear(), 1969);
    }

    @Test
    public void testGetNumSongs() {
        assertEquals(abbeyRoad.getNumSongs(), 17);
    }

    @Test
    public void testGetLength() {
        assertEquals(abbeyRoad.getLength(), 47);
    }
}
