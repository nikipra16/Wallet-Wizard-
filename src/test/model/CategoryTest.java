package model;
import model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    private LogItem l1;
    private LogItem l2;
    private Category c1;
    private Category c2;

    private List<LogItem> c1Items;

    @BeforeEach
    void runBefore() {
        c1 = new Category("Salary");
        c2 = new Category("Shopping");

        l1 = new LogItem(LocalDate.of(2023, 1, 1), -25, c2);
        l2 = new LogItem(LocalDate.of(2023, 1, 2), 1000, c1);

        c1Items = c1.getCategoryItems();

    }

    @Test
    public void constructorTest() {
       assertEquals("Salary",c1.getCategoryName());
       assertEquals(0, c1Items.size());

    }

    @Test
    public void addLogToCategoryTest() {
        c1.addLogToCategory(l2);
        assertTrue(c1Items.contains(l2));
        assertFalse(c1.addLogToCategory(l2));
    }

    @Test
    public void removeLogFromCategoryTest() {
        c1.addLogToCategory(l2);
        c1.removeLogFromCategory(l2);
        assertFalse(c1Items.contains(l2));
    }
}
