package model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Book {
    private final ArrayList<logEntry> logbook;

    public Book() {
        logbook = new ArrayList<>();
    }

    public void addLog(logEntry log) {
        logbook.add(log);
    }

    public void removeLog(logEntry log) {
        logbook.remove(log);
    }

    public double totalAmountLeft() {
        double sum = 0;
        for (logEntry log : logbook) {
            sum = sum + log.getAmount();
        }
        return sum;
    }

//    public double monthlyExpenditure() {
//        double sum = 0;
//        Month month = LocalDate.now().getMonth();
//        for (logEntry log : logbook) {
//            if (log.getDate().getMonth() == month) {
//                sum = sum + log.getAmount();
//            }
//        }
//        return sum;
//    }

    public double monthlyExpenditure(Month month) {
        double sum = 0;
        for (logEntry log : logbook) {
            if (log.getDate().getMonth() == month) {
                sum = sum + log.getAmount();
            }
        }
        return sum;
    }

    public int size() {
        return logbook.size();
    }

    public boolean containsLog(logEntry logEntry) {
        return logbook.contains(logEntry);
    }


}
