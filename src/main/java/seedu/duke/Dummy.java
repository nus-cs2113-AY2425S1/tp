package seedu.duke;

import java.util.Scanner;

public class Dummy {
    public static void main(String[] args) {
        TrackerData trackerData = new TrackerData();

        CategoryManager categoryManager = new CategoryManager();
        BudgetManager budgetManager = new BudgetManager();
        ExpenseManager expenseManager = new ExpenseManager();

        Scanner in = new Scanner(System.in);
        System.out.println("Hiya! How can I assist?");
        while (true) {
            if (in.hasNextLine()) {
                String input = in.nextLine().trim();
                if (input.startsWith("add-expense")) {
                    addExpenseRequest(input, expenseManager, trackerData);
                } else if (input.startsWith("add-category")) {
                    categoryManager.addCategory(trackerData, input);
                } else if (input.startsWith("delete-expense")) {
                    deleteExpenseRequest(input, expenseManager, trackerData);
                } else if (input.startsWith("tag-expense")) {
                    expenseManager.tagExpense(trackerData, input);
                } else if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Goodbye! :> Hope to see you again soon!");
                    break;
                } else if (input.equalsIgnoreCase("view-budget")) {
                    budgetManager.viewBudget(trackerData);
                } else if (input.startsWith("set-budget")) {
                    setBudgetLimitRequest(input, budgetManager, trackerData);
                } else if (input.startsWith("view-expenses")) {
                    expenseManager.viewExpensesByCategory(trackerData);
                } else if (input.equalsIgnoreCase("toggle-reset")) {
                    budgetManager.toggleAutoReset();
                } else {
                    System.out.println("Invalid input! Try again."); // Provide feedback for invalid input
                }
            } else {
                System.out.println("No input received.");
                break;
            }
        }
    }

    public static void addExpenseRequest(String input, ExpenseManager expenseManager, TrackerData trackerData) {
        try {
            String[] parts = input.split(" ");
            String name = null;
            double amount = 0;
            String category = null;

            for (String part : parts) {
                if (part.startsWith("n/")) {
                    name = part.substring(2).trim();
                } else if (part.startsWith("a/")) {
                    amount = Double.parseDouble(part.substring(2).trim());
                } else if (part.startsWith("c/")) {
                    category = part.substring(2).trim();
                }
            }

            if (name == null || amount == 0) {
                System.out.println("Invalid input! Please provide name, amount, and category.");
                return;
            }

            expenseManager.addExpense(trackerData, name, amount, category);
        } catch (Exception e) {
            System.out.println("Error parsing the input. Please use the correct format for add-expense commands.");
        }
    }

    public static void setBudgetLimitRequest(String input, BudgetManager budgetManager, TrackerData trackerData) {
        try {
            String[] parts = input.split(" ");
            double limit = 0;
            String category = null;

            for (String part : parts) {
                if (part.startsWith("c/")) {
                    category = part.substring(2).trim();
                } else if (part.startsWith("l/")) {
                    limit = Double.parseDouble(part.substring(2).trim());
                }
            }

            if (category == null || limit == 0) {
                System.out.println("Invalid input! Please provide category name and limit.");
                return;
            }

            budgetManager.setBudgetLimit(trackerData, category, limit);
        } catch (Exception e) {
            System.out.println("Error parsing the input. Please use the correct format for set-budget commands.");
        }
    }

    public static void deleteExpenseRequest(String input, ExpenseManager expenseManager, TrackerData trackerData) {
        try {
            String[] parts = input.split(" ");
            if (parts.length < 2 || !parts[1].startsWith("e/")) {
                System.out.println("Invalid input! Please provide an expense index to delete.");
                return;
            }
            int expenseIndex = Integer.parseInt(parts[1].substring(2).trim()) - 1; // 1-based index
            expenseManager.deleteExpense(trackerData, expenseIndex);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing the expense index. Please use the correct format.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}




