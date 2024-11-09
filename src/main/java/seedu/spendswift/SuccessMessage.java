package seedu.spendswift;

import seedu.spendswift.command.Expense;

public class SuccessMessage {
    public static void printAddExpenseAddCategory(String categoryName) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Category '" + categoryName + "' added successfully.");
    }

    public static void printAddExpense(Expense expense) {
        System.out.println(UI.SEPARATOR);
        System.out.println("Added" + expense);
        System.out.println(UI.SEPARATOR);
    }
}
