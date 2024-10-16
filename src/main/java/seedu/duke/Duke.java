package seedu.duke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        Scanner in = new Scanner(System.in);
        System.out.println("Hiya! How can I assist?");
        while (true) {
            if (in.hasNextLine()) {
                String input = in.nextLine().trim();
                if (input.startsWith("add-expense")) {
                    addExpenseRequest(input, expenseTracker);
                } else if (input.startsWith("add-category")) {
                    expenseTracker.addCategory(input);
                }else if (input.startsWith("delete-expense")) {
                    deleteExpenseRequest(input, expenseTracker);
                } else if (input.startsWith("tag-expense")) {
                    expenseTracker.tagExpense(input);
                } else if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Goodbye! (｡•‿•｡) Hope to see you again soon!");
                    break;
                } else if (input.equalsIgnoreCase("view-budget")) {
                    expenseTracker.viewBudget();
                } else if (input.startsWith("set-budget")) {
                    setBudgetLimitRequest(input, expenseTracker);
                } else if (input.startsWith("view-expenses")) {
                    expenseTracker.viewExpensesByCategory();
                } else if (input.equalsIgnoreCase("toggle-reset")) {
                    expenseTracker.toggleAutoReset();
                } else {
                    System.out.println("Invalid input! Try again."); // Provide feedback for invalid input
                }
            } else {
                System.out.println("No input received.");
                break;
            }
        }
    }


    public static void addExpenseRequest(String input, ExpenseTracker expenseTracker) {
        try {
            String[] parts = input.split(" ");

            String name = null;
            double amount = 0;
            String category = null;


            for (String part: parts) {
                if (part.startsWith("n/")) {
                    name = part.substring(2).trim();
                } else if (part.startsWith("a/")) {
                    amount = Double.parseDouble(part.substring(2).trim());
                } else if (part.startsWith("c/")) {
                    category = part.substring(2).trim();
                }
            }

            if (name == null || amount == 0) {
                System.out.println("Invalid input! Please provide category and limit.");
                return;
            }

            expenseTracker.addExpense(name, amount, category);

        } catch (Exception e) {
            System.out.println("Error parsing the input. Please use correct format for add-expense commands.");
            return;
        }
    }

    public static void setBudgetLimitRequest(String input, ExpenseTracker expenseTracker) {
        try {
            String[] parts = input.split(" ");

            double limit = 0;
            String category = null;


            for (String part: parts) {
                if (part.startsWith("c/")) {
                    category = part.substring(2).trim();
                } else if (part.startsWith("l/")) {
                    limit = Double.parseDouble(part.substring(2).trim());
                }
            }

            if (category == null || limit == 0) {
                System.out.println("Invalid input! Please provide name and amount.");
                return;
            }

            expenseTracker.setBudgetLimit(category, limit);

        } catch (Exception e) {
            System.out.println("Error parsing the input. Please use correct format for add-expense commands.");
        }
    }

    public static void deleteExpenseRequest(String input, ExpenseTracker expenseTracker) {
        try {
            String[] parts = input.split(" ");
            if (parts.length < 2 || !parts[1].startsWith("e/")) {
                System.out.println("Invalid input! Please provide an expense index to delete.");
                return;
            }
            int expenseIndex = Integer.parseInt(parts[1].substring(2).trim()) - 1; // 1-based index
            expenseTracker.deleteExpense(expenseIndex);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing the expense index. Please use correct " +
                    "format for delete-expense commands.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }



}
