package seedu.duke.data.hospital;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String name;
    private int index;
    //private TaskList tasks;

    public Patient(String name, int index) {
        this.name = name;
        this.index = index;
        //this.tasks = new TaskList();
    }

    public String getName() {
        return name;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    /*
    public TaskList getTasks() {
        return tasks;
    }
    */
}
