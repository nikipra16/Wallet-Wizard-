//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.Month;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//public class BudgetTest {
//    private Budget b1;
//
//    @BeforeEach
//    void runBefore() {
//        b1 = new Budget(Month.APRIL);
//    }
//
//    @Test
//    public void budgetNotYetSetTest() {
//        assertEquals(Month.valueOf("APRIL"),b1.getMonth());
//        assertEquals(0, b1.getBudget());
//    }
//
//    @Test
//    public void setMonthlyBudgetTest() {
//        b1.setMonthlyBudget(150, Month.of(1));
//        assertEquals(150,b1.getBudget());
//        assertEquals(Month.of(1),b1.getMonth());
//    }
//
//    @Test
//    public void setMonthTest() {
//        b1.setMonth(Month.APRIL);
//        assertEquals(Month.of(4),b1.getMonth());
//    }
//
//    @Test
//    public  void setBudgetTest() {
//        b1.setBudget(100);
//        assertEquals(100,b1.getBudget());
//    }
//}
