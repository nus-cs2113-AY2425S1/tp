package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.ui.UI;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Assertions.NULL_LIST;
import static seedu.exchangecoursemapper.constants.Assertions.NULL_STORAGE;
import static seedu.exchangecoursemapper.constants.Logs.EXECUTE;
import static seedu.exchangecoursemapper.constants.Logs.INIT_STORAGE_LIST_PT;
import static seedu.exchangecoursemapper.constants.Logs.NO_MODULES;
import static seedu.exchangecoursemapper.constants.Logs.DISPLAY_MODULES;
import static seedu.exchangecoursemapper.constants.Logs.EXECUTE_COMPLETE;


public class ListPersonalTrackerCommand extends CheckInformationCommand {

    private static final Logger logger = Logger.getLogger(ListPersonalTrackerCommand.class.getName());
    private final Storage storage;
    private static final UI ui = new UI();

    public ListPersonalTrackerCommand(Storage storage) {
        assert storage != null : NULL_STORAGE;
        this.storage = storage;
        logger.log(Level.INFO, INIT_STORAGE_LIST_PT);
    }

    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, EXECUTE);
        List<String> mappedModules = storage.loadAllCourses();
        assert mappedModules != null : NULL_LIST;

        if (mappedModules.isEmpty()) {
            logger.log(Level.INFO, NO_MODULES);
            ui.printNoMappedModules();
        } else {
            logger.log(Level.INFO, DISPLAY_MODULES);
            ui.printMappedModules(mappedModules);
        }

        logger.log(Level.INFO, EXECUTE_COMPLETE);
    }
}
