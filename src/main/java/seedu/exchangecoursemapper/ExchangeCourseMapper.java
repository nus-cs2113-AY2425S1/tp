package seedu.exchangecoursemapper;

import seedu.exchangecoursemapper.parser.Parser;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeCourseMapper {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Storage savedCourses;

    public static void main(String[] args) {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.WARNING);

        runMapper();
    }

    private static void runMapper() {
        Parser parser = new Parser();
        UI mapperUI = new UI();
        savedCourses = new Storage();

        mapperUI.displayGreeting();
        mapperUI.runChat(parser, savedCourses);
    }
}
