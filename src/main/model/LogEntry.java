package model;

import java.time.LocalDate;

public class LogEntry {
    private final LocalDate date;
    private double amount;
    private Category category;

    public LogEntry(LocalDate date, double amount, Category category) {
        this.date = date;
        this.amount = amount;
        this.category = new Category("Not Categorized");
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (this.category.getCategoryName().equals("Not Categorized") || this.category != category) {
            this.category = category;
            category.addLogToCategory(this);
        }
    }

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

