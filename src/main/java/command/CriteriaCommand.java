package command;

import allocator.Allocator;
import exceptions.SEPException;
import studentlist.StudentList;
import ui.UI;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CriteriaCommand extends Command {
    private String input;
    private UI ui;
    private Allocator allocator;

    public CriteriaCommand (StudentList studentList,String input, UI ui) {
        super(studentList);
        this.input = input;
        this.ui = ui;
        this.allocator = new Allocator(studentList);
    }

    /**
     * Processes the user input to set a minimum GPA requirement.
     * This method takes the user-provided input and extracts the necessary part
     * to set the minimum GPA through the allocator's setMinimumGPA method.
     * If the GPA is valid, a confirmation message is printed; otherwise, an error
     * message is displayed.
     * 
     * @throws SEPException if there are issues with GPA validation.
     */
    @Override
    public void run() {
        try {
            allocator.setMinimumGPA(input);
            BigDecimal roundedGPA = BigDecimal.valueOf(allocator.getMinimumGPA()).setScale(2, RoundingMode.HALF_UP);
            ui.printResponse("Minimum requirement of GPA set to " + roundedGPA + " successfully.");
        } catch (SEPException e) {
            ui.printResponse(e.getMessage());
        }
    }

}
