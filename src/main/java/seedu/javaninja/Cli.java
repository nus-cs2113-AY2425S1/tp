package seedu.javaninja;

import java.util.Scanner;

/* manages input by user */
public class Cli {
    private QuizManager quizManager;

    public Cli () {
        quizManager = new QuizManager();
    }

    public void start() {
        System.out.println("Welcome to Java Ninja!");
        System.out.println("'view' to view topics");
        System.out.println("'select' to select a topic");
        System.out.println("'review' to review the past result");
        System.out.println("'quit' to exit the programme");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("quit")) {
            switch (input.toLowerCase()) {
            case "view":
                quizManager.printTopics();
                break;
            case "select":
                System.out.println("Enter topic name: ");
                String topicName = scanner.nextLine();
                quizManager.selectTopic(topicName);
                break;
            case "review":
                System.out.println("Reviewing your past results:");
                String pastResults = quizManager.getPastResults();
                System.out.println(pastResults);
                break;
            default:
                System.out.println("Invalid input");
            }
            input = scanner.nextLine();
        }
        System.out.println("Goodbye!");
    }
}
