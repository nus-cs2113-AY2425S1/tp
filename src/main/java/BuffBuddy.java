import command.Command;
import command.CommandResult;
import command.ExitCommand;
import core.DataManager;

import history.History;
import parser.Parser;
import ui.Ui;
import programme.ProgrammeList;

public class BuffBuddy {
    private static final String DEFAULT_FILE_PATH = "./data/data.json";
    private final Ui ui;

    private final History history;
    private final ProgrammeList programmes;
    private final DataManager dataManager;
    private final Parser parser;
    private boolean isRunning;


    public BuffBuddy(String filePath) {
        ui = new Ui();
        dataManager = new DataManager(filePath);
        programmes = dataManager.loadProgrammeList();
        history = dataManager.loadHistory();
        parser = new Parser();
        isRunning = true;
    }

    public static void main(String[] args) {
        new BuffBuddy(DEFAULT_FILE_PATH).run();
    }

    public void run() {
        ui.showWelcome();
        handleCommands();
        ui.showFarewell();
        dataManager.saveData(programmes, history);
    }

    private void handleCommands() {
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);
                CommandResult result = command.execute(programmes, history);
                ui.showMessage(result);

                if (command instanceof ExitCommand) {
                    isRunning = false;
                }

            } catch (Exception e) {
                ui.showError(e);
            }
        }
    }
}
