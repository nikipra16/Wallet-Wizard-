package ui;

import model.Book;
import model.LogEntry;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class LogEntriesPanel extends JFrame {
    private JTextField dateField;
    private JFormattedTextField amountField;
    private JTextField categoryField;
    private double amount;
    private LocalDate date;
    private String category;

    //Formats to format and parse numbers
    private NumberFormat amountDisplayFormat;
    private NumberFormat amountEditFormat;
    private SimpleDateFormat dateDisplayFormat;
    private SimpleDateFormat dateEditFormat;
    private JTextField categoryDisplayFormat;
    private JTextField categoryEditFormat;
    private JLabel amountLabel;
    private JLabel dateLabel;
    private JLabel categoryLabel;
    private JPanel logEntriesPanel;

    public LogEntriesPanel() {
        setTitle("Log Entries");
        setFormats();
        setFields();
        setLabels();
        logEntriesPanel = new JPanel(new GridLayout(4,2));
        setPanel();

        JButton save = new JButton("submit");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveLogEntry();
            }
        });
        save.setSize(10,20);
        logEntriesPanel.add(save);
        add(logEntriesPanel,BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void setFields() {
        amountField = new JFormattedTextField();
        amountField.setColumns(10);
        dateField = new JFormattedTextField(dateEditFormat);
        dateField.setColumns(10);
        categoryField = new JTextField();
        categoryField.setColumns(10);
    }

    public void setLabels() {
        amountLabel = new JLabel("Amount");
        amountLabel.setLabelFor(amountField);
        dateLabel = new JLabel("Date");
        dateLabel.setLabelFor(dateField);
        categoryLabel = new JLabel("Category");
        categoryLabel.setLabelFor(categoryField);
    }

    public void setFormats() {
        amountDisplayFormat = NumberFormat.getNumberInstance();
        amountEditFormat = NumberFormat.getNumberInstance();
        dateDisplayFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateEditFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void setPanel() {
        logEntriesPanel.add(dateLabel);
        logEntriesPanel.add(dateField);
        logEntriesPanel.add(amountLabel);
        logEntriesPanel.add(amountField);
        logEntriesPanel.add(categoryLabel);
        logEntriesPanel.add(categoryField);
    }

    public void saveLogEntry() {
        String dateInput = dateField.getText();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate logDate = LocalDate.parse(dateInput, dateFormat);
        double logAmount = Double.parseDouble(amountField.getText());
        String logCategory = categoryField.getText();


        dateField.setText(null);
        amountField.setText("");
        categoryField.setText("");

        LogEntry logEntry = new LogEntry(logDate,logAmount,logCategory);
        Book logBook = new Book("Book");
        logBook.addLog(logEntry);

    }

//    public static void main(String[] args) {
//        new LogEntriesPanel();
//
//    }

}
