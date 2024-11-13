package seedu.duke.commands;

import seedu.duke.data.task.TaskList;

public abstract class Command {
    protected TaskList tasks;

    public abstract CommandResult execute();
    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
