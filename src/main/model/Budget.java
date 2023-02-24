package model;

import java.time.LocalDate;
import java.time.Month;

public class Budget {
    private double budget;
    private Month month;

    public Budget() {
        this.budget = 0;
        this.month = null;
    }

//    public void setBudget(double budget) {
//        this.budget = budget;
//    }
    //EFFECTS: returns the budget in $
    public double getBudget() {
        return budget;
    }

    //EFFECTS: returns month
    public Month getMonth() {
        return month;
    }

    //MODIFIES: this
    public void setMonthlyBudget(double budget, Month month) {
        this.month = month;
        this.budget = budget;

    }
}
