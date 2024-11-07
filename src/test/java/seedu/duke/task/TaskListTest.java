package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.duke.data.task.Task;
import seedu.duke.data.task.TaskList;
import seedu.duke.data.task.TaskList.DuplicateTaskException;
import seedu.duke.data.task.TaskList.TaskNotFoundException;
import seedu.duke.data.task.Todo;

public class TaskListTest {

    @Test
    public void testAddTask_successful() throws DuplicateTaskException, TaskNotFoundException {
        TaskList tasks = new TaskList();
        Task task = new Todo("Read book");
        tasks.addTask(task);
        assertEquals(1, tasks.getSize());
        assertEquals("Read book", tasks.getTask(0).getDescription());
    }

    @Test
    public void testDeleteTask_index_successful() throws DuplicateTaskException, TaskNotFoundException {
        TaskList tasks = new TaskList();
        Task task = new Todo("Read book");
        tasks.addTask(task);
        tasks.deleteTask(0);
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void testDeleteTask_index_taskNotExist() {
        TaskList tasks = new TaskList();
        assertThrows(TaskNotFoundException.class, () -> tasks.deleteTask(1));
    }

    @Test 
    public void testDeleteTask_task_successful() throws DuplicateTaskException, TaskNotFoundException {
        TaskList tasks = new TaskList();
        Task task = new Todo("Read book");
        tasks.addTask(task);
        tasks.deleteTask(task);
        assertEquals(0, tasks.getSize());
    }

    @Test 
    public void testDeleteTask_task_taskNotExist() {
        TaskList tasks = new TaskList();
        Task task = new Todo("Read book");
        assertThrows(TaskNotFoundException.class, () -> tasks.deleteTask(task));
    }

    @Test
    public void testFindTask_foundMatches() throws DuplicateTaskException, TaskNotFoundException{
        TaskList tasks = new TaskList();
        Task task = new Todo("Read book");
        Task task2 = new Todo("Close book");
        tasks.addTask(task);
        tasks.addTask(task2);

        ArrayList<Task> foundMatches = tasks.findTasks("bOOk");
        assertEquals("Read book", foundMatches.get(0).getDescription());
        assertEquals("Close book", foundMatches.get(1).getDescription());
    }

    @Test
    public void testFindTask_taskNotFound(){
        TaskList tasks = new TaskList();
        assertThrows(TaskNotFoundException.class, () -> tasks.findTasks("book"));
    }

    @Test
    public void testMarkTask_successful() throws DuplicateTaskException, TaskNotFoundException {
        TaskList tasks = new TaskList();
        Task task = new Todo("Read book");
        tasks.addTask(task);
        tasks.markAsDone(0);
        assertEquals(true, tasks.getTask(0).getDone());
    }

    @Test
    public void testMarkTask_taskNotFound() {
        TaskList tasks = new TaskList();
        assertThrows(TaskNotFoundException.class, () -> tasks.markAsDone(0));
    }

    @Test
    public void testGetCompletionRate() throws DuplicateTaskException {
        TaskList tasks = new TaskList();
        Task task = new Todo("Read book");
        Task task2 = new Todo("Close book");
        task.markAsDone();
        tasks.addTask(task);
        tasks.addTask(task2);

        String expResult = "50.00%";
        String result = tasks.completionRatePercentageToString();

        assertEquals(expResult, result);
    }

    @Test
    public void testGetCompletionRate_emptyTaskList() {
        TaskList tasks = new TaskList();
        String expResult = "100.00%";
        String result = tasks.completionRatePercentageToString();
        assertEquals(expResult, result);
    }
}
