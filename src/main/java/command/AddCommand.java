package command;

import exceptions.SEPException;

import student.Student;
import studentlist.StudentList;
import ui.UI;

public class AddCommand extends Command {
    private String input;
    private UI ui;

    public AddCommand(StudentList studentList, String input, UI ui) {
        super(studentList);
        this.input = input;
        this.ui = ui;
    }

    /**
     * Executes the AddCommand, which creates a new student based on the input data
     * and adds it to the student list. If successful, displays a confirmation message.
     * If an error occurs, an error message is displayed instead.
     */
    
    @Override
    public void run() {
        try {
            Student newStudent = super.studentList.makeStudent(this.input);
            super.studentList.addStudent(newStudent);
            ui.printResponse("Added " + newStudent.getId() + " successfully.\n" +
                    "There are " + studentList.getNumStudents() + " student(s) in the list.");
        } catch (SEPException e) {
            ui.printResponse(e.getMessage());
        }
    }
}
