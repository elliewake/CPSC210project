package persistence;

import model.Artist;
import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSong(String title, int length, Artist artist, Song song) {
        assertEquals(title, song.getTitle());
        assertEquals(length, song.getLength());
        assertEquals(artist, song.getArtist());
    }
}
