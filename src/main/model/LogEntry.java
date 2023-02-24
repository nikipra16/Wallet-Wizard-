package model;

import java.time.LocalDate;

public class LogEntry {
    private final LocalDate date;
    private final double amount;
    private Category category;

    public LogEntry(LocalDate date, double amount) {
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
            } else  {
                setCategory(category);
            }
        }
    }
}

