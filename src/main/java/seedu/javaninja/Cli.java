package seedu.javaninja;

import java.util.Scanner;

/* Manages input by user */
public class Cli {
    private Scanner scanner;

    public Cli() {
        scanner = new Scanner(System.in);
    }

    public void closeScanner() {
        scanner.close();
    }

    public String readInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void printStartMessage() {
        System.out.println("Welcome to Java Ninja!");
        System.out.println("'view' to view topics");
        System.out.println("'select [topic]' to select a topic");
        System.out.println("'review' to review past results");
        System.out.println("'help' for a list of commands");
        System.out.println("'quit' to exit the program");
        System.out.println("'exit' to exit the quiz, activate only while doing the quiz");
    }

    // Help message
    public void printHelp() {
        System.out.println("List of available commands:");
        System.out.println("'view' - View available topics");
        System.out.println("'select [topic]' - Start a quiz on a specific topic");
        System.out.println("'review' - Review all your quiz results");
        System.out.println("'help' - Show this help message");
        System.out.println("'quit' - Exit the program");
    }

    public void printGoodByeMessage() {
        System.out.println("Goodbye!");
    }
}
