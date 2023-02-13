package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogBookTest {
    private LogItem l1;
    private LogItem l2;
    private Category c1;
    private Category c2;
    private Category c3;
    private Category c4;

    private LogBook lb1;

    @BeforeEach
    void runBefore() {
        c1 = new Category("Salary");
        c2 = new Category("Shopping");
        c3 = new Category("Grocery");
        c4 = new Category("Food");

        l1 = new LogItem(LocalDate.of(2023, 1, 1), -25, c2);
        l2 = new LogItem(LocalDate.of(2023, 1, 2), 1000, c1);

        lb1 = new LogBook();


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
}

