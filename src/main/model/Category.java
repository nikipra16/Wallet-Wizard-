package model;

import java.util.ArrayList;
import java.util.List;


public class Category {
    private final String categoryName;
    private final ArrayList<LogEntry> categoryItems;

    public Category(String categoryName) {
        categoryItems = new ArrayList<>();
        this.categoryName = categoryName;
    }

    public boolean addLogToCategory(LogEntry logbookEntry) {
        if (!categoryItems.contains(logbookEntry)) {
            categoryItems.add(logbookEntry);
            logbookEntry.setCategory(this);
            return true;
        } else {
            return false;
        }
    }

    public void removeLogFromCategory(LogEntry logbookEntry) {
        if (categoryItems.contains(logbookEntry)) {
            categoryItems.remove(logbookEntry);
            logbookEntry.changeCategory(this);
        }
    }

    public String getCategoryName() {
        return categoryName;
    }


    public List<LogEntry> getCategoryItems() {
        return categoryItems;
    }


}
