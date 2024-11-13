package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import seedu.duke.data.exception.MissingTaskArgument;
import seedu.duke.data.task.Task;
import seedu.duke.data.task.Task.EmptyTaskDescription;
import seedu.duke.data.task.Task.UnknownTaskType;

public class TaskTest {
    @Test
    public void createRepeatTest() throws MissingTaskArgument, EmptyTaskDescription, UnknownTaskType {
        Task task = Task.createTask("repeat", "Read book", "2 days");
        assertEquals("[R][ ] Read book (repeat: every 2 days)", task.toString());
    }

    @Test
    public void createRepeatTest_noRepeat() {
        assertThrows(MissingTaskArgument.class, () -> Task.createTask("repeat", "Read book"));
    }

    @Test
    public void createRepeatTest_noDescription() {
        assertThrows(Task.EmptyTaskDescription.class, () -> Task.createTask("repeat", "", "2 days"));
    }

    @Test 
    public void createRepeatTest_unknownTaskType() {
        assertThrows(Task.UnknownTaskType.class, () -> Task.createTask("unknown", "Read book", "2 days"));
    }

    @Test
    public void createDeadlineTest() throws MissingTaskArgument, EmptyTaskDescription, UnknownTaskType {
        Task task = Task.createTask("deadline", "Read book", "2021-12-12");
        assertEquals("[D][ ] Read book (by: 2021-12-12)", task.toString());
    }

    @Test
    public void createDeadlineTest_noBy() {
        assertThrows(MissingTaskArgument.class, () -> Task.createTask("deadline", "Read book"));
    }
}
