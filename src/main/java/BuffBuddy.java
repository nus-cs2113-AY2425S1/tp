// @@author TVageesan
import command.Command;
import command.CommandResult;
import command.ExitCommand;
import storage.Storage;
import history.History;
import parser.Parser;
import ui.Ui;
import programme.ProgrammeList;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuffBuddy {
    private static final String DEFAULT_FILE_PATH = "./data/data.json";

    private final Ui ui;
    private History history;
    private ProgrammeList programmes;
    private final Storage storage;
    private final Parser parser;

    public BuffBuddy(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            programmes = storage.loadProgrammeList();
            history = storage.loadHistory();
        } catch (IOException e) {
            ui.showMessage("unable to load, initialising empty data");
            programmes = new ProgrammeList();
            history = new History();
        }
    }

    public static void main(String[] args) {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.OFF);
        new BuffBuddy(DEFAULT_FILE_PATH).run();
    }

    public void run() {
        ui.showWelcome();
        handleCommands();
        ui.showFarewell();
    }

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
            } catch (Exception e) {
                ui.showMessage(e);
            }
        }
    }
}

