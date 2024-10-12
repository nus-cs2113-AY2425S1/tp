package seedu.exchangecoursemapper.parser;

import seedu.exchangecoursemapper.command.FilterCoursesCommand;
import seedu.exchangecoursemapper.command.ListCommandsCommand;
import seedu.exchangecoursemapper.command.ListSchoolCommand;
import seedu.exchangecoursemapper.command.ListUniCoursesCommand;
import seedu.exchangecoursemapper.command.AddCoursesCommand;

import java.util.Scanner;

import static seedu.exchangecoursemapper.constants.Commands.COMMANDS;
import static seedu.exchangecoursemapper.constants.Commands.LISTINGSCHOOLS;
import static seedu.exchangecoursemapper.constants.Commands.FILTER_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.SET;
import static seedu.exchangecoursemapper.constants.Commands.ADD_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.BYE;
import static seedu.exchangecoursemapper.constants.Commands.COMMAND_WORD_INDEX;
import static seedu.exchangecoursemapper.constants.Messages.BYE_MESSAGE;
import static seedu.exchangecoursemapper.constants.Messages.INVALID_COMMAND_MESSAGE;
import static seedu.exchangecoursemapper.constants.Regex.SPACE;

public class Parser {

    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void processUserInput(String userInput) {
        String input = userInput.trim();
        String[] inputDetails = input.split(SPACE);
        String command = inputDetails[COMMAND_WORD_INDEX];
        if (input.equals(LISTINGSCHOOLS)) {
            new ListSchoolCommand().execute(userInput);
        } else if (command.equals(COMMANDS)) {
            new ListCommandsCommand().execute(userInput);
        } else if (command.equals(FILTER_COURSES)) {
            new FilterCoursesCommand().execute(userInput);
        } else if (command.equals(ADD_COURSES)) {
            new AddCoursesCommand().execute(userInput);
        } else if (command.equals(SET)) {
            new ListUniCoursesCommand().execute(userInput);
        } else if (command.equals(BYE)) {
            System.out.println(BYE_MESSAGE);
        } else { // Add your parts here
            System.out.println(INVALID_COMMAND_MESSAGE);
        }
    }
}
