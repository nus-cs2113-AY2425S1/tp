package seedu.javaninja;

import java.io.IOException;

public class JavaNinja {
    private Cli cli;
    private Parser parser;
    private QuizManager quizManager;

    public JavaNinja() {
        this.cli = new Cli();
        this.quizManager = new QuizManager();
        this.parser = new Parser(quizManager, cli);
    }

    public void run() {
        cli.printStartMessage();
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
        new JavaNinja().run();
    }
}
