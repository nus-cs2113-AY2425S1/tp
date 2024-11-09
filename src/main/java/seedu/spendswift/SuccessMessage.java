package seedu.spendswift;

import seedu.spendswift.command.Category;
import seedu.spendswift.command.Expense;

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

    public static void printExpensesByCategory(List<Expense> expenses) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Expenses grouped by categories:");

        Map<Category, List<Integer>> expenseIndexesByCategory = new HashMap<>();

        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            Category category = expense.getCategory();

            expenseIndexesByCategory.putIfAbsent(category, new ArrayList<>());
            expenseIndexesByCategory.get(category).add(i);
        }

        for (Category category : expenseIndexesByCategory.keySet()) {
            System.out.println("Category: " + category);
            List<Integer> expenseIndexes = expenseIndexesByCategory.get(category);

            for (Integer index : expenseIndexes) {
                Expense expense = expenses.get(index);
                System.out.println(" " + expense + " [" + (index + 1) + "] ");
            }
        }
        System.out.println(UI.SEPARATOR);
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
}
