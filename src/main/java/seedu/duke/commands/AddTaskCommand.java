package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.data.task.Task;
import seedu.duke.data.task.TaskList;

public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list";
    
    public static final Logger logger = Logger.getLogger(AddTaskCommand.class.getName());

    private final Task toAdd;

    static {
        logger.setLevel(Level.SEVERE); // Only show warnings and errors
    }
    public AddTaskCommand(String description) {
        assert description != null : "Task to add should not be null";
        
        this.toAdd = new Task(description);
    }
    public AddTaskCommand(Task task) {
        this.toAdd = task;
    }

    @Override
    public CommandResult execute() {
        try {
            tasks.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (TaskList.DuplicateTaskException e) {
            logger.log(Level.SEVERE, "Duplicate task detected: {0}", toAdd);
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }
    
}
