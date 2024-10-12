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
                if (input.startsWith("add category")) {
                    expenseTracker.addCategory(input);
                } else if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Goodbye! (｡•‿•｡) Hope to see you again soon!");
                    break;
                }
            } else {
                System.out.println("Invalid input! Try again.");
                break;
            }
        }
    }
}
