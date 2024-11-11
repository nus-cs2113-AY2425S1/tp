package seedu.javaninja;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * The `Cli` class is responsible for managing all interactions with the user via the command-line interface.
 * It provides methods for reading user input and printing messages, options, help text, and results to the console.
 */
public class Cli {
    private static final String ENCLOSURE = "----------------------";  // Separator line for console formatting
    private Scanner scanner;  // Scanner for reading user input

    /**
     * Default constructor that initializes the `Scanner` with `System.in` for standard input.
     */
    public Cli() {
        scanner = new Scanner(System.in);
    }

    /**
     * Constructor that allows for a custom `InputStream` for testing purposes.
     *
     * @param inputStream The input stream for `Scanner` initialization.
     */
    public Cli(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    /**
     * Closes the `Scanner` instance to release system resources.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Reads input from the user and trims any leading or trailing whitespace.
     *
     * @return The trimmed user input as a `String`.
     */
    public String readInput() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    /**
     * Prints the welcome message with available commands at the start of the application.
     */
    public void printStartMessage() {
        System.out.println("Welcome to Java Ninja!");
        printHelp();
    }

    /**
     * Prints the help message listing all available commands and their descriptions.
     */
    public void printHelp() {
        System.out.println("List of available commands:");
        System.out.println("'view' to view topics");
        System.out.println("'select /d timed|untimed /t [topic]|random' " +
            "to select a topic or random topics");
        System.out.println("'review' to review past results");
        System.out.println("'help' for this list of commands");
        System.out.println("'quit' to exit the program");
        System.out.println("'exit' to exit the quiz, activate only while doing the quiz");
        System.out.println("'add Flashcards /q <DESCRIPTION> " +
                "/a <ANSWER>' to input for your own revision as well!");
    }

    /**
     * Prints the goodbye message upon exiting the program.
     */
    public void printGoodByeMessage() {
        System.out.println("Thank you for using Java Ninja!");
    }

    /**
     * Prints a custom message to the user.
     *
     * @param message The message to be displayed to the user.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a list of options to the user, commonly used for displaying multiple-choice options.
     *
     * @param options A list of strings representing the options to be displayed.
     */
    public void printOptions(List<String> options) {
        System.out.println("Options: ");
        for (String option : options) {
            System.out.println("- " + option);
        }
    }

    /**
     * Prints past quiz results to the user.
     *
     * @param results A string representation of past results to be displayed.
     */
    public void printPastResults(String results) {
        System.out.println(results);
    }

    /**
     * Prints a separator line to visually distinguish sections in the console output.
     */
    public void printEnclosure() {
        System.out.println(ENCLOSURE);
    }
}
