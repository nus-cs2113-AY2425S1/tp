package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String filePath = "spendswift.txt";
        Storage storage = new Storage(filePath);
        TrackerData trackerData = new TrackerData();

        try {
            storage.loadData(trackerData);
            System.out.println("Data loaded successfully");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

        CategoryManager categoryManager = new CategoryManager();
        BudgetManager budgetManager = new BudgetManager();
        ExpenseManager expenseManager = new ExpenseManager();
        Parser parser = new Parser(expenseManager, categoryManager, budgetManager);

        Scanner in = new Scanner(System.in);
        System.out.println("Hiya! How can I assist?");
        boolean isExit = false;

        while (!isExit && in.hasNextLine()) {
            String input = in.nextLine();
            isExit = parser.parseCommand(input, trackerData);
        }

        try {
            storage.saveData(trackerData);
            System.out.println("Data has been saved!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
