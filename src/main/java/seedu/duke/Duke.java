package seedu.duke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        Scanner in = new Scanner(System.in);
        System.out.println("Hiya! How can I assist?");
        while (true) {
            String input = in.nextLine().trim();
            if (input.startsWith("add category")) {
                expenseTracker.addCategory(input);
    }
}
