package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a category with a given name and contains the log entries under that category
public class Category implements Writable {
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
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this and LogEntry
    //EFFECTS: removes LogEntry from a Category if the entry is of that category
    public void removeLogFromCategory(LogEntry logbookEntry) {
        if (categoryLogs.contains(logbookEntry)) {
            categoryLogs.remove(logbookEntry);
            logbookEntry.changeCategory(this);
        }
    }

    //EFFECTS: returns category name
    public String getCategoryName() {
        return categoryName;
    }

    //EFFECTS: returns the logEntries under a category
    public List<LogEntry> getCategoryLogs() {
        return categoryLogs;
    }

    // EFFECTS: returns an unmodifiable list of logEntries in this logbook
    public List<LogEntry> getLogEntries() {
        return Collections.unmodifiableList(categoryLogs);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Logs", logEntriesToJson());
        json.put("Name", this.categoryName);
        return json;
    }

    // EFFECTS: returns things in this logbook as a JSON array
    private JSONArray logEntriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (LogEntry logEntry : categoryLogs) {
            jsonArray.put(logEntry.toJson());
        }

        return jsonArray;
    }
}
