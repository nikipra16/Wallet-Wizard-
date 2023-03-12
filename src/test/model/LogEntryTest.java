package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LogEntryTest {
    LogEntry l1;
    LogEntry l2;

    @BeforeEach
    void runBefore() {

        l1 = new LogEntry(LocalDate.of(2023,2,1),0,"Salary");
        l2 = new LogEntry(LocalDate.of(2023, 1, 2),1000,"Salary");
    }

    @Test
    public void notYetCategorized() {
        assertEquals("Not Categorized",l1.getCategory());
    }

    @Test
    public void justCategorized() {
        assertEquals("Not Categorized",l1.getCategory());
        l1.setCategory("Salary");
        assertEquals("Salary",l1.getCategory());
    }

    @Test
    public void changeCategoryToDifferent() {
        l2.setCategory("Food");
        assertEquals("Food",l2.getCategory());
    }

    @Test
    public void removeCategory() {
        l2.setCategory("Salary");
        assertEquals("Salary",l2.getCategory());
        l2.changeCategory("Salary");
        assertEquals("Not Categorized",l2.getCategory());
    }

    @Test
    public void changeCategory() {
        l2.setCategory("Salary");
        assertEquals("Salary",l2.getCategory());
        l2.changeCategory("Food");
        assertNotEquals("Salary", "Food");
        assertEquals("Food",l2.getCategory());
    }
    @Test
    public void resetThenChangeTest() {
        l2.setCategory("Salary");
        assertEquals("Salary",l2.getCategory());
        l2.changeCategory("Salary");
        assertEquals("Not Categorized",l2.getCategory());
        l2.setCategory("Grocery");
        assertEquals("Grocery",l2.getCategory());
    }

    @Test
    public void getDateTest() {
        assertEquals(LocalDate.of(2023,2,1),l1.getDate());
    }

    @Test
    public void getAmountTest() {
        assertEquals(0,l1.getAmount());
        assertEquals(1000,l2.getAmount());
    }

    @Test
    public void toStringTest() {
        assertEquals("Date: 2023-02-01  Amount: 0.0  Category: Not Categorized",l1.toString());
    }
}