package seedu.javaninja;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * The Cli class is responsible for managing all interactions with the user via the command-line interface.
 * It provides methods for reading user input and printing messages, options, help text, and results to the console.
 */
public class Cli {
    private static final String ENCLOSURE = "----------------------";  // Separator line for console formatting
    private Scanner scanner;  // Scanner for reading user input

    public Cli() {
        scanner = new Scanner(System.in);
    }

    public Cli(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public void closeScanner() {
        scanner.close();
    }

    public String readInput() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public void printStartMessage() {
        System.out.println("Welcome to Java Ninja!");
        printHelp();
    }

    public void printHelp() {
        System.out.println("List of available commands:");
        System.out.println("'view' to view topics");
        System.out.println("'select /d timed/untimed /t [topic]/random' to select a topic or random topics");
        System.out.println("'review' to review past results (default sorted by newest)");
        System.out.println("'review highest/lowest/oldest/newest' to sort results");
        System.out.println("'review t/[TOPIC]' to view results of a specific topic");
        System.out.println("'review t/[TOPIC] highest/lowest/oldest/newest' to sort");
        System.out.println("'add Flashcards /q <DESCRIPTION> /a <ANSWER>' to input for your own revision as well!");
        System.out.println("'help' for this list of commands");
        System.out.println("'exit' to exit the quiz, activate only while doing the quiz");
        System.out.println("'quit' to exit the program");
    }

    public void printGoodByeMessage() {
        System.out.println("Thank you for using Java Ninja!");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printOptions(List<String> options) {
        System.out.println("Options: ");
        for (String option : options) {
            System.out.println("- " + option);
        }
    }

    public void printPastResults(String results) {
        System.out.println(results);
    }

    public void printEnclosure() {
        System.out.println(ENCLOSURE);
    }
}