package persistence;



import model.LogEntry;
import model.Book;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Book logBook = reader.readBook();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBook.json");
        try {
            Book logBook = reader.readBook();
            assertEquals("My Book", logBook.getName());
            assertEquals(0, logBook.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


//    @Test
//    void testReaderEmptyCategory() {
//        JsonReader reader = new JsonReader("./data/testReaderEmptyCategory.json");
//        try {
//            Category category = reader.readCategory();
//            assertEquals("", category.getCategoryName());
//            assertEquals(0, category.getCategoryLogs().size());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }

    @Test
    void testReaderGeneralBook() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBook.json");
        try {
            Book logBook = reader.readBook();
            assertEquals("", logBook.getName());
            List<LogEntry> logEntries = logBook.getEntries();
            assertEquals(2, logEntries.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

//    @Test
//    void testReaderGeneralBudget() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralBook.json");
//        try {
//            Book logBook = reader.readBook();
//            assertEquals("", logBook.getName());
//            List<LogEntry> logEntries = logBook.getEntries();
//            assertEquals(2, logEntries.size());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
}
