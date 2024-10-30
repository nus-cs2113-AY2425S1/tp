// @@author TVageesan

package command.programme.edit;

import command.CommandResult;
import command.programme.ProgrammeCommand;
import history.History;
import programme.ProgrammeList;

/**
 * Abstract command class for all editing operations on a programme.
 * <p>
 * This class serves as a base for all editing commands that operate on
 * a programme, including operations to modify exercises or days within
 * a programme.
 * </p>
 */
public abstract class EditCommand extends ProgrammeCommand {

    public static final String COMMAND_WORD = "edit";

    protected int exerciseId;

    /**
     * Constructs an EditCommand with the specified programme index, day ID, and exercise ID.
     *
     * @param programmeIndex the index of the programme being edited
     * @param dayId the ID of the day being edited within the programme
     * @param exerciseId the ID of the exercise being edited
     */
    public EditCommand(int programmeIndex, int dayId, int exerciseId) {
        super(programmeIndex, dayId);
        assert exerciseId >= 0 : "exercise id must be non-negative";
        this.exerciseId = exerciseId;
    }

    /**
     * Constructs an EditCommand with the specified programme index and day ID.
     *
     * @param programmeIndex the index of the programme being edited
     * @param dayId the ID of the day being edited within the programme
     */
    public EditCommand(int programmeIndex, int dayId) {
        super(programmeIndex, dayId);
    }

    /**
     * Constructs an EditCommand with the specified programme index.
     *
     * @param programmeIndex the index of the programme being edited
     */
    public EditCommand(int programmeIndex) {
        super(programmeIndex);
    }

    /**
     * Executes the edit command on the given ProgrammeList.
     * <p>
     * This method must be implemented by subclasses to define specific
     * editing behavior.
     * </p>
     *
     * @param programmes the ProgrammeList containing the programmes to edit
     * @return a CommandResult indicating the outcome of the edit operation
     */
    public abstract CommandResult execute(ProgrammeList programmes);

    @Override
    public CommandResult execute(ProgrammeList programmes, History history) {
        return execute(programmes);
    }
}
