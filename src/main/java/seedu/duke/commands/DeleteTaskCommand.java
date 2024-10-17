package seedu.duke.commands;

import seedu.duke.data.task.TaskList;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Task deleted successfully!";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task not found in the list!";
    
    private final int index;
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            tasks.deleteTask(index);
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (TaskList.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
    }
}
