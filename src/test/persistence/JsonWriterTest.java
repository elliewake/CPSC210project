package persistence;

import model.Artist;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Playlist p = new Playlist("Test playlist");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Playlist p = new Playlist("Test playlist");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlaylist.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlaylist.json");
            p = reader.read();
            assertEquals("Test playlist", p.getName());
            assertEquals(0, p.getNumSongs());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Playlist p = new Playlist("Test playlist");
            p.addSong(new Song("Visions of Johanna", 4, Artist.BobDylan));
            p.addSong(new Song("Hey", 5, Artist.Pixies));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlaylist.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlaylist.json");
            p = reader.read();
            assertEquals("Test playlist", p.getName());
            List<Song> songs = p.getPlaylist();
            assertEquals(2, songs.size());
            checkSong("Visions of Johanna", 4, Artist.BobDylan, songs.get(0));
            checkSong("Hey", 5, Artist.Pixies, songs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
