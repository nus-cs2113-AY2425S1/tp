package seedu.javaninja;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* Manages input by user */
public class Cli {
    private QuizManager quizManager;
    private Scanner scanner;

    public Cli() {
        quizManager = new QuizManager();
        scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        System.out.println("Welcome to Java Ninja!");
        System.out.println("'view' to view topics");
        System.out.println("'select [topic]' to select a topic");
        System.out.println("'review' to review past results");
        System.out.println("'help' for a list of commands");
        System.out.println("'quit' to exit the program");
        System.out.println("'exit' to exit the quiz, activate only while doing the quiz");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String command = processCommand(input);

            if (command.equals("quit")) {
                break;
            }

            switch (command) {
            case "view":
                quizManager.printTopics();
                break;
            case "select":
                selectTopic(input);
                break;
            case "review":
                System.out.println("Reviewing your past results:");
                System.out.println(quizManager.getPastResults());
                break;
            case "help":
                printHelp();
                break;
            case "add":
                quizManager.addQuestionByUser(input);
                break;
            default:
                System.out.println("Invalid input. Type 'help' for a list of commands.");
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }

    private void selectTopic(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Please specify a topic to select.");
        } else {
            System.out.println("Set a time limit for the quiz.");
            System.out.print("Enter the number of minutes (or 0 if you want to set seconds): ");
            int minutes = Integer.parseInt(scanner.nextLine().trim());

            int seconds = 0;
            if (minutes == 0) {
                System.out.print("Enter the number of seconds: ");
                seconds = Integer.parseInt(scanner.nextLine().trim());
            } else {
                seconds = minutes * 60;  // Convert minutes to seconds
            }

            quizManager.selectTopic(parts[1], scanner, seconds);
        }
    }

    public String processCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    // Help message
    private void printHelp() {
        System.out.println("List of available commands:");
        System.out.println("'view' - View available topics");
        System.out.println("'select [topic]' - Start a quiz on a specific topic");
        System.out.println("'review' - Review all your quiz results");
        System.out.println("'help' - Show this help message");
        System.out.println("'quit' - Exit the program");
    }
}
