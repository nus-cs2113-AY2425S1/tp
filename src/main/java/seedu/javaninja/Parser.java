package seedu.javaninja;

import java.io.IOException;

/**
 * The `Parser` class interprets user input commands and delegates the respective actions to
 * `QuizManager` and `Cli` based on the command type. It is responsible for parsing commands
 * such as "view", "select", "review", "help", and "add".
 */
public class Parser {
    private QuizManager quizManager; // Manages quiz operations
    private Cli cli;                 // Handles user interaction and output display

    /**
     * Constructs a Parser instance with specified `QuizManager` and `Cli` instances.
     *
     * @param quizManager The `QuizManager` instance used for quiz operations.
     * @param cli The `Cli` instance used for user input and output.
     */
    public Parser(QuizManager quizManager, Cli cli) {
        this.quizManager = quizManager;
        this.cli = cli;
    }

    /**
     * Determines and processes the command based on the user's input.
     * Depending on the command, it invokes appropriate methods in `QuizManager` or `Cli`.
     *
     * @param input The raw input string entered by the user.
     * @throws IOException if an input or output exception occurs.
     */
    public void determineCommand(String input) throws IOException {
        switch (processCommand(input)) {
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

    /**
     * Processes the input command by extracting the main command word and converting it to lowercase.
     *
     * @param input The raw input string entered by the user.
     * @return The lowercase main command word, used for command determination.
     */
    public String processCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }
}
