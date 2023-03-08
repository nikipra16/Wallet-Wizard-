package model;

import java.util.ArrayList;
import java.util.List;

//Represents a category with a given name and contains the log entries under that category
public class Category {
    private String categoryName;
    private ArrayList<LogEntry> categoryLogs;

    //EFFECTS: constructs a category with a given name and contains the log entries under that category
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

    //MODIFIES:this and LogEntry
    //EFFECTS:removes LogEntry from a Category if the entry is of that category
    public void removeLogFromCategory(LogEntry logbookEntry) {
        if (categoryLogs.contains(logbookEntry)) {
            categoryLogs.remove(logbookEntry);
            logbookEntry.changeCategory(this);
        }
    }

    //EFFECTS:returns category name
    public String getCategoryName() {
        return categoryName;
    }

    //EFFECTS:returns the logs under a category
    public List<LogEntry> getCategoryLogs() {
        return categoryLogs;
    }


}
