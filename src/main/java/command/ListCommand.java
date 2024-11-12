package command;

import exceptions.SEPException;
import studentlist.StudentList;
import ui.UI;

public class ListCommand extends Command {
    private final UI ui;

    public ListCommand(StudentList studentList, UI ui) {
        super(studentList);
        this.ui = ui;
    }

    @Override
    public void run() {
        try {
            ui.printStudentList(studentList.getList());
        } catch (SEPException e) {
            ui.printResponse(e.getMessage());
        }
    }
}
