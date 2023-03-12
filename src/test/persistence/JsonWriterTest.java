package persistence;


import model.Category;
import model.LogEntry;
import model.Book;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Book logBook = new Book("My work room");
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
            Book logBook = new Book("My Book");
//            Budget budget = new Budget(Month.FEBRUARY);
//            budget.setMonthlyBudget(100,Month.FEBRUARY);
            Category category = new Category("Food");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBook.json");
            writer.open();
            writer.write(logBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBook.json");
            logBook = reader.readBook();
            assertEquals("My Book", logBook.getName());
            assertEquals(0, logBook.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        Category c1 = new Category("Salary");
        Category c2 = new Category("Food");

        try {
            Book logBook = new Book("Book");
//            Budget budget = new Budget(Month.FEBRUARY);
//            budget.setMonthlyBudget(100,Month.FEBRUARY);
            Category category = new Category("Food");
            LogEntry l1 = new LogEntry(LocalDate.of(2022, 02, 02), 1000,c1);
            l1.setCategory(c1);
            LogEntry l2 = new LogEntry(LocalDate.of(2022, 04, 03), -200,c2);
            l2.setCategory(c2);
            logBook.addLog(l1);
            logBook.addLog(l2);
            JsonWriter writer = new JsonWriter("./data/testWriterBook.json");
            writer.open();
            writer.write(logBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterBook.json");
            logBook = reader.readBook();
            assertEquals("Book", logBook.getName());
            logBook.getEntries();
            List<LogEntry> logEntries = logBook.getEntries();
            assertEquals(2, logEntries.size());
            checkEntry(LocalDate.of(2022,02,02), 1000.0,c1.getCategoryName(), logEntries.get(0));
            checkEntry((LocalDate.of(2022,04,03)), -200.0,c2.getCategoryName(), logEntries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}