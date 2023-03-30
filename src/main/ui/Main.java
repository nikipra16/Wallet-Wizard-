package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

// represents the main
public class Main {
    public static void main(String[] args) throws FileNotFoundException,IOException {
        try {
            BookPage book = new BookPage();
            new Gui(book);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
