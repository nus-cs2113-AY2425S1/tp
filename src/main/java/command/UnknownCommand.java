package command;

import studentlist.StudentList;
import ui.UI;

public class UnknownCommand extends Command {
    private UI ui;

    public UnknownCommand(StudentList studentList, UI ui) {
        super(studentList);
        this.ui = ui;
    }

    @Override
    public void run() {
        this.ui.printResponse("Invalid command");
    }
}
