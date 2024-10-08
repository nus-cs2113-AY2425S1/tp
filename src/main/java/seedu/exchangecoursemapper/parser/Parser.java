package seedu.exchangecoursemapper.parser;

import seedu.exchangecoursemapper.command.ListSchoolCommand;

import java.util.Scanner;

public class Parser {
    public static final String LISTINGSCHOOLS = "list schools";

    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void processUserInput(String userInput) {
        if (userInput.startsWith(LISTINGSCHOOLS)) {
            new ListSchoolCommand().execute(userInput);
        } else {
            System.out.println(userInput);
        } // Add your parts here
    }
}
