package model;

import java.util.ArrayList;
import java.util.List;


public class Category {
    private final String categoryName;
    private final ArrayList<LogItem> categoryItems;

    public Category(String categoryName) {
        categoryItems = new ArrayList<>();
        this.categoryName = categoryName;
    }

    public boolean addLogToCategory(LogItem logItem) {
        if (!categoryItems.contains(logItem)) {
            categoryItems.add(logItem);
            logItem.setCategory(this);
            return true;
        } else {
            return false;
        }
    }

    public void removeLogFromCategory(LogItem logItem) {
        if (categoryItems.contains(logItem)) {
            categoryItems.remove(logItem);
            logItem.changeCategory(this);
        }
    }

    public String getCategoryName() {
        return categoryName;
    }


    public List<LogItem> getCategoryItems() {
        return categoryItems;
    }


}
