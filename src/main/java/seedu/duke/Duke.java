package seedu.duke;

import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        runMapper();
    }

    private static void runMapper() {
        Parser parser = new Parser();
        UI mapperUI = new UI();

        mapperUI.displayGreeting();
        mapperUI.runChat(parser);
    }
}
