package seedu.javaninja;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JavaNinja {
    private Cli cli;
    private Parser parser;
    private QuizManager quizManager;

    public JavaNinja() {
        this.cli = new Cli();
        this.quizManager = new QuizManager(cli);
        this.parser = new Parser(quizManager, cli);
    }

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

    public static void main(String[] args) {
        Logger rootLogger = Logger.getLogger(""); // Get the root logger
        rootLogger.setLevel(Level.OFF);
        new JavaNinja().run();
    }
}
