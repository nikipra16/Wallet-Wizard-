package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BudgetTest {
    private Budget b1;

    @BeforeEach
    void runBefore() {
        b1 = new Budget();
    }

    @Test
    public void budgetNotYetSetTest() {
        assertNull(b1.getMonth());
        assertEquals(0, b1.getBudget());
    }

    @Test
    public void setBudgetTest() {
        b1.setMonthlyBudget(150, LocalDate.of(2023,1,1));
        assertEquals(150,b1.getBudget());
        assertEquals(LocalDate.of(2023,1,1),b1.getMonth());
    }
}
