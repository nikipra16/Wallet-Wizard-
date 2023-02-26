package ui;

import model.Book;
import model.Budget;
import model.Category;
import model.LogEntry;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BookPage {
    private Book logBook;
    private Scanner input;


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
            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    private void processCommand(String command) {
        switch (command) {
            case "a":
                addLogEntry();
                break;
            case "b":
                getMonthlyExpenditure();
                break;
            case "c":
                setMonthlyBudget();
                break;
            case "d":
                getBalance();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }


    private void displayMenu() {
        System.out.println("Select from the following options:");
        System.out.println("\t[a] Make a new entry");
        System.out.println("\t[b] Get expenditure for a Month");
        System.out.println("\t[c] Set budget for a Month");
        System.out.println("\t[d] Get Balance");
        System.out.println("\t[e] quit");
    }

    private void getBalance() {
        System.out.println("Your Balance:");
        if (logBook.size() > 0) {
            double total;
            total = logBook.totalAmountLeft();
            System.out.println(total);
            if (total <= 0) {
                System.out.println("YOU ARE IN DEBT!!!");
            }
        } else {
            System.out.println("No entries made yet!");
        }
    }

    private void setMonthlyBudget() {
        System.out.print("Enter month number: ");
        int month = input.nextInt();
        System.out.print("Enter budget amount: ");
        double amount = input.nextDouble();
        Budget budget = new Budget();
        budget.setMonthlyBudget(amount,Month.of(month));
        System.out.println("A budget of " + budget.getBudget() + " for " + budget.getMonth());
    }

    private void getMonthlyExpenditure() {
        System.out.print("Enter month number: ");
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
        LogEntry log = new LogEntry(logDate, amount);
        log.setCategory(category);
        logBook.addLog(log);
        System.out.println("Added $" + log.getAmount() + " to your Book under " + category.getCategoryName()
                + ".");
    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        logBook = new Book();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

}