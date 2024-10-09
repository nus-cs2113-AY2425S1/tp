package seedu.manager;

import seedu.manager.command.Command;
import seedu.manager.command.CommandOutput;
import seedu.manager.event.EventList;
import seedu.manager.parser.Parser;
import seedu.manager.ui.Ui;

public class Main {
    private static final Ui ui = new Ui();
    private static final EventList events = new EventList();

    /**
     * Main entry-point for the EventManagerCLI application.
     */
    public static void main(String[] args) {
        ui.greetUser();
        runCommandLoop();
        System.exit(0);
    }

    /**
     * Run command loop to get command from users
     * Parse the command and execute it
     * The loop ends when ExitCommand is triggered
     */
    private static void runCommandLoop() {
        Command command;
        boolean isGettingCommands = true;
        while(isGettingCommands){
            String userCommandText = ui.getCommands();
            command = new Parser().parseCommand(userCommandText);
            command.setData(events);
            CommandOutput output = command.execute();
            ui.showOutputToUser(output);

            isGettingCommands = !output.getCanExit();
        }
    }
}
