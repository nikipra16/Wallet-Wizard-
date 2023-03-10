package persistence;

import model.Book;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import model.Category;
import model.LogEntry;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Book read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Book parseBook(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        Book b = new Book(name);
        addLogEntries(b, jsonObject);
        return b;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addLogEntries(Book b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("LogEntries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addLogEntry(b, nextEntry);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addLogEntry(Book b, JSONObject jsonObject) {
//        String logDate = jsonObject.getString("Date");
        Double amount = jsonObject.getDouble("Amount");
        String category1 = jsonObject.getString("Category");
        Category category = new Category(category1);
//        Category category = Category.valueOf(jsonObject.getString("category"));
//        int year = LocalDate.now().getYear();
//        int month =  LocalDate.now().getMonthValue();
//        int dayOfMonth = LocalDate.now().getDayOfMonth();
//        year = jsonObject.getInt("year");
//        month = jsonObject.getInt("Month");
//        dayOfMonth = jsonObject.getInt("Day");
//        LocalDate logDate = LocalDate.of(year,month,dayOfMonth);
//          LocalDate date = jsonObject.getString(logDate);
//        LogEntry logEntry = null;
//        int year = logEntry.getDate().getYear();
//        int month = logEntry.getDate().getMonthValue();
//        int day = logEntry.getDate().getDayOfMonth();
        String date = jsonObject.getString("Date");
//        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate logDate = LocalDate.parse(date);
//        LocalDate logDate = LocalDate.of(year,month,day);
        LogEntry logentry = new LogEntry(logDate,amount, category);
        logentry.setCategory(category);
        b.addLog(logentry);
    }
}
