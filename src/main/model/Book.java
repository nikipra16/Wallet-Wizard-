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
    }

    //MODIFIES:this
    //EFFECTS:removes a logEntry to Book
    public void removeLog(LogEntry log) {
        logbook.remove(log);
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

    //EFFECTS: checks if a LogEntry is in the Book
    public boolean containsLog(LogEntry logbookEntry) {
        return logbook.contains(logbookEntry);
    }

    //EFFECTS: save Book to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("LogEntries", logEntriesToJson());
        return json;
    }

    //EFFECTS: returns name of the book
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<LogEntry> getEntries() {
        return Collections.unmodifiableList(logbook);
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray logEntriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (LogEntry logEntry : logbook) {
            jsonArray.put(logEntry.toJson());
        }

        return jsonArray;
    }

}
