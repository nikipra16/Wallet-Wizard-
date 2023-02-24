package model;

import java.time.Month;
import java.util.ArrayList;

public class Book {
    private final ArrayList<LogEntry> logbook;

    public Book() {
        logbook = new ArrayList<>();
    }

    public void addLog(LogEntry log) {
        logbook.add(log);
    }

    public void removeLog(LogEntry log) {
        logbook.remove(log);
    }

    public double totalAmountLeft() {
        double sum = 0;
        for (LogEntry log : logbook) {
            sum = sum + log.getAmount();
        }
        return sum;
    }

    public double monthlyExpenditure(Month month) {
        double sum = 0;
        for (LogEntry log : logbook) {
            if (log.getDate().getMonth() == month) {
                sum = sum + log.getAmount();
            }
        }
        return sum;
    }

    public int size() {
        return logbook.size();
    }

    public boolean containsLog(LogEntry logbookEntry) {
        return logbook.contains(logbookEntry);
    }


}
