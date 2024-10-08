package seedu.exchangecoursemapper.parser;

import seedu.exchangecoursemapper.command.ListSchoolCommand;

import java.util.Scanner;

public class Parser {
    private Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void processUserInput(String userInput) {
        if (userInput.startsWith("list schools")) {
            new ListSchoolCommand().execute(userInput);
        } else {
            System.out.println(userInput);
        } // Add your parts here
    }
}
