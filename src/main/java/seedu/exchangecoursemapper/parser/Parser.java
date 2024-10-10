package seedu.exchangecoursemapper.parser;

import seedu.exchangecoursemapper.command.FilterCoursesCommand;
import seedu.exchangecoursemapper.command.ListCommandsCommand;
import seedu.exchangecoursemapper.command.ListSchoolCommand;
import seedu.exchangecoursemapper.command.AddCoursesCommand;

import java.util.Scanner;

public class Parser {
    public static final String LISTINGSCHOOLS = "list schools";
    public static final String COMMANDS = "commands";
    public static final String FILTER_COURSES = "filter";
    public static final String ADD_COURSES = "add";

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
        } else if (userInput.startsWith(ADD_COURSES)) {
            new AddCoursesCommand().execute(userInput);
        } else { // Add your parts here
            System.out.println(userInput);
        }
    }
}
