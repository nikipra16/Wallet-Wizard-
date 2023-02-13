package model;

import java.util.ArrayList;

public class Category {
    private String categoryName;
    private ArrayList<LogItem> categoryItems;

    public Category(String categoryName) {
        categoryItems = new ArrayList<>();
        this.categoryName = categoryName;
    }

    public void addLog(LogItem logItem) {
        if (!categoryItems.contains(logItem)) {
            categoryItems.add(logItem);
        }
    }

    public void removeLog(LogItem logItem) {
        if (categoryItems.contains(logItem)) {
            categoryItems.remove(logItem);
        }
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<LogItem> getCategoryItems() {
        return categoryItems;
    }

    public void setCategoryItems(ArrayList<LogItem> categoryItems) {
        this.categoryItems = categoryItems;
    }

}
