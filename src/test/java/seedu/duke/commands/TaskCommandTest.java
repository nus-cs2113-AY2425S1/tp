package seedu.duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seedu.duke.data.task.TaskList;

public class TaskCommandTest {
    @Test
    void testAddTask() {
        AddTaskCommand addTaskCommand =  new AddTaskCommand("Read book");
        TaskList tasks = new TaskList();
        addTaskCommand.setData(tasks);
        CommandResult commandResult = addTaskCommand.execute();
        
        assertEquals("New task added: [ ] Read book", commandResult.getFeedbackToUser());
    }
    
    @Test
    void testDeleteTask_TaskNotExist(){
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(1);
        TaskList tasks = new TaskList();
        deleteTaskCommand.setData(tasks);
        CommandResult commandResult = deleteTaskCommand.execute();
        assertEquals(deleteTaskCommand.MESSAGE_TASK_NOT_FOUND, commandResult.getFeedbackToUser());
    }
}
