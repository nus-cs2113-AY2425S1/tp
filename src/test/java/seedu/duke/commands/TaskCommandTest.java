package seedu.duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seedu.duke.data.task.TaskList;

public class TaskCommandTest {
    @Test
    void testAddTask() {
        AddTaskCommand addTaskCommand = new AddTaskCommand("Read book");
        TaskList tasks = new TaskList();
        addTaskCommand.setData(tasks);
        CommandResult commandResult = addTaskCommand.execute();
        
        assertEquals("New task added: [ ] Read book", commandResult.getFeedbackToUser());
    }

    @Test
    void testDeleteTask_taskNotExist(){
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(1);
        TaskList tasks = new TaskList();
        deleteTaskCommand.setData(tasks);
        CommandResult commandResult = deleteTaskCommand.execute();
        assertEquals(DeleteTaskCommand.MESSAGE_TASK_NOT_FOUND, commandResult.getFeedbackToUser());
    }

    @Test
    void testMarkTask() {
        TaskList tasks = new TaskList();
        AddTaskCommand command = new AddTaskCommand("Read book");

        command.setData(tasks);
        command.execute();

        MarkTaskCommand command2 = new MarkTaskCommand(1);
        command2.setData(tasks);
        CommandResult commandResult = command2.execute();
        assertEquals("Task marked successfully: [X] Read book", commandResult.getFeedbackToUser());
    }

    @Test
    void testPrintTask(){
        TaskList tasks = new TaskList();
        AddTaskCommand command = new AddTaskCommand("Read book");
        command.setData(tasks);
        command.execute();

        ListTaskCommand listTaskCommand = new ListTaskCommand();
        listTaskCommand.setData(tasks);
        CommandResult commandResult = listTaskCommand.execute();
        assertEquals("Here are the tasks in your list!\n1. [ ] Read book\n", commandResult.getFeedbackToUser());
    }

    @Test
    void testDuplicateTask(){
        TaskList tasks = new TaskList();
        AddTaskCommand command = new AddTaskCommand("Read book");
        command.setData(tasks);
        command.execute();
        AddTaskCommand command2 = new AddTaskCommand("Read book");
        command2.setData(tasks);
        CommandResult commandResult = command2.execute();
        assertEquals(AddTaskCommand.MESSAGE_DUPLICATE_TASK, commandResult.getFeedbackToUser());
    }
    
    @Test
    void testFindTask(){
        TaskList tasks = new TaskList();
        AddTaskCommand command = new AddTaskCommand("Read book");
        command.setData(tasks);
        command.execute();
        
        AddTaskCommand command2 = new AddTaskCommand("Meomeo");
        command2.setData(tasks);
        command2.execute();

        FindTaskCommand findTaskCommand = new FindTaskCommand("meo");
        findTaskCommand.setData(tasks);
        CommandResult commandResult = findTaskCommand.execute();
        assertEquals("Here are the matching tasks in your list: \n1. [ ] Meomeo\n", commandResult.getFeedbackToUser());
    }
}
