package seedu.duke.commands;

import seedu.duke.data.task.TaskList;

public class TaskCommand extends Command {
    protected TaskList tasks;
    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
    @Override
    public CommandResult execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
