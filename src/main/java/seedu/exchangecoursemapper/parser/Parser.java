package seedu.exchangecoursemapper.parser;

import seedu.exchangecoursemapper.command.FilterCoursesCommand;
import seedu.exchangecoursemapper.command.ListCommandsCommand;
import seedu.exchangecoursemapper.command.ListSchoolCommand;
import seedu.exchangecoursemapper.command.ListUniCoursesCommand;
import seedu.exchangecoursemapper.command.AddCoursesCommand;
import seedu.exchangecoursemapper.command.HelpCommand;
import seedu.exchangecoursemapper.command.DeleteCoursesCommand;
import seedu.exchangecoursemapper.command.ObtainContactsCommand;
import seedu.exchangecoursemapper.command.ListPersonalTrackerCommand;
import seedu.exchangecoursemapper.command.CompareMappedCommand;
import seedu.exchangecoursemapper.command.FindCoursesCommand;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Commands.COMMANDS;
import static seedu.exchangecoursemapper.constants.Commands.FILTER_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.SET;
import static seedu.exchangecoursemapper.constants.Commands.ADD_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.DELETE_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.BYE;
import static seedu.exchangecoursemapper.constants.Commands.OBTAIN;
import static seedu.exchangecoursemapper.constants.Commands.LISTING_SCHOOLS;
import static seedu.exchangecoursemapper.constants.Commands.COMMAND_WORD_INDEX;
import static seedu.exchangecoursemapper.constants.Commands.HELP;
import static seedu.exchangecoursemapper.constants.Commands.LIST_MAPPED;
import static seedu.exchangecoursemapper.constants.Commands.COMPARE_PU;
import static seedu.exchangecoursemapper.constants.Commands.FIND;
import static seedu.exchangecoursemapper.constants.Logs.RECEIVED_INPUT;
import static seedu.exchangecoursemapper.constants.Logs.NULL_INPUT;
import static seedu.exchangecoursemapper.constants.Logs.EMPTY_INPUT_DETAILS;
import static seedu.exchangecoursemapper.constants.Logs.INVALID_INPUT;
import static seedu.exchangecoursemapper.constants.Messages.INVALID_COMMAND_MESSAGE;
import static seedu.exchangecoursemapper.constants.Regex.SPACE;

import static seedu.exchangecoursemapper.constants.Logs.THE_AUSTRALIAN_NATIONAL_UNIVERSITY;
import static seedu.exchangecoursemapper.constants.Logs.THE_AUSTRALIAN_NATIONAL_UNIVERSITY_ABBREVIATION;
import static seedu.exchangecoursemapper.constants.Logs.THE_UNIVERSITY_OF_WESTERN_AUSTRALIA_ABBREVIATION ;
import static seedu.exchangecoursemapper.constants.Logs.THE_UNIVERSITY_OF_WESTERN_AUSTRALIA;
import static seedu.exchangecoursemapper.constants.Logs.THE_UNIVERSITY_OF_MELBOURNE_ABBREVIATION;
import static seedu.exchangecoursemapper.constants.Logs.THE_UNIVERSITY_OF_MELBOURNE;
import static seedu.exchangecoursemapper.constants.Logs.VICTORIA_UNIVERSITY_OF_WELLINGTON_ABBREVIATION;
import static seedu.exchangecoursemapper.constants.Logs.VICTORIA_UNIVERSITY_OF_WELLINGTON;


public class Parser {

    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    public Scanner scanner = new Scanner(System.in);

    private final UI mapperUI = new UI();

    public String getUserInput() {
        logger.setLevel(Level.WARNING);
        return scanner.nextLine();
    }

    public void processUserInput(String userInput, Storage storage) {
        logger.setLevel(Level.WARNING);
        assert userInput != null : NULL_INPUT;

        String input = userInput.trim().toLowerCase();
        String[] inputDetails = input.split(SPACE);

        logger.log(Level.INFO, RECEIVED_INPUT, input);

        assert inputDetails.length > 0 : EMPTY_INPUT_DETAILS;

        String command = inputDetails[COMMAND_WORD_INDEX];

        if (input.equals(LISTING_SCHOOLS)) {
            new ListSchoolCommand().execute(input);
        } else if (input.equals(COMMANDS)) {
            new ListCommandsCommand().execute(input);
        } else if (input.equals(LIST_MAPPED)) {
            new ListPersonalTrackerCommand(storage).execute(input);
        } else if (command.equals(FILTER_COURSES)) {
            new FilterCoursesCommand().execute(input);
        }  else if (command.equals(DELETE_COURSES)) {
            new DeleteCoursesCommand().execute(input, storage);
        } else if (command.equals(ADD_COURSES)) {
            new AddCoursesCommand().execute(input, storage);
        } else if (command.equals(SET)) {
            new ListUniCoursesCommand().execute(input);
        } else if (command.equals(HELP)) {
            new HelpCommand().execute(input);
        } else if (command.equals(OBTAIN)) {
            new ObtainContactsCommand().execute(input);
        }  else if (command.equals(COMPARE_PU)) {
            new CompareMappedCommand(storage).execute(userInput);
        } else if (command.equals(FIND)) {
            new FindCoursesCommand(storage).execute(input, storage);
        } else if (command.equals(BYE)) {
            mapperUI.displayExitMessage();
        } else {
            logger.log(Level.WARNING, INVALID_INPUT, command);
            System.out.println(INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Parses an abbreviated partner university name and returns the full name.
     * It will return the original string if no match with the formatted abbreviation is found.
     *
     * @param PU User's partner university input.
     *
     * @return the full name of the partner university if it matches a known abbreviation.
     */
    public String parsePUAbbreviations(String PU) {
        String formattedPU = PU.toLowerCase().trim();
        if (formattedPU.equals(THE_UNIVERSITY_OF_WESTERN_AUSTRALIA_ABBREVIATION)) {
            formattedPU = THE_UNIVERSITY_OF_WESTERN_AUSTRALIA;
        } else if (formattedPU.equals(THE_UNIVERSITY_OF_MELBOURNE_ABBREVIATION)) {
            formattedPU = THE_UNIVERSITY_OF_MELBOURNE;
        } else if (formattedPU.equals(THE_AUSTRALIAN_NATIONAL_UNIVERSITY_ABBREVIATION)) {
            formattedPU = THE_AUSTRALIAN_NATIONAL_UNIVERSITY;
        } else if (formattedPU.equals(VICTORIA_UNIVERSITY_OF_WELLINGTON_ABBREVIATION)) {
            formattedPU = VICTORIA_UNIVERSITY_OF_WELLINGTON;
        }
        return formattedPU;
    }
}
