package model;

import java.time.LocalDate;

public class Budget {
    private double budget;
    private LocalDate month;

    public Budget(double budget, LocalDate month) {
        this.budget = budget;
        this.month = month;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public void setMonthlyBudget(double budget, LocalDate month) {
        this.month = month;
        this.budget = budget;

    }
}
