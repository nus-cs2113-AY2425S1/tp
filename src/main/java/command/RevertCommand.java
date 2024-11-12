package command;

import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.SEPEmptyException;
import studentlist.StudentList;
import ui.Messages;
import ui.UI;
import university.UniversityRepository;

/**
 * Command to revert the allocation status of all students in the StudentList.
 * Resets each student's allocation status and associated university index
 * to indicate no allocation, allowing for a fresh allocation process.
 */
public class RevertCommand extends Command {
    private static final Logger logger = Logger.getLogger(RevertCommand.class.getName());
    /**
     * Reference to the UI object used for displaying messages to the user.
     */
    private UI ui;


    /**
     * Constructs a RevertCommand with the specified student list and user interface.
     *
     * @param studentList The reference to the StudentList object.
     * @param ui          The reference to the UI object.
     */
    public RevertCommand(StudentList studentList, UI ui) {
        super(studentList);
        assert studentList != null : "StudentList cannot be null";
        this.ui = ui;
    }

    /**
     * Executes the revert command, resetting the allocation status of all students
     * in the list to their unallocated state. Upon completion, a confirmation message
     * is displayed to the user. Also logs the start and completion of the revert operation.
     */
    @Override
    public void run() {
        logger.log(Level.FINE, "Starting revert operation.");

        // Ensure studentList is initialized
        assert studentList != null : "StudentList cannot be null during revert operation";

        try {
            // Perform the revert action on student list and uni repo
            studentList.revertAllocation();
            UniversityRepository.resetMap();

            // Log completion and notify the user
            logger.log(Level.FINE, "Revert operation completed.");
            ui.printResponse(Messages.REVERT_COMPLETE);
        } catch (SEPEmptyException e) {
            ui.printResponse(e.getMessage());
        }
    }
}
