package seedu.duke.commands;

import seedu.duke.data.task.Task;
import seedu.duke.data.task.TaskList;

public class AddTaskCommand extends TaskCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list";
    private final Task toAdd;
    public AddTaskCommand(String description) {
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
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }
    
}
