import core.CommandHandler;
import core.DataAdapter;

import history.History;
import ui.Ui;
import programme.ProgrammeList;

public class BuffBuddy {
    private static final String DEFAULT_FILE_PATH = "./data/data.json";
    private final Ui ui;

    private final History history;
    private final ProgrammeList programmes;
    private final DataAdapter dataAdapter;
    private final CommandHandler commandHandler;


    public BuffBuddy(String filePath) {
        ui = new Ui();
        dataAdapter = new DataAdapter(filePath);
        programmes = dataAdapter.loadProgrammeList();
        history = dataAdapter.loadHistory();
        commandHandler = new CommandHandler();
    }

    public static void main(String[] args) {
        new BuffBuddy(DEFAULT_FILE_PATH).run();
    }

    public void run() {
        ui.showWelcome();
        commandHandler.run(ui, programmes, history);
        ui.showFarewell();
        dataAdapter.saveData(programmes, history);
    }
}
