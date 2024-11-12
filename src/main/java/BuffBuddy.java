// @@author TVageesan
import command.Command;
import command.CommandResult;
import command.ExitCommand;
import storage.Storage;
import history.History;
import parser.Parser;
import ui.Ui;
import programme.ProgrammeList;
import java.util.NoSuchElementException;

/**
* Represents the main class of the BuffBuddy application, a fitness tracking
* program designed to manage user commands, interact with storage, display UI messages,
* and maintain a history of commands and user programs.
*/

public class BuffBuddy {
    private static final String DEFAULT_FILE_PATH = "./data/data.json";

    private final Ui ui;
    private final History history;
    private final ProgrammeList programmes;
    private final Storage storage;
    private final Parser parser;

    public BuffBuddy(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        programmes = storage.loadProgrammeList();
        history = storage.loadHistory();
        ui.showMessage(storage.getMessage());
    }

    /**
    * Main entry point for the BuffBuddy application.
    * Initializes a BuffBuddy instance with the default file path and
    * starts the main command handling loop.
    *
    * @param args Command-line arguments (unused).
    */

    public static void main(String[] args) {
        new BuffBuddy(DEFAULT_FILE_PATH).run();
    }
    
    /**
    * Runs the main program loop for BuffBuddy, displaying a welcome message,
    * handling user commands, and displaying a farewell message upon exit.
    */
    
    public void run() {
        ui.showWelcome();
        handleCommands();
        ui.showFarewell();
    }

    /**
    * Handles the command processing loop, reading commands from the user,
    * parsing them, executing the corresponding actions, and saving data.
    * Exits the loop when an ExitCommand is issued.
    */
    
    private void handleCommands() {
        while(true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);
                CommandResult result = command.execute(programmes, history);
                ui.showMessage(result);
                if (command instanceof ExitCommand) {
                    return;
                }
                storage.saveData(programmes,history);
            }  catch (Exception e) {
                // NoSuchElementException occurs on CTRL + C exit of BuffBuddy, and thus should not be printed
                if (e instanceof NoSuchElementException) {
                    continue;
                }
                ui.showMessage(e);
            }
        }
    }
}

