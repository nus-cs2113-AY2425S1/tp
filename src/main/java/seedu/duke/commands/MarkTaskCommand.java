package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.data.task.TaskList;

public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_SUCCESS = "Task marked successfully: %1$s";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task not found in the list!";
    
    private static final Logger logger = Logger.getLogger(MarkTaskCommand.class.getName());

    private final int index;

    static {
        logger.setLevel(Level.SEVERE);
    }

    public MarkTaskCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public CommandResult execute() {
        assert index >= 0 : "Index should be non-negative";
        assert tasks != null : "Task list should not be null";
        
        try {
            tasks.markAsDone(index);
            return new CommandResult(String.format(MESSAGE_SUCCESS, tasks.getTask(index)));
        } catch (TaskList.TaskNotFoundException e) {
            logger.log(Level.SEVERE, "Attempted to mark a task at an invalid index: {0}", index);
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
    }
}