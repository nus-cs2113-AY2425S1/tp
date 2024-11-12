package command;

import studentlist.StudentList;
import ui.UI;

public class GenerateCommand extends Command {
    private UI ui;

    public GenerateCommand(StudentList studentList, UI ui) {
        super(studentList);
        this.ui = ui;
    }

    @Override
    public void run() {
        super.studentList.generateReport();
    }
}
