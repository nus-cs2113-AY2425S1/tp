/**
 * The AllocateCommand class is responsible for performing the student allocation process.
 * This command utilizes the {@link Allocator} to assign students and updates the
 * {@link StudentList} with the allocation results. Additionally, it interacts with the
 * {@link UI} to display a message indicating that allocation is in progress.
 */
package command;

import allocator.Allocator;
import exceptions.SEPException;
import studentlist.StudentList;
import ui.UI;

/**
 * Represents a command to allocate students to resources or groups.
 * Initializes and uses an {@link Allocator} to manage the allocation process
 * for the given {@link StudentList}.
 */
public class AllocateCommand extends Command {
    private Allocator allocator;
    private UI ui;

    /**
     * Constructs an AllocateCommand with the specified student list and UI.
     * 
     * @param studentList The list of students to be allocated.
     * @param ui          The user interface used to display allocation messages.
     */
    public AllocateCommand(StudentList studentList, UI ui) {
        super(studentList);
        this.ui = ui;
        this.allocator = new Allocator(studentList);
    }

    /**
     * Executes the allocation process by setting the allocated student list in
     * {@link StudentList} and printing an allocation message in the UI.
     */
    @Override
    public void run() {
        try {
            this.studentList.setStudentList(allocator.allocate());
            this.studentList.setAllocationStatus(true);
            this.ui.printAllocatingMessage();
        } catch (SEPException e) {
            ui.printResponse(e.getMessage());
        }
    }
}
