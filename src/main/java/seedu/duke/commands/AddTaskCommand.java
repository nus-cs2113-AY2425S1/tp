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

    private String taskType;
    private String[] args;
    private Task toAdd = null;

    static {
        logger.setLevel(Level.SEVERE); // Only show warnings and errors
    }

    public AddTaskCommand(String taskType, String... parameters) {
        this.taskType = taskType;
        this.args = parameters;
    }
    
    public AddTaskCommand(Task task) {
        this.toAdd = task;
    }

    @Override
    public CommandResult execute() {
        assert tasks != null : "Task list should not be null";
        try {
            if (toAdd == null) {
                toAdd = Task.createTask(taskType, args);
            }
            tasks.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (TaskList.DuplicateTaskException e) {
            logger.log(Level.SEVERE, "Duplicate task detected: {0}", toAdd);
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Invalid task type: {0}", taskType);
            return new CommandResult(e.getMessage());
        } catch (Task.EmptyTaskDescription e){
            logger.log(Level.SEVERE, "Empty task description");
            return new CommandResult(e.getMessage());
        } catch (Task.MissingTaskArgument e) {
            logger.log(Level.SEVERE, "Missing task argument for type: {0}", taskType);
            return new CommandResult(e.getMessage());
        }
    }
    
}
