package seedu.duke.parser;

import java.util.Scanner;

public class Parser {
    private Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "";
        }
    }

    public void processUserInput(String userInput) {
        if (userInput.startsWith("list schools")) {
            System.out.println(userInput);
        } // Add your parts here
        else{
            System.out.println(userInput);
        }
    }
}
