package command;

import exceptions.SEPException;
import studentlist.StudentList;
import ui.UI;

public class FilterCommand extends Command{
    private String input;
    private UI ui;

    public FilterCommand(StudentList studentList, String input, UI ui) {
        super(studentList);
        this.input = input;
        this.ui = ui;
    }

    @Override
    public void run() {
        try {
            super.studentList.filterStudent(this.input);;
        } catch (SEPException e) {
            ui.printResponse(e.getMessage());
        }
    }
}
