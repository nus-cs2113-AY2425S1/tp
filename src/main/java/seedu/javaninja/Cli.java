package seedu.javaninja;

import java.io.IOException;
import java.util.Scanner;

/* Manages input by user */
public class Cli {
    private QuizManager quizManager;

    public Cli() {
        quizManager = new QuizManager();
    }

    public void start() throws IOException {
        System.out.println("Welcome to Java Ninja!");
        System.out.println("'view' to view topics");
        System.out.println("'select' to select a topic");
        System.out.println("'review' to review the past result");
        System.out.println("'help' for a list of commands");
        System.out.println("'quit' to exit the programme");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (true) {
            String command = processCommand(input);
            if (command.equals("quit")) {
                break;
            }
            switch (command) {
            case "view":
                quizManager.printTopics();
                break;
            case "select":
                quizManager.selectTopic(input);
                break;
            case "review":
                System.out.println("Reviewing your past results:");
                String pastResults = quizManager.getPastResults();
                System.out.println(pastResults);
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
            input = scanner.nextLine();
        }
        System.out.println("Goodbye!");
    }

    public String processCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    // Help message
    private void printHelp() {
        System.out.println("List of available commands:");
        System.out.println("'view' - View available topics");
        System.out.println("'select' - Type 'select' and hit Enter. Then type a topic name (e.g.\"Loops\")");
        System.out.println("'review' - Review all your quiz results");
        System.out.println("'help' - Show this help message");
        System.out.println("'quit' - Exit the program");
    }
}
