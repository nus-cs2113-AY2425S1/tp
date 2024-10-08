package seedu.duke.commands;

import seedu.duke.data.task.TaskList;

public abstract class TaskCommand extends Command {
    protected TaskList tasks;
    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
