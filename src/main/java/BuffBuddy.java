import command.Command;
import core.DataWrapper;
import core.History;
import parser.Parser;
import core.Ui;
import core.Storage;
import programme.ProgrammeList;


/**
 * The Medea class serves as the main application interface,
 * managing the flow of the application, including command processing,
 * task loading, and saving tasks to storage.
 */
public class BuffBuddy {
    private static final String DEFAULT_FILE_PATH = "./data/data.json";
    private final Ui userInterface;
    private final Storage storage;
    private History history;
    private final DataWrapper dataWrapper;
    private ProgrammeList pList;
    private final Parser commandParser;

    public BuffBuddy(String filePath) {
        userInterface = new Ui();
        storage = new Storage(filePath);
        commandParser = new Parser();
        pList = new ProgrammeList();
        history = new History();
        dataWrapper = new DataWrapper(pList, history);
    }


    public static void main(String[] args) {
        new BuffBuddy(DEFAULT_FILE_PATH).run();
    }

    public void run() {
        loadTasks();
        userInterface.showWelcome();
        handleUserCommands();
        userInterface.showFarewell();
        saveTasks();
    }

    private void handleUserCommands() {
        while (true) {
            try {
                String fullCommand = userInterface.readCommand();
                Command command = commandParser.parse(fullCommand);
                if (command.isExit()) {
                    return;
                }
                wrapWithLine(() -> command.execute(userInterface, pList, history));
            } catch (Exception exception) {
                wrapWithLine(() -> userInterface.showError(exception));
            }
        }
    }

    private void wrapWithLine(Runnable action) {
        userInterface.showLine();
        action.run();
        userInterface.showLine();
    }

    private void loadTasks() {
        storage.load(dataWrapper);
        pList = dataWrapper.getProgrammeList();
        history = dataWrapper.getHistory();
    }
    private void saveTasks() {
        storage.save(pList, history);
    }
}
