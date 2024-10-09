package seedu.duke;

import seedu.commands.AddCommand;
import seedu.commands.Command;
import seedu.commands.FilterCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.ListCommand;
import seedu.commands.SortCommand;
import seedu.commands.UpdateCommand;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * The EasInternship class contains the main method which is the entry point for the application.
 * It manages the application's main loop, where user input is read, commands are parsed,
 * and the task list is displayed and updated accordingly.
 */
public class EasInternship {
    private static final Ui ui = new Ui();
    private static final InternshipList internshipList = new InternshipList();
    private static final Map<String, Command> commands = new HashMap<>();

    /**
     * The main method initializes the UI and command handlers, and continuously
     * prompts the user for input until an exit command is given.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {

        ui.showWelcome();

        // Initialize command map
        initializeCommands();

        // Main loop
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] inputArgs = input.split("\\s+", 2);
            String commandWord = inputArgs[0].toLowerCase(); // Extract command word

            if (commandWord.equals("bye")) {
                ui.showGoodbye();
                isExit = true;
                continue;
            }

            Command command = commands.get(commandWord);
            if (command == null) {
                System.out.println("Unknown command: " + commandWord);
                continue;
            }

            try {
                String[] commandArgs = inputArgs.length > 1 ? inputArgs[1].split("\\s+") : new String[0];
                command.execute(commandArgs);
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }
    }

    /**
     * Initializes the available commands and maps them to their respective handlers.
     */
    private static void initializeCommands() {
        commands.put("add", new AddCommand(internshipList));
        commands.put("del", new DeleteCommand(internshipList));
        commands.put("update", new UpdateCommand(internshipList));
        commands.put("sort", new SortCommand(internshipList));
        commands.put("filter", new FilterCommand(internshipList));
        commands.put("list", new ListCommand(internshipList)); // Custom command to list all internships
    }
}
