import com.google.gson.JsonObject;

import command.Command;
import history.History;
import storage.Storage;
import ui.Ui;
import parser.Parser;
import programme.ProgrammeList;

public class BuffBuddy {
    private static final String DEFAULT_FILE_PATH = "./data/data.json";
    private final Ui ui;
    private final Storage storage;
    private final History history;
    private final ProgrammeList pList;
    private final Parser commandParser;


    public BuffBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        commandParser = new Parser();
        pList = loadProgrammeList();
        history = loadHistory();
    }

    private ProgrammeList loadProgrammeList() {
        try {
            JsonObject programmeListJson = storage.loadProgrammeList();
            return ProgrammeList.fromJson(programmeListJson);
        } catch (Exception e) {
            return new ProgrammeList();
        }
    }

    private History loadHistory() {
        try {
            JsonObject historyJson = storage.loadHistory();
            return History.fromJson(historyJson);
        } catch (Exception e) {
            return new History();
        }
    }

    public static void main(String[] args) {
        new BuffBuddy(DEFAULT_FILE_PATH).run();
    }

    public void run() {
        ui.showWelcome();
        handleUserCommands();
        ui.showFarewell();
        saveData();
    }

    private void handleUserCommands() {
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = commandParser.parse(fullCommand);
                if (command.isExit()) {
                    return;
                }
                command.execute(ui, pList, history);
            } catch (Exception e) {
                ui.showError(e);
            }
        }
    }

    private void saveData() {
        try {
            storage.save(pList, history);
        } catch (Exception e) {
            ui.showError(e);
        }
    }
}
