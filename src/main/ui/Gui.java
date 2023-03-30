package ui;

import model.LogEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//represents a GUI
public class Gui extends JFrame implements ActionListener {
    private JTextField dateField;
    private JTextField amountField;
    private JTextField categoryField;
    private NumberFormat amountDisplayFormat;
    private SimpleDateFormat dateDisplayFormat;
    private SimpleDateFormat dateEditFormat;
    private JPanel homePanel;
    private JList<String> entries;
    private DefaultListModel<String> entriesList;
    private final JTabbedPane tabs;
    private BookPage bookPage;

    //EFFECTS: Constructs the Gui
    public Gui(BookPage bookPage) {
        welcomeWindow();
        this.bookPage = bookPage;
        setTitle("Log Entries");
        tabs = new JTabbedPane();
        setBounds(500, 10, 500, 400);
        tabs.setTabPlacement(JTabbedPane.TOP);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        entriesList = new DefaultListModel<>();
        entries = new JList<>(entriesList);
        loadTabs();
        add(tabs);
        setBackground(Color.pink);
        setVisible(true);

    }

    //EFFECTS: adds logEntry and displays it in the panel
    public void addLogEntry() {
        String dateInput = dateField.getText();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate logDate = LocalDate.parse(dateInput, dateFormat);
        double logAmount = Double.parseDouble(amountField.getText());
        String logCategory = categoryField.getText();

        LogEntry logEntry = new LogEntry(logDate, logAmount, logCategory);
        logEntry.setCategory(logCategory);
        bookPage.getBook().addLog(logEntry);
        entriesList.addElement(logEntry.toString());
        dateField.setText(null);
        amountField.setText("");
        categoryField.setText("");

    }

    //MODIFIES: entriesList,this
    //EFFECTS: loads data and displays it
    public void loadDisplay() {
        bookPage.load();
        List<LogEntry> logEntries = bookPage.getBook().getEntries();
        for (LogEntry logEntry : logEntries) {
            entriesList.addElement(logEntry.toString());
        }
    }

    //EFFECTS: loads tabs,panels and buttons
    public void loadTabs() {
        homePanel = new JPanel(new GridLayout(5, 2));
        homePanel.setBackground(Color.getHSBColor(100,25,20));
        setFields();
        setLabels();
        JButton enter = new JButton("enter");
        JButton save = new JButton("save and exit");
        JButton load = new JButton("load");
        load.addActionListener(this);
        save.addActionListener(this);
        enter.addActionListener(this);
        homePanel.add(enter, BorderLayout.LINE_END);
        homePanel.add(save);
        homePanel.add(load);
        tabs.add(homePanel, 0);
        tabs.setTitleAt(0, "Home");
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane entryPanel = new JScrollPane(entries);
        panel.add(entryPanel,BorderLayout.CENTER);
        JButton remove = new JButton("clear entries");
        remove.addActionListener(this);
        panel.add(remove,BorderLayout.SOUTH);
        tabs.add(panel, 1);
        tabs.setTitleAt(1, "Entries");


    }

    //EFFECTS: action performed according to the button clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enter")) {
            addLogEntry();
        }
        if (e.getActionCommand().equals("clear entries")) {
            entriesList.clear();
        }
        if (e.getActionCommand().equals("save and exit")) {
            endGui();
        }
        if (e.getActionCommand().equals("load")) {
            loadDisplay();

        }
    }

    //EFFECTS: sets the fields
    public void setFields() {

        dateDisplayFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateEditFormat = new SimpleDateFormat("yyyy-MM-dd");
        amountField = new JTextField();
        amountField.setColumns(10);
        dateField = new JFormattedTextField(dateEditFormat);
        dateField.setColumns(10);
        categoryField = new JTextField();
        categoryField.setColumns(10);
    }


    //EFFECTS: sets the labels
    public void setLabels() {
        JLabel amountLabel = new JLabel("   Amount");
        amountLabel.setLabelFor(amountField);
        amountLabel.setFont(new Font("Courier Bold", Font.PLAIN, 15));
        JLabel dateLabel = new JLabel("   Date");
        dateLabel.setFont(new Font("Courier Bold", Font.PLAIN, 15));
        dateLabel.setLabelFor(dateField);
        JLabel categoryLabel = new JLabel("   Category");
        categoryLabel.setFont(new Font("Courier Bold", Font.PLAIN, 15));
        categoryLabel.setLabelFor(categoryField);
        homePanel.add(dateLabel);
        homePanel.add(dateField);
        homePanel.add(amountLabel);
        homePanel.add(amountField);
        homePanel.add(categoryLabel);
        homePanel.add(categoryField);
    }

    //EFFECTS: creates the opening window
    private void welcomeWindow() {
        JWindow welcomeWindow = new JWindow();
        ImageIcon image = new ImageIcon("data/projectImage.jpg","logo");
        welcomeWindow.getContentPane().add(
                new JLabel("",image, JLabel.CENTER));
        welcomeWindow.setBounds(500, 10, 400, 500);
        welcomeWindow.setVisible(true);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        welcomeWindow.setVisible(false);
        welcomeWindow.dispose();
    }

    //EFFECTS: saves and ends the Gui
    private void endGui() {
        bookPage.saveBook();
        WindowEvent saveWindow = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        dispatchEvent(saveWindow);
    }


}