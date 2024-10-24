package wheresmymoney;

import java.util.Scanner;


public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a message.
     *
     * @param message The message to display.
     */
    public static void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads the user input.
     *
     * @return The user input as a String.
     */
    public static String getUserInput() {
        if (!scanner.hasNextLine()) {
            System.exit(0);
        }
        return scanner.nextLine();
    }

    public static void close(){
        scanner.close();
    }

    /**
     * Displays an Introductory message to the user
     */
    public static void displayIntroText() {
        displayMessage("WheresMyMoney");
        displayMessage("What can I do for you?");
    }

    /**
     * Displays the text prompting for a command
     */
    public static void displayCommandPrompt() {
        System.out.print("> ");
    }

    /**
     * Gets a command from the user
     */
    public static String getCommand(){
        Ui.displayCommandPrompt();
        return Ui.getUserInput();
    }
}
