package model;

import java.time.LocalDate;

public class Budget {
    private double budget;
    private LocalDate month;

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

    public LocalDate getMonth() {
        return month;
    }

    public void setMonthlyBudget(double budget, LocalDate month) {
        this.month = month;
        this.budget = budget;

    }
}
