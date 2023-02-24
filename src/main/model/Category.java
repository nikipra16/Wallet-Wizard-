package model;

import java.util.ArrayList;
import java.util.List;


public class Category {
    private final String categoryName;
    private final ArrayList<logEntry> categoryItems;

    public Category(String categoryName) {
        categoryItems = new ArrayList<>();
        this.categoryName = categoryName;
    }

    public boolean addLogToCategory(logEntry logEntry) {
        if (!categoryItems.contains(logEntry)) {
            categoryItems.add(logEntry);
            logEntry.setCategory(this);
            return true;
        } else {
            return false;
        }
    }

    public void removeLogFromCategory(logEntry logEntry) {
        if (categoryItems.contains(logEntry)) {
            categoryItems.remove(logEntry);
            logEntry.changeCategory(this);
        }
    }

    public String getCategoryName() {
        return categoryName;
    }


    public List<logEntry> getCategoryItems() {
        return categoryItems;
    }


}
