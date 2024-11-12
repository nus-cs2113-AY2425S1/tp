package seedu.javaninja;

import java.io.IOException;

/**
 * The Parser class interprets user input commands and delegates the respective actions to
 * QuizManager and Cli based on the command type. It is responsible for parsing commands
 * such as "view", "select", "review", "help", and "add".
 */
public class Parser {
    private QuizManager quizManager; // Manages quiz operations
    private Cli cli;                 // Handles user interaction and output display

    public Parser(QuizManager quizManager, Cli cli) {
        this.quizManager = quizManager;
        this.cli = cli;
    }

    public void determineCommand(String input) throws IOException {
        String[] parts = input.split(" ");
        String mainCommand = processCommand(input);

        switch (mainCommand) {
        case "view":
            cli.printEnclosure();
            cli.printOptions(quizManager.getQuizzesAvailable());
            cli.printEnclosure();
            break;
        case "select":
            cli.printEnclosure();
            quizManager.handleQuizSelection(input);
            cli.printEnclosure();
            break;
        case "review":
            cli.printEnclosure();
            handleReviewCommand(parts);
            cli.printEnclosure();
            break;
        case "help":
            cli.printEnclosure();
            cli.printHelp();
            cli.printEnclosure();
            break;
        case "add":
            cli.printEnclosure();
            quizManager.addInput(input);
            cli.printEnclosure();
            break;
        default:
            cli.printEnclosure();
            cli.printMessage("Invalid input. Type 'help' for a list of commands.");
            cli.printEnclosure();
        }
    }

    private void handleReviewCommand(String[] parts) {
        String topic = "";
        boolean isNewestFirst = true; // Default sorting by newest
        boolean sortByScore = false;
        boolean sortByLowestScore = false; // Flag for sorting by lowest score

        for (String part : parts) {
            if (part.startsWith("t/")) {
                topic = part.substring(2);
            } else if (part.equalsIgnoreCase("newest")) {
                isNewestFirst = true;
            } else if (part.equalsIgnoreCase("oldest")) {
                isNewestFirst = false;
            } else if (part.equalsIgnoreCase("highest")) {
                sortByScore = true;
            } else if (part.equalsIgnoreCase("lowest")) {
                sortByLowestScore = true;
            }
        }

        if (!topic.isEmpty()) {
            cli.printMessage("Reviewing results for topic: " + topic);
            quizManager.reviewResultsByTopic(topic, isNewestFirst, sortByScore, sortByLowestScore);
        } else {
            cli.printMessage("Reviewing all quiz results:");
            quizManager.reviewAllResults(isNewestFirst, sortByScore, sortByLowestScore);
        }
    }

    public String processCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }
}
