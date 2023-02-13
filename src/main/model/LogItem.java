package model;

import java.time.LocalDate;

public class LogItem {
    private final LocalDate date;
    private double amount;
    private Category category;

    public LogItem(LocalDate date, double amount, Category category) {
        this.date = date;
        this.amount = amount;
        this.category = category;
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
        this.category = category;
    }

    //public boolean giveCategory(Category category) {
      //  if (category == null) {
      //      this.category = category;

       // }
       // return true;
   // }

    public boolean removeCategory(Category category) {
        if (this.category != null) {
            setCategory(null);
        }
        return false;
    }

}
