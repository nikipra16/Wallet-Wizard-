package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LogItemTest {
    LogItem l1;
    LogItem l2;
    Category c1;
    Category c2;
    Category c3;
    Category c4;

    @BeforeEach
    void runBefore() {
        c1 = new Category("Salary");
        c2 = new Category("Shopping");
        c3 = new Category("Grocery");
        c4 = new Category("Food");

        l1 = new LogItem(LocalDate.of(2023,1,1),0,new Category("Not Categorized"));
        l2 = new LogItem(LocalDate.of(2023, 1, 2),1000,c1);
    }

    @Test
    public void notYetCategorized() {
        assertEquals("Not Categorized",l1.getCategory().getCategoryName());
    }

    @Test
    public void justCategorized() {
        assertEquals("Not Categorized",l1.getCategory().getCategoryName());
        l1.setCategory(c1);
        assertEquals(c1,l1.getCategory());
    }

    @Test
    public void changeCategoryToDifferent() {
        l2.setCategory(c4);
        assertEquals("Food",l2.getCategory().getCategoryName());
    }

    @Test
    public void removeCategory() {
        l2.setCategory(c1);
        assertEquals(c1,l2.getCategory());
        l2.changeCategory(c1);
        assertEquals("Not Categorized",l2.getCategory().getCategoryName());
    }

    @Test
    public void changeCategory() {
        l2.setCategory(c1);
        assertEquals(c1,l2.getCategory());
        l2.changeCategory(c2);
        assertEquals("Shopping",l2.getCategory().getCategoryName());
        assertEquals(c2,l2.getCategory());
    }

    @Test
    public void getDateTest() {
        assertEquals(LocalDate.of(2023,1,1),l1.getDate());
    }

    @Test
    public void getAmountTest() {
        assertEquals(0,l1.getAmount());
        assertEquals(1000,l2.getAmount());
    }
}