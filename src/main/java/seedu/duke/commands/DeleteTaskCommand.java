package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.data.task.TaskList;


public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Task deleted successfully!";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task not found in the list!";
    
    private static final Logger logger = Logger.getLogger(DeleteTaskCommand.class.getName());
    private final int index;
    public DeleteTaskCommand(int index) {
        this.index = index - 1;
    }

    static {
        logger.setLevel(Level.SEVERE);
    }

    @Override
    public CommandResult execute() {
        assert index >= 0 : "Index should be non-negative";
        assert tasks != null : "Task list should not be null";
        
        try {
            tasks.deleteTask(index);
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (TaskList.TaskNotFoundException e) {
            logger.log(Level.SEVERE, "Attempted to delete a task at an invalid index: {0}", index);
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
    }
}
