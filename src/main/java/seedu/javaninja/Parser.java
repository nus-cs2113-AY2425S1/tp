package seedu.javaninja;

import java.io.IOException;

public class Parser {
    private QuizManager quizManager;
    private Cli cli;

    public Parser(QuizManager quizManager, Cli cli) {
        this.quizManager = quizManager;
        this.cli = cli;
    }

    public void determineCommand(String input) throws IOException {
        try {
            switch (processCommand(input)) {
            case "view":
                quizManager.printTopics();
                break;
            case "select":
                String[] parts = input.split(" ", 2);
                if (parts.length > 1) {
                    quizManager.selectTopic(parts[1].trim());
                } else {
                    System.out.println("Please provide a topic to select.");
                }
                break;
            case "review":
                System.out.println("Reviewing your past results:");
                System.out.println(quizManager.getPastResults());
                break;
            case "help":
                cli.printHelp();
                break;
            case "add":
                quizManager.addFlashcardByUser(input);
                break;
            default:
                System.out.println("Invalid input. Type 'help' for a list of commands.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String processCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }
}
