package persistence;


import model.LogEntry;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEntry(LocalDate date, Double amount, String category, LogEntry logEntry) {
        assertEquals(date, logEntry.getDate());
        assertEquals(category, logEntry.getCategory());
        assertEquals(amount,logEntry.getAmount());
    }
}
