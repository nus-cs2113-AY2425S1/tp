package seedu.duke.data.hospital;

import seedu.duke.data.task.TaskList;

public class Patient {
    private String name;
    private TaskList taskList;

    // Required for JSON deserialization
    public Patient() {
        this.name = "";
        this.taskList = new TaskList();
    }

    public Patient(String name) {
        this.name = name;
        this.taskList = new TaskList();
    }

    public Patient(String name, int index) {
        this.name = name;
        this.taskList = new TaskList();
    }

    public Patient(String name, TaskList taskList) {
        this.name = name;
        this.taskList = taskList;
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
