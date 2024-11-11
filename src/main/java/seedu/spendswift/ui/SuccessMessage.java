package seedu.spendswift.ui;

import seedu.spendswift.format.Format;
import seedu.spendswift.model.Category;
import seedu.spendswift.model.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessMessage {
    private static final String NO_EXPENSE = "No expenses to display.";

    public static void printAddExpenseAddCategory(String categoryName) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Category '" + categoryName + "' added successfully.");
    }

    public static void printAddExpense(Expense expense) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Added" + expense);
        System.out.println(UI.SEPARATOR);
    }

    public static void printDeleteExpense(Expense expense) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Deleted expense: " + expense);
        System.out.println(UI.SEPARATOR);
    }

    public static void printNoExpense() {
        System.out.println(UI.SEPARATOR);
        System.out.println(NO_EXPENSE);
        System.out.println(UI.SEPARATOR);
    }

    public static void printExpensesByCategory(List<Expense> expenses, List<Category> categories) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Expenses grouped by categories:");

        // Map to store expenses by category
        Map<Category, List<Integer>> expenseIndexesByCategory = new HashMap<>();

        // Group expenses by category
        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            Category category = expense.getCategory();

            expenseIndexesByCategory.putIfAbsent(category, new ArrayList<>());
            expenseIndexesByCategory.get(category).add(i);
        }

        // Display expenses for each category
        for (Category category : categories) {
            System.out.println("Category: " + category);
            List<Integer> expenseIndexes = expenseIndexesByCategory.get(category);

            if (expenseIndexes == null || expenseIndexes.isEmpty()) {
                System.out.println(" No expense");
            } else {
                for (Integer index : expenseIndexes) {
                    Expense expense = expenses.get(index);
                    System.out.println(" " + expense + " [" + (index + 1) + "] ");
                }
            }
        }
    }

    public static void printTaggedExpense(Expense expense) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Tagged expense: " + expense);
        System.out.println(UI.SEPARATOR);
    }

    public static void printMissingCategory(String category) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Category '" + category + "' does not exist.");
        System.out.println(UI.SEPARATOR);
    }

    public static void printExistingCategory(String category) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Category '" + category + "' already exists!");
        System.out.println(UI.SEPARATOR);
    }

    public static void printAddCategory(Category category) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Category '" + category + "' added successfully.");
        System.out.println(UI.SEPARATOR);
    }

    public static void printNewBudget(double limit, Category category) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Set budget for category '" + category + "' to " + Format.formatAmount(limit));
        System.out.println(UI.SEPARATOR);
    }

    public static void printExistingBudget(double limit, Category category) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Updated budget for category '" + category + "' to "
                + Format.formatAmount(limit));
        System.out.println(UI.SEPARATOR);
    }

    public static void printBudgetReset(){
        System.out.println(UI.SEPARATOR);
        System.out.println("Budgets have been reset for all categories.");
        System.out.println(UI.SEPARATOR);
    }

    public static void printNoBudgetForAll(){
        System.out.println(UI.SEPARATOR);
        System.out.println("No budgets set for any category.");
        System.out.println(UI.SEPARATOR);
    }

    public static void printOverBudget(Category category, double expense, Double budget) {
        System.out.println(UI.SEPARATOR);
        System.out.println(category + ": " + Format.formatAmount(expense) + " spent, " +
                "Over budget by " + Format.formatAmount(budget));
        System.out.println(UI.SEPARATOR);
    }

    public static void printWithinBudget(Category category, double expense, double budget) {
        System.out.println(UI.SEPARATOR);
        System.out.println(category + ": " + Format.formatAmount(expense) + " spent, " +
                Format.formatAmount(budget) + " remaining");
        System.out.println(UI.SEPARATOR);
    }

    public static void printNoBudget(Category category) {
        System.out.println(UI.SEPARATOR);
        System.out.println(category + ": No budget set");
        System.out.println(UI.SEPARATOR);
    }
}
