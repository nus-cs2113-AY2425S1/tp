package seedu.duke.commands;

import seedu.duke.data.task.TaskList;

public abstract class Command {
    public abstract CommandResult execute();
    protected TaskList tasks;
    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
