package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private LogEntry l1;
    private LogEntry l2;
    private LogEntry l3;



    private Book lb1;

    @BeforeEach
    void runBefore() {
        l1 = new LogEntry(LocalDate.of(2023, 3, 1), -25,"Food");
        l2 = new LogEntry(LocalDate.of(2023, 1, 2), 1000,"Salary");
        l3 = new LogEntry(LocalDate.of(2019, 1, 2), 100,"Salary");

        lb1 = new Book("Nikita's Book");


    }

    @Test
    public void constructorTest() {
        assertEquals(0, lb1.size());
    }

    @Test
    public void addLogTest() {
        lb1.addLog(l2);
        assertEquals(1,lb1.size());
    }

    @Test
    public void removeLogTest() {
        lb1.addLog(l2);
        lb1.addLog(l1);
        lb1.removeLog(l2);
        assertTrue(lb1.containsLog(l1));
        assertFalse(lb1.containsLog(l2));

    }

    @Test
    public void totalAmountLeftTest() {
        lb1.addLog(l2);
        lb1.addLog(l1);
        assertEquals(975,lb1.totalAmountLeft());
    }

    @Test
    public void totalAmountLeftDifferentYearTest() {
        lb1.addLog(l2);
        lb1.addLog(l3);
        assertEquals(1000,lb1.totalAmountLeft());
    }

    @Test
    public void monthlySpendingTest() {
        lb1.addLog(l2);
        lb1.addLog(l2);
        lb1.addLog(l1);
        lb1.addLog(l3);
        assertEquals(4,lb1.size());
        assertTrue(lb1.containsLog(l1));
        assertEquals(2000,lb1.monthlyExpenditure(YearMonth.of(2023,1)));
    }
}

