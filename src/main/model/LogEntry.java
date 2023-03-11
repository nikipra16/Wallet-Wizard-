package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

//Represents a log entry containing a date, amount and a category
public class LogEntry implements Writable {
    private LocalDate date;
    private double amount;
    private Category category;

    //EFFECTS: constructs a log entry with a given date, amount and a category
    public LogEntry(LocalDate date, double amount, Category category) {
        this.date = date;
        this.amount = amount;
        this.category = new Category("Not Categorized");
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
    public Category getCategory() {
        return category;
    }

    //MODIFIES:this and category
    //EFFECTS: if entry is not categorized or category is different from current category change it to category
    public void setCategory(Category category) {
        if (this.category.getCategoryName().equals("Not Categorized") || this.category != category) {
            this.category = category;
            category.addLogToCategory(this);
        }
    }

    //MODIFIES: this and category
    //EFFECTS: if entry is already categorized change it to new category or uncategorized if new category
    // is the same as current category
    public void changeCategory(Category category) {
        if (!this.category.getCategoryName().equals("Not Categorized")) {
            if (this.category == category) {
                this.category = new Category("Not Categorized");
                category.removeLogFromCategory(this);
            } else {
                setCategory(category);
            }
        }
    }

    // EFFECTS: returns string representation of this logEntry
    public String toString() {
        return  "Date: " + this.getDate().toString() + "  " + "Amount: " + this.getAmount() + "  " + "Category: "
                + this.getCategory().getCategoryName();
    }

    //EFFECTS: save logEntry to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Date", this.date);
        json.put("Amount", this.amount);
        json.put("Category", this.category.getCategoryName());
        return json;
    }
}

