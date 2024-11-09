package seedu.javaninja;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main entry point for the JavaNinja application, providing a command-line interface for
 * interacting with the application. Initializes core components (CLI, Parser, and QuizManager)
 * and manages the main application loop.
 */
public class JavaNinja {
    private Cli cli;                  // Handles user input/output operations
    private Parser parser;            // Interprets user commands
    private QuizManager quizManager;  // Manages quiz-related functionalities

    /**
     * Constructs a new JavaNinja application instance by initializing the CLI, QuizManager, and Parser.
     */
    public JavaNinja() {
        this.cli = new Cli();
        this.quizManager = new QuizManager(cli);
        this.parser = new Parser(quizManager, cli);
    }

    /**
     * Starts the application, entering a loop to handle user commands until "quit" is received.
     * Displays start and exit messages, and manages saving results upon exit.
     */
    public void run() {
        cli.printEnclosure();
        cli.printStartMessage();
        cli.printEnclosure();
        while (true) {
            try {
                String input = cli.readInput().trim();
                if (input.equals("quit")) {
                    break;
                }
                parser.determineCommand(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        quizManager.saveResults();
        cli.printGoodByeMessage();
        cli.closeScanner();
    }

    /**
     * The main method, which serves as the application’s entry point. Configures logging and
     * initiates the JavaNinja application.
     *
     * @param args Command-line arguments, not utilized in this application.
     */
    public static void main(String[] args) {
        Logger rootLogger = Logger.getLogger(""); // Get the root logger
        rootLogger.setLevel(Level.OFF);
        new JavaNinja().run();
    }
}
