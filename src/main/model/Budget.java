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

    public double getBudget() {
        return budget;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonthlyBudget(double budget, Month month) {
        this.month = month;
        this.budget = budget;

    }
}
