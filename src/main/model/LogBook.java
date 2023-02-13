package model;

import java.time.Month;
import java.util.ArrayList;
import java.time.LocalDate;

public class LogBook {
    private  ArrayList<LogItem> logbook;

    public LogBook() {
        logbook = new ArrayList<>();
    }

    public void addLog(LogItem log) {
        logbook.add(log);
    }

    public void removeLog(LogItem log) {
        logbook.remove(log);
    }

    public double totalAmountLeft() {
        double sum = 0;
        for (LogItem log : logbook) {
            sum = sum + log.getAmount();
        }
        return sum;
    }

    public double totalAmountForMonth() {
        double sum = 0;
        Month month = LocalDate.now().getMonth();
        for (LogItem log : logbook) {
            if (log.getDate().getMonth() == month) {
                sum = sum + log.getAmount();
            }
        }
        return sum;
    }


}
