package seedu.manager;

import seedu.manager.command.Command;
import seedu.manager.event.EventList;
import seedu.manager.exception.DuplicateDataException;
import seedu.manager.exception.InvalidCommandException;
import seedu.manager.parser.Parser;
import seedu.manager.storage.FileParser;
import seedu.manager.ui.Ui;
import seedu.manager.storage.Storage;

import java.io.IOException;

public class Main {
    private static final Ui ui = new Ui();
    private static EventList events = new EventList();
    private static final String EVENT_FILE_PATH = "events.csv";
    private static final String PARTICIPANT_FILE_PATH = "participants.csv";
    private static final String ITEM_FILE_PATH = "items.csv";
    private static final Storage storage = new Storage(EVENT_FILE_PATH, PARTICIPANT_FILE_PATH, ITEM_FILE_PATH);

    /**
     * Main entry-point for the EventManagerCLI application.
     */
    public static void main(String[] args) {
        ui.greetUser();
        loadData();
        runCommandLoop();
        System.exit(0);
    }

    //@@author MatchaRRR
    /**
     * Run command loop to get command from users
     * Parse the command and execute it
     * The loop ends when ExitCommand is triggered
     */
    private static void runCommandLoop() {
        Command command;
        boolean isGettingCommands = true;
        while (isGettingCommands){
            try {
                String userCommandText = ui.getCommand();
                command = new Parser().parseCommand(userCommandText);
                command.setData(events);
                command.execute();
                ui.showOutputToUser(command);
                saveData();
                isGettingCommands = !command.getCanExit();
            } catch (InvalidCommandException | DuplicateDataException exception) {
                ui.showErrorMessageToUser(exception);
            }
        }
    }

    //@@author KuanHsienn
    /**
     * Loads events from file and handles exceptions.
     */
    private static void loadData() {
        try {
            storage.loadEvents(events);
            storage.loadParticipants(events);
            storage.loadItems(events);
            ui.showMessage("Events loaded successfully.");
        } catch (IOException exception) {
            ui.showErrorMessageToUser(exception);
        }
    }

    //@@author KuanHsienn
    /**
     * Saves events to file and handles exceptions.
     */
    private static void saveData() {
        try {
            storage.saveEvents(events);
            storage.saveParticipants(events);
            storage.saveItems(events);
            ui.showMessage("Events saved successfully.");
        } catch (IOException e) {
            ui.showErrorMessageToUser(e);
        }
    }
}
