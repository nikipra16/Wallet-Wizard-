package model;

import java.time.YearMonth;
import java.util.ArrayList;

public class Book {
    private  ArrayList<LogEntry> logbook;

    public Book() {
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
        for (LogEntry log : logbook) {
            sum = sum + log.getAmount();
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


}
