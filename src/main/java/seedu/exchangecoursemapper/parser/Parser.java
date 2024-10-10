package seedu.exchangecoursemapper.parser;

import seedu.exchangecoursemapper.command.FilterCoursesCommand;
import seedu.exchangecoursemapper.command.ListCommandsCommand;
import seedu.exchangecoursemapper.command.ListSchoolCommand;
import seedu.exchangecoursemapper.command.ListUniCoursesCommand;

import java.util.Scanner;

import static seedu.exchangecoursemapper.constants.Commands.COMMANDS;
import static seedu.exchangecoursemapper.constants.Commands.LISTINGSCHOOLS;
import static seedu.exchangecoursemapper.constants.Commands.FILTER_COURSES;
import static seedu.exchangecoursemapper.constants.Commands.SET;

public class Parser {
    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void processUserInput(String userInput) {
        if (userInput.startsWith(LISTINGSCHOOLS)) {
            new ListSchoolCommand().execute(userInput);
        } else if (userInput.startsWith(COMMANDS)) {
            new ListCommandsCommand().execute(userInput);
        } else if (userInput.startsWith(FILTER_COURSES)) {
            new FilterCoursesCommand().execute(userInput);
        } else if (userInput.startsWith(SET)) {
            new ListUniCoursesCommand().execute(userInput);
        } else { // Add your parts here
            System.out.println(userInput);
        }
    }
}
