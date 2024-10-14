package seedu.duke.data.hospital;

import seedu.duke.data.task.TaskList;

public class Patient {
    private String name;
    private TaskList taskList;

    public Patient(String name, int index) {
        this.name = name;
        this.taskList = new TaskList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Patient{name='" + name + "'}";
    }
}