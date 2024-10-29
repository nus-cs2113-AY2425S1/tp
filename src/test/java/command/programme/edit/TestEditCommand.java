package command.programme.edit;

import command.CommandResult;
import programme.ProgrammeList;

// A concrete subclass of EditCommand to help testing abstract EditCommand class
class TestEditCommand extends EditCommand {
    public TestEditCommand(int programmeIndex, int dayId, int exerciseId) {
        super(programmeIndex, dayId, exerciseId);
    }

    public TestEditCommand(int programmeIndex, int dayId) {
        super(programmeIndex, dayId);
    }

    public TestEditCommand(int programmeIndex) {
        super(programmeIndex);
    }

    @Override
    public CommandResult execute(ProgrammeList programmes) {
        return new CommandResult("Executed");
    }
}
