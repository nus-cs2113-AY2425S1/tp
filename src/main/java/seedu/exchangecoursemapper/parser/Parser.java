package seedu.exchangecoursemapper.parser;

import seedu.exchangecoursemapper.command.FilterCoursesCommand;
import seedu.exchangecoursemapper.command.ListCommandsCommand;
import seedu.exchangecoursemapper.command.ListSchoolCommand;
import seedu.exchangecoursemapper.command.ListUniCoursesCommand;
import seedu.exchangecoursemapper.command.AddCoursesCommand;
import seedu.exchangecoursemapper.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Commands.COMMANDS;
import static seedu.exchangecoursemapper.constants.Commands.LISTINGSCHOOLS;
import static seedu.exchangecoursemapper.constants.Commands.FILTER_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.SET;
import static seedu.exchangecoursemapper.constants.Commands.ADD_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.BYE;
import static seedu.exchangecoursemapper.constants.Commands.COMMAND_WORD_INDEX;
import static seedu.exchangecoursemapper.constants.Messages.INVALID_COMMAND_MESSAGE;
import static seedu.exchangecoursemapper.constants.Regex.SPACE;

public class Parser {

    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    private final Scanner scanner = new Scanner(System.in);

    private final UI mapperUI = new UI();

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void processUserInput(String userInput) {
        // Assert that userInput is not null
        assert userInput != null : "User input should not be null";

        String input = userInput.trim();
        String[] inputDetails = input.split(SPACE);

        // Log user input
        logger.log(Level.INFO, "User input received: {0}", input);

        // Assert that inputDetails array is not empty
        assert inputDetails.length > 0 : "Input details should not be empty after splitting";

        String command = inputDetails[COMMAND_WORD_INDEX];

        if (input.equals(LISTINGSCHOOLS)) {
            logger.log(Level.INFO, "Executing ListSchoolCommand");
            new ListSchoolCommand().execute(input);
        } else if (command.equals(COMMANDS)) {
            logger.log(Level.INFO, "Executing ListCommandsCommand");
            new ListCommandsCommand().execute(input);
        } else if (command.equals(FILTER_COURSES)) {
            logger.log(Level.INFO, "Executing FilterCoursesCommand");
            new FilterCoursesCommand().execute(input);
        } else if (command.equals(ADD_COURSES)) {
            logger.log(Level.INFO, "Executing AddCoursesCommand");
            new AddCoursesCommand().execute(input);
        } else if (command.equals(SET)) {
            logger.log(Level.INFO, "Executing ListUniCoursesCommand");
            new ListUniCoursesCommand().execute(input);
        } else if (command.equals(BYE)) {
            logger.log(Level.INFO, "Executing displayExitMessage");
            mapperUI.displayExitMessage();
        } else {
            logger.log(Level.WARNING, "Invalid command: {0}", command);
            System.out.println(INVALID_COMMAND_MESSAGE);
        }
    }
}
