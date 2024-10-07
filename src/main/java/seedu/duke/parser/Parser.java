package seedu.duke.parser;

import java.util.Scanner;

public class Parser {
    private Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void processUserInput(String userInput) {
        if (userInput.startsWith("list schools")) {
            System.out.println(userInput);
        }
        else{
            System.out.println(userInput);
        }
    }
}
