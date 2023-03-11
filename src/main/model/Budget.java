package model;

import java.time.Month;

//Represents a budget containing an amount in dollars for a given month.
public class Budget {
    private double budget;
    private Month month;

    //REQUIRES: budget amount >= $0
    //MODIFIES: this
    //EFFECTS: constructs a budget in $ for a month
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
    //EFFECTS: sets a budget for a given month
    public void setMonthlyBudget(double budget, Month month) {
        this.month = month;
        this.budget = budget;

    }
}
