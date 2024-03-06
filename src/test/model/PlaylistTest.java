package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Artist.TheBeatles;
import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    private Playlist testPlaylist;
    private Song comeTogether;
    private Song something;

    @BeforeEach
    public void runBefore() {
        testPlaylist = new Playlist("Test");
        comeTogether = new Song("Come Together", 4, TheBeatles);
        something = new Song("Something", 4, TheBeatles);
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
        testPlaylist.addSong(comeTogether);
        testPlaylist.addSong(something);
        assertEquals(testPlaylist.getLengthMins(), 8);
    }

    @Test
    public void testGetPlaylist() {
        testPlaylist.addSong(something);
        testPlaylist.addSong(comeTogether);
        assertEquals(testPlaylist.getPlaylist().get(1), comeTogether);
    }
}