package command;

import studentlist.StudentList;
import ui.UI;

public class ExitCommand extends Command {
    private UI ui;

    public ExitCommand(StudentList studentList, UI ui) {
        super(studentList);
        this.ui = ui;
    }

    @Override
    public void run() {
        this.ui.cleanupAndExit();
    }
}
