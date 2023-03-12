package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

//Represents a log entry containing a date, amount and a category
public class LogEntry implements Writable {
    private LocalDate date;
    private double amount;
    private String category;

    //EFFECTS: constructs a log entry with a given date, amount and a category
    public LogEntry(LocalDate date, double amount, String category) {
        this.date = date;
        this.amount = amount;
        this.category = "Not Categorized";
    }

    //EFFECTS: returns date
    public LocalDate getDate() {
        return date;
    }

    //EFFECTS: returns amount in $
    public double getAmount() {
        return amount;
    }

    //EFFECTS: returns category
    public String getCategory() {
        return category;
    }

    //MODIFIES:this and category
    //EFFECTS: if entry is not categorized or category is different from current category change it to category
    public void setCategory(String category) {
        if (this.getCategory().equals("Not Categorized") || this.category != category) {
            this.category = category;
        }
    }

    //MODIFIES: this and category
    //EFFECTS: if entry is already categorized change it to new category or uncategorized if new category
    // is the same as current category
    public void changeCategory(String category) {
        if (!this.getCategory().equals("Not Categorized")) {
            if (this.category == category) {
                this.category = "Not Categorized";
            } else {
                setCategory(category);
            }
        }
    }

    // EFFECTS: returns string representation of this logEntry
    public String toString() {
        return  "Date: " + this.getDate().toString() + "  " + "Amount: " + this.getAmount() + "  " + "Category: "
                + this.getCategory();
    }

    //EFFECTS: save logEntry to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Date", this.date);
        json.put("Amount", this.amount);
        json.put("Category", this.getCategory());
        return json;
    }
}

