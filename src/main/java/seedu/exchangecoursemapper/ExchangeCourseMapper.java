package seedu.exchangecoursemapper;

import seedu.exchangecoursemapper.parser.Parser;
import seedu.exchangecoursemapper.ui.UI;

public class ExchangeCourseMapper {
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
