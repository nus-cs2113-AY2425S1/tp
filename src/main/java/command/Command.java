package command;

import exceptions.SEPEmptyException;
import studentlist.StudentList;

/**
 * An abstract class representing a command that can be executed by the application.
 * Subclasses should override the run() method to provide the implementation of the
 * command.
 */
public abstract class Command {
    protected StudentList studentList;

    public Command(StudentList studentList) {
        this.studentList = studentList;
    }

    /**
     * Executes the command.
     */
    public abstract void run() throws SEPEmptyException;
}
