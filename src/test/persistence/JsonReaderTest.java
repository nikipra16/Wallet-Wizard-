package persistence;

import model.Category;
import model.LogEntry;
import model.Book;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Book logBook = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBook.json");
        try {
            Book logBook = reader.read();
            assertEquals("My work room", logBook.getName());
            assertEquals(0, logBook.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBook.json");
        try {
            Book logBook = reader.read();
            assertEquals("", logBook.getName());
            List<LogEntry> logEntries = logBook.getEntries();
            assertEquals(2, logEntries.size());
//            checkEntry(LocalDate.of());
//            checkEntry("saw", Category.WOODWORK, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
