package model;

import java.util.ArrayList;
import java.util.List;


public class Category {
    private final String categoryName;
    private final ArrayList<LogEntry> categoryLogs;

    public Category(String categoryName) {
        categoryLogs = new ArrayList<>();
        this.categoryName = categoryName;
    }

    //MODIFIES:this and LogEntry
    //EFFECTS:adds LogEntry to a Category if the entry is of that category
    public boolean addLogToCategory(LogEntry logbookEntry) {
        if (!categoryLogs.contains(logbookEntry)) {
            categoryLogs.add(logbookEntry);
            logbookEntry.setCategory(this);
            return true;
        } else {
            return false;
        }
    }

    public void removeLogFromCategory(LogEntry logbookEntry) {
        if (categoryLogs.contains(logbookEntry)) {
            categoryLogs.remove(logbookEntry);
            logbookEntry.changeCategory(this);
        }
    }

    public String getCategoryName() {
        return categoryName;
    }


    public List<LogEntry> getCategoryLogs() {
        return categoryLogs;
    }


}
