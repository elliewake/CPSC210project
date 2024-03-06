package persistence;

import model.Artist;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Playlist p = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlaylist.json");
        try {
            Playlist p = reader.read();
            assertEquals("Test playlist", p.getName());
            assertEquals(0, p.getNumSongs());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlaylist.json");
        try {
            Playlist p = reader.read();
            assertEquals("Test playlist", p.getName());
            List<Song> songs = p.getPlaylist();
            assertEquals(2, songs.size());
            checkSong("Hey", 5, Artist.Pixies, songs.get(0));
            checkSong("Visions of Johanna", 4, Artist.BobDylan, songs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
