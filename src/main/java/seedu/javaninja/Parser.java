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
        switch (processCommand(input)) {
        case "view":
            cli.printEnclosure();
            cli.printOptions(quizManager.getQuizzesAvailable());
            cli.printEnclosure();
            break;
        case "select":
            String[] parts = input.split(" ", 2);
            if (parts.length > 1) {
                quizManager.selectQuizToAttempt(parts[1].trim());
            } else {
                cli.printMessage("Please provide a topic to select.");
            }
            break;
        case "review":
            cli.printEnclosure();
            cli.printMessage("Reviewing your past results:");
            cli.printPastResults(quizManager.getPastResults());
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

    public String processCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }
}
