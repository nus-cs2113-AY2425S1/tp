package seedu.duke;

import seedu.commands.Command;
import seedu.ui.Ui;

import java.util.ArrayList;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

//@@author Toby-Yu
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
        setUpLogger();
        Logger logger = Logger.getLogger("EasInternship");

        logger.log(Level.INFO, "Starting Program");

        Ui ui = new Ui();
        InternshipList internshipList = new InternshipList();
        Storage.loadFromFile(internshipList);
        Parser parser = new Parser();

        ui.showWelcome();

        // Main loop
        boolean isExit = false;

        while (!isExit) {
            System.out.print("Enter command: ");
            String input = ui.readInput();

            if (input.equals("exit")) {
                Storage.saveToFile(internshipList);
                ui.showGoodbye();
                isExit = true;
                continue;
            }

            logger.log(Level.INFO, "Handling Command");
            Command command = parser.parseCommand(input);

            if (command == null) {
                continue;
            }

            command.setInternshipList(internshipList);
            ArrayList<String> commandArgs = parser.parseData(command, input);

            if (commandArgs == null) {
                continue;
            }

            try {
                command.execute(commandArgs);
            } catch (Exception e) {
                ui.showErrorCommand(e.getMessage());
            }
        }

        logger.log(Level.INFO, "Ending Program");
    }

    //@@author Ridiculouswifi
    private static void setUpLogger() {
        Logger logger = Logger.getLogger("EasInternship");
        logger.setUseParentHandlers(false);

        try {
            FileHandler fileHandler = new FileHandler("./data/EasInternshipLogs.txt");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
