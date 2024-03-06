package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Artist.TheBeatles;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongTest {
    private Song comeTogether;

    @BeforeEach
    public void runBefore() {
        comeTogether = new Song("Come Together", 4, TheBeatles);
    }

    @Test
    public void testGetTitle() {
        assertEquals(comeTogether.getTitle(), "Come Together");
    }

    @Test
    public void testGetLength() {
        assertEquals(comeTogether.getLength(), 4);
    }

    @Test
    public void testGetArtist() {
        assertEquals(comeTogether.getArtist(),TheBeatles);
    }

    @Test
    public void testToString() {
        assertEquals(comeTogether.toString(), "Come Together by TheBeatles");
    }

}
