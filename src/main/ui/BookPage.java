package ui;

import model.Book;
import model.Budget;
import model.Category;
import model.LogEntry;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BookPage {
    private Book logBook;
    private Scanner input;
    private static final String JSON_STORE = "./data/Book.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the teller application
    public BookPage() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBook();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBook() {
        boolean keepGoing = true;
        String command = null;

        init();

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
    private void processCommand(String command) {
        switch (command) {
            case "a":
                addLogEntry();
                break;
            case "b":
                setMonthlyBudget();
                break;
//            case "c":
//                getMonthlyExpenditure();
//                break;
            case "c":
                getBalance();
                break;
            case "s":
                saveWorkRoom();
                break;
            case "l":
                loadWorkRoom();
            case "p":
                printThingies();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Select from the following options:");
        System.out.println("\t[a] Make a new entry");
        System.out.println("\t[b] Set budget for a Month");
//        System.out.println("\t[c] Get balance for a Month");
        System.out.println("\t[c] Get balance for the Year");
        System.out.println("\t[s] Save LogBook");
        System.out.println("\t[l] Load LogBook");
        System.out.println("\t[p] Print LogBook");
        System.out.println("\t[q] quit");
    }

    //REQUIRES: at least one log entry
    // EFFECTS: returns balance
    private void getBalance() {
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

    //REQUIRES: amount > 0
    //MODIFIES: Budget
    // EFFECTS: sets a budget for a month
    private void setMonthlyBudget() {
        System.out.print("Enter month number: ");
        int month = input.nextInt();
        System.out.print("Enter budget amount: ");
        double amount = input.nextDouble();
        Budget budget = new Budget();
        if (amount > 0) {
            budget.setMonthlyBudget(amount,Month.of(month));
            System.out.println("A budget of " + budget.getBudget() + " for " + budget.getMonth());
        } else {
            System.out.println("BUDGET MUST BE GREATER THAN $0!!!");
        }

    }


    //MODIFIES: this
    //EFFECTS: adds a Log entry to the Book
    private void addLogEntry() {
        System.out.print("Enter date: yyyy/mm/dd: ");
        String date = input.next();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate logDate = LocalDate.parse(date, d);
        System.out.print("Enter amount: $");
        double amount = input.nextDouble();
        System.out.print("Enter category: ");
        String categoryName = input.next();
        Category category = new Category(categoryName);
        LogEntry log = new LogEntry(logDate, amount,category);
        log.setCategory(category);
        logBook.addLog(log);
        System.out.println("Added $" + log.getAmount() + " to your Book under " + category.getCategoryName()
                + ".");
    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        logBook = new Book("Book");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: saves logbook to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(logBook);
            jsonWriter.close();
            System.out.println("Saved " + logBook.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads logbook from file
    private void loadWorkRoom() {
        try {
            logBook = jsonReader.read();
            System.out.println("Loaded " + logBook.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints all the logEntries in logbook to the console
    private void printThingies() {
        List<LogEntry> logEntries = logBook.getEntries();

        for (LogEntry logEntry : logEntries) {
            System.out.println(logEntry);
        }
    }

//    // EFFECTS: get balance for a month
//    private void getMonthlyExpenditure() {
//        System.out.print("Enter Year and Month (yyyy/mm): ");
//        String date = input.next();
//        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM");
//        YearMonth yearMonth = YearMonth.parse(date, d);
//        if (logBook.size() > 0) {
//            double total;
//            total = logBook.monthlyExpenditure(yearMonth);
//            System.out.println(total);
//        } else {
//            System.out.println("No entries");
//        }
//    }

}