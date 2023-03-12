//package model;
//
//import org.json.JSONObject;
//import persistence.Writable;
//
//import java.time.Month;
//
////Represents a budget containing an amount in dollars for a given month.
//public class Budget implements Writable {
//    private double budget;
//    private Month month;
//
//    //REQUIRES: budget amount >= $0
//    //MODIFIES: this
//    //EFFECTS: constructs a budget in $ for a month
//    public Budget(Month month) {
//        this.budget = 0;
//        this.month = month;
//    }
//
//    //EFFECTS: returns the budget in $
//    public double getBudget() {
//        return budget;
//    }
//
//    //EFFECTS: returns month
//    public Month getMonth() {
//        return month;
//    }
//
//    //MODIFIES: this
//    //EFFECTS: sets a budget for a given month
//    public void setMonthlyBudget(double budget, Month month) {
//        this.month = month;
//        this.budget = budget;
//
//    }
//
//    @Override
//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("Month", month.toString());
//        json.put("Budget", budget);
//        return json;
//    }
//
//
//    public void setBudget(double budget) {
//        this.budget = budget;
//    }
//
//    public void setMonth(Month month) {
//        this.month = month;
//    }
//}
