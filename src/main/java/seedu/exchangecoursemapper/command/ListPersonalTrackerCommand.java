package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.storage.CourseRepository;
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


/**
 * Command to list all mapped courses from the user's personal tracker.
 * This command checks the validity of the data file, removes any duplicate entries,
 * and displays the mapped courses if they exist.
 */
public class ListPersonalTrackerCommand extends CheckInformationCommand {

    private static final Logger logger = Logger.getLogger(ListPersonalTrackerCommand.class.getName());
    private static final CourseRepository courseRepository = new CourseRepository();
    private static final UI ui = new UI();
    private final Storage storage;

    /**
     * Constructs a ListPersonalTrackerCommand with the specified storage.
     *
     * @param storage The storage to be used for loading and displaying courses.
     * @throws AssertionError if the storage is null.
     */
    public ListPersonalTrackerCommand(Storage storage) {
        assert storage != null : NULL_STORAGE;
        this.storage = storage;
        logger.log(Level.INFO, INIT_STORAGE_LIST_PT);
    }

    /**
     * Executes the ListPersonalTrackerCommand by validating the data file, removing duplicates,
     * and displaying mapped courses if any exist.
     *
     * @param userInput The user input provided for the command.
     */
    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, EXECUTE);
        try {
            if (!courseRepository.isFileValid()) {
                return;
            }
            courseRepository.removeDuplicateEntries();
            List<Course> mappedModules = storage.loadAllCourses();
            assert mappedModules != null : NULL_LIST;

            if (mappedModules.isEmpty()) {
                logger.log(Level.INFO, NO_MODULES);
                ui.printNoMappedModules();
            } else {
                logger.log(Level.INFO, DISPLAY_MODULES);
                ui.printMappedModules(mappedModules);
            }

            logger.log(Level.INFO, EXECUTE_COMPLETE);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
