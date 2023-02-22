package model;

import java.time.LocalDate;

public class LogItem {
    private final LocalDate date;
    private double amount;
    private Category category;

    public LogItem(LocalDate date, double amount, Category category) {
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

    // public void setAmount(double amount) {
    //    this.amount = amount;
    //}

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (this.category.getCategoryName().equals("Not Categorized") || this.category != category) {
            this.category = category;
            category.addLogToCategory(this);
        }
    }

    //public boolean giveCategory(Category category) {
    //  if (category == null) {
    //      this.category = category;

    // }
    // return true;
    // }

    public boolean changeCategory(Category category) {
        if (!this.category.getCategoryName().equals("Not Categorized") && this.category == category) {
            this.category = new Category("Not Categorized");
            category.removeLogFromCategory(this);
        } else if (this.category != category) {
            setCategory(category);
        }
        return false;
    }

}

