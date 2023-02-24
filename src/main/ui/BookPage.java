package ui;

import model.Book;
import model.Category;
import model.logEntry;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BookPage {
//    private double deb;
//    private double cred;
//    double amount;
    private Book logBook;
    private Scanner input;
    private LocalDate logDate;
    private String categoryName;


    // EFFECTS: runs the teller application
    public BookPage() {
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
            if (command.equals("1")) {
                processCommand(command);
            }
            if (command.equals("2")) {
                keepGoing = false;
            }
            if (command.equals("3")) {
                keepGoing = false;
            }
        }

        System.out.println("\nGoodbye!");
    }

    private void processCommand(String command) {
        if (command.equals("1")) {
            addLogEntry();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayMenu() {
        System.out.println("Select from the following options:");
        System.out.println("\t[1] Make a new entry");
        System.out.println("\t[2] Set a budget for a Month");
        System.out.println("\t[3] quit");
    }


    private void addLogEntry() {
        System.out.print("Enter amount: $");
        double amount = input.nextDouble();
        System.out.print("Enter category: ");
        categoryName = input.next();
        Category category = new Category(categoryName);
        System.out.print("Enter date: yyyy/mm/dd: ");
        String date = input.next();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate logDate = LocalDate.parse(date, d);
        logEntry log = new logEntry(logDate, amount, category);
        log.setCategory(category);
        logBook.addLog(log);
        System.out.println("Added $" + log.getAmount() + " to your Book under " + category.getCategoryName());
    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        logBook = new Book();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

}