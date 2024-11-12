package seedu.duke.flashutils.utils;

import java.util.Scanner;

/**
 * A class responsible for handling user interaction and displaying information to the console.
 * It provides methods to display responses, get user input, and format the output.
 */
public class Ui {
    private static Scanner scanner;
    private static final String LINE_SEPARATOR = "_".repeat(50);

    /**
     * Initializes the scanner object for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays all available commands for users
     */
    public static void displayCommands() {
        String availableCommands = "Type help to view all the available commands";
        System.out.println(availableCommands);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints welcome message when users enter the app 
     * The welcome message includes the app's logo and introduction
     */
    public static void welcomeMessage() {
        String logo = "FlashBang"; 
        String intro = "Welcome to the FlashBang app - learning your modules through engaging flashcards";
        System.out.println(logo + "\n" + intro);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints a formatted response to the console with line separators.
     *
     * @param text The message to be printed.
     */
    public static void printResponse(String text) {
        text = LINE_SEPARATOR +  "\n" + text + "\n" + LINE_SEPARATOR + "\n";
        System.out.print(text);
    }

    /**
     * Retrieves a user's input from the console.
     *
     * @return The raw string input from the user.
     */
    public static String getRequest() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * Prints the announcement to get the prompt from user
     * @param prompt
     */
    public static void displayGetNewPromptFromUser(String prompt) {
        System.out.println("Enter new "+prompt+" :");
    }

    /**
     * Prints the confirmation question
     * @param prompt
     */
    public static void displayConfirmationQuestion(String prompt) {
        System.out.println("Do you want to change "+prompt+" (y/n):");
    }

    /**
     * Prints the old value
     * @param prompt
     * @param value
     */
    public static void displayOldStoredValue(String prompt, String value) {
        System.out.println("Old "+prompt+" : "+value);
    }

}
