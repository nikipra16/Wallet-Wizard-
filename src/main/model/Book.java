package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a Book containing the log entries.
public class Book implements Writable {
    private String name;
    private  ArrayList<LogEntry> logbook;

    //EFFECTS: constructs a logbook
    public Book(String name) {
        this.name = name;
        logbook = new ArrayList<>();
    }

    //MODIFIES:this
    //EFFECTS:adds a logEntry to Book
    public void addLog(LogEntry log) {
        logbook.add(log);
        EventLog.getInstance().logEvent(new Event("A logentry (" + "Category:" + log.getCategory()
                + " Amount:" + log.getAmount() + ") was added to the LogBook!"));
    }

    //MODIFIES:this
    //EFFECTS:removes a logEntry to Book
    public void removeLog(LogEntry log) {
        logbook.remove(log);
        EventLog.getInstance().logEvent(new Event("A log entry under " + log.getCategory() + " is removed"));
    }

    public void clearAllEntries() {
        logbook.clear();
        EventLog.getInstance().logEvent(new Event("Cleared all entries!!!"));
    }

    //EFFECTS: returns total amount spent/earned
    public double totalAmountLeft() {
        double sum = 0;
        int year = Year.now().getValue();
        for (LogEntry log : logbook) {
            if (log.getDate().getYear() == year) {
                sum = sum + log.getAmount();
            }
        }
        return sum;
    }

    //EFFECTS: returns total amount spent/earned in a month
    public double monthlyExpenditure(YearMonth yearMonth) {
        double sum = 0;
        for (LogEntry log : logbook) {
            if (log.getDate().getMonth() == yearMonth.getMonth() && log.getDate().getYear() == yearMonth.getYear()) {
                sum = sum + log.getAmount();
            }
        }
        return sum;
    }

    //EFFECTS: returns number of entries in a Book
    public int size() {
        return logbook.size();
    }

    //EFFECTS: checks if a LogEntry is in the logbook
    public boolean containsLog(LogEntry logbookEntry) {
        return logbook.contains(logbookEntry);
    }

    //EFFECTS: returns name of the logbook
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns an unmodifiable list of logEntries in this logbook
    public List<LogEntry> getEntries() {
        return logbook;
    }

    //EFFECTS: save logbook to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("LogEntries", logEntriesToJson());
        EventLog.getInstance().logEvent(new Event("Saved Entries"));
        return json;
    }

    // EFFECTS: returns things in this logbook as a JSON array
    private JSONArray logEntriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (LogEntry logEntry : logbook) {
            jsonArray.put(logEntry.toJson());
        }

        return jsonArray;
    }

}
