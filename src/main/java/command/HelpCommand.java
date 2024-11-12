package command;

import studentlist.StudentList;
import ui.Messages;
import ui.UI;

public class HelpCommand extends Command {
    private UI ui;

    public HelpCommand(StudentList studentList, UI ui) {
        super(studentList);
        this.ui = ui;
    }

    @Override
    public void run() {
        this.ui.printResponse(Messages.HELP);
    }
}
