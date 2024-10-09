package seedu.duke;

import seedu.commands.Command;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The EasInternship class contains the main method which is the entry point for the application.
 * It manages the application's main loop, where user input is read, commands are parsed,
 * and the task list is displayed and updated accordingly.
 */
public class EasInternship {
    /**
     * The main method initializes the UI and command handlers, and continuously
     * prompts the user for input until an exit command is given.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        InternshipList internshipList = new InternshipList();
        Parser parser = new Parser();

        ui.showWelcome();

        // Main loop
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                ui.showGoodbye();
                isExit = true;
                continue;
            }

            Command command = parser.parseCommand(input);

            if (command == null) {
                System.out.println("Unknown command: " + input);
                continue;
            }

            command.setInternshipList(internshipList);
            ArrayList<String> commandArgs = parser.parseData(command, input);

            try {
                command.execute(commandArgs);
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }
    }
}
