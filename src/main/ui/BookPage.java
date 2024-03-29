package ui;

import model.Book;
import model.LogEntry;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//represents a book page
public class BookPage {
    private Book logBook;
    private Scanner input;
    private static final String JSON_STORE = "./data/Book.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the teller application
    public BookPage() throws IOException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBook();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runBook() throws IOException {
        boolean keepGoing = true;
        String command = null;

        init();
        runGui();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {
        switch (command) {
            case "a":
                addLogEntry();
                break;
            case "b":
                getMonthlyExpenditure();
                break;
            case "c":
                getBalance();
                break;
            case "s":
                saveBook();
                break;
            case "l":
                load();
                break;
            case "p":
                printLogBook();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("Select from the following options:");
        System.out.println("\t[a] Make a new entry");
        System.out.println("\t[b] Get balance for a Month");
        System.out.println("\t[c] Get balance for the Year");
        System.out.println("\t[s] Save LogBook");
        System.out.println("\t[l] Load LogBook");
        System.out.println("\t[p] Print LogBook");
        System.out.println("\t[q] quit");
    }

    //REQUIRES: at least one log entry
    // EFFECTS: returns balance
    public void getBalance() {
        System.out.println("Your Balance:");
        if (logBook.size() > 0) {
            double total;
            total = logBook.totalAmountLeft();
            System.out.println(total);
            if (total < 0) {
                System.out.println("YOU ARE IN DEBT!!!");
            }
        } else {
            System.out.println("No entries made yet!");
        }
    }


    //MODIFIES: this
    //EFFECTS: adds a log entry to the Book
    public void addLogEntry() {
        System.out.print("Enter date: yyyy/mm/dd: ");
        String date = input.next();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate logDate = LocalDate.parse(date, d);
        System.out.print("Enter amount: $");
        double amount = input.nextDouble();
        System.out.print("Enter category: ");
        String categoryName = input.next();
        LogEntry log = new LogEntry(logDate, amount,categoryName);
        log.setCategory(categoryName);
        logBook.addLog(log);
        System.out.println("Added $" + log.getAmount() + " to your Book under " + categoryName
                + ".");
    }


    // MODIFIES: this
    // EFFECTS: initializes logbook
    public void init() {
        logBook = new Book("Book");

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: saves logbook to file
    public void saveBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(logBook);
//            jsonWriter.writeCategory(category);
            jsonWriter.close();
            System.out.println("Saved " + logBook.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads logbook from file
    public void load() {
        try {
            logBook = jsonReader.readBook();
            System.out.println("Loaded " + logBook.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    //EFFECTS: prints logbook
    public void printLogBook() {
        List<LogEntry> logEntries = logBook.getEntries();

        for (LogEntry logEntry : logEntries) {
            System.out.println(logEntry);
        }
    }


    // EFFECTS: get balance for a month
    public void getMonthlyExpenditure() {
        System.out.print("Enter Year and Month (yyyy/mm): ");
        String date = input.next();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM");
        YearMonth yearMonth = YearMonth.parse(date, d);
        if (logBook.size() > 0) {
            double total;
            total = logBook.monthlyExpenditure(yearMonth);
            System.out.println(total);
        } else {
            System.out.println("No entries");
        }
    }

    //EFFECTS: returns the list of entries
    public Book getBook() {
        return this.logBook;
    }

    //EFFECTS: runs the Gui
    public void runGui() throws IOException {
        new Gui(this);

    }


    //    //EFFECTS: prints entries in a category
//    private void printCategory() {
//        List<LogEntry> logEntries = category.getLogEntries();
//
//        for (LogEntry logEntry : logEntries) {
//            System.out.println(logEntry);
//        }
//    }

    //REQUIRES: amount > 0
    //MODIFIES: Budget
    // EFFECTS: sets a budget for a month
//    private void setMonthlyBudget() {
//        System.out.print("Enter month number: ");
//        int month = input.nextInt();
//        System.out.print("Enter budget amount: ");
//        double amount = input.nextDouble();
//        Budget budget = new Budget(Month.of(month));
//        if (amount > 0) {
//            budget.setMonthlyBudget(amount,Month.of(month));
//            System.out.println("A budget of " + budget.getBudget() + " for " + budget.getMonth());
//        } else {
//            System.out.println("BUDGET MUST BE GREATER THAN $0!!!");
//        }
//
//    }



}