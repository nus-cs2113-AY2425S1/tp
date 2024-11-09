package seedu.duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seedu.duke.data.exception.MissingTaskArgument;
import seedu.duke.data.task.Deadline;
import seedu.duke.data.task.Repeat;
import seedu.duke.data.task.Task;
import seedu.duke.data.task.TaskList;
import seedu.duke.data.task.TaskList.DuplicateTaskException;
import seedu.duke.data.task.Todo;

public class TaskCommandTest {
    @Test
    void testAddTaskCommand_addTodo_successful() {
        AddTaskCommand addTaskCommand = new AddTaskCommand("todo", "Read book");
        TaskList tasks = new TaskList();
        addTaskCommand.setData(tasks);
        CommandResult commandResult = addTaskCommand.execute();
        
        assertEquals("New task added: [T][ ] Read book", commandResult.getFeedbackToUser());
    }

    @Test
    void testAddTaskCommand_addDeadline_successful() {
        AddTaskCommand addTaskCommand = new AddTaskCommand("deadline", "Read book", "23:00");
        TaskList tasks = new TaskList();
        addTaskCommand.setData(tasks);
        CommandResult commandResult = addTaskCommand.execute();
        assertEquals("New task added: [D][ ] Read book (by: 23:00)", commandResult.getFeedbackToUser());
    }

    @Test
    void testAddTaskCommand_addRepeat_successful(){
        AddTaskCommand addTaskCommand = new AddTaskCommand("repeat", "Read book", "2 days");
        TaskList tasks = new TaskList();
        addTaskCommand.setData(tasks);
        CommandResult commandResult = addTaskCommand.execute();
        assertEquals("New task added: [R][ ] Read book (repeat: every 2 days)", commandResult.getFeedbackToUser());
    }

    @Test
    void testAddTaskCommand_noDescription(){
        AddTaskCommand addTaskCommand = new AddTaskCommand("deadline", "");
        TaskList tasks = new TaskList();
        addTaskCommand.setData(tasks);
        CommandResult commandResult = addTaskCommand.execute();
        assertEquals("Task description cannot be empty", commandResult.getFeedbackToUser());
    }

    @Test
    void testAddTaskCommand_missingArguments(){
        AddTaskCommand addTaskCommand = new AddTaskCommand("deadline");
        TaskList tasks = new TaskList();
        addTaskCommand.setData(tasks);
        CommandResult commandResult = addTaskCommand.execute();
        assertEquals("Missing arguments for task type: deadline\n" + 
            "The required arguments for task type: deadline are: description, due date", 
            commandResult.getFeedbackToUser());
    }

    @Test
    void testAddTaskCommand_unknownTaskType(){
        AddTaskCommand addTaskCommand = new AddTaskCommand("random", "Read book");
        TaskList tasks = new TaskList();
        addTaskCommand.setData(tasks);
        CommandResult commandResult = addTaskCommand.execute();
        assertEquals("Unknown task type: random", commandResult.getFeedbackToUser());
    }

    @Test
    void testDeleteTaskCommand_successful() throws DuplicateTaskException{
        Task task = new Todo("Read book");
        TaskList tasks = new TaskList();
        tasks.addTask(task);

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(1);
        deleteTaskCommand.setData(tasks);
        CommandResult commandResult = deleteTaskCommand.execute();
        assertEquals(DeleteTaskCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    void testDeleteTaskCommand_taskNotExist(){
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(1);
        TaskList tasks = new TaskList();
        deleteTaskCommand.setData(tasks);
        CommandResult commandResult = deleteTaskCommand.execute();
        assertEquals(DeleteTaskCommand.MESSAGE_TASK_NOT_FOUND, commandResult.getFeedbackToUser());
    }

    @Test
    void testMarkTaskCommand_successful() throws TaskList.DuplicateTaskException, TaskList.TaskNotFoundException{
        TaskList tasks = new TaskList();
        Task task = new Todo("Read book");
        tasks.addTask(task);
        
        MarkTaskCommand command = new MarkTaskCommand(1);
        command.setData(tasks);
        CommandResult commandResult = command.execute();
        assertEquals("Task marked successfully: [T][X] Read book", commandResult.getFeedbackToUser());
    }

    @Test
    void testUnmarkTaskCommand_successful() throws DuplicateTaskException{
        TaskList tasks = new TaskList();
        Task task = new Todo("Read book");
        task.markAsDone();
        tasks.addTask(task);
        
        UnmarkTaskCommand command = new UnmarkTaskCommand(1);
        command.setData(tasks);
        CommandResult commandResult = command.execute();
        assertEquals("Task unmarked successfully: [T][ ] Read book", commandResult.getFeedbackToUser());
    }
    
    @Test 
    void testMarkTaskCommand_noTask(){
        MarkTaskCommand command = new MarkTaskCommand(1);
        TaskList tasks = new TaskList();
        command.setData(tasks);
        CommandResult commandResult = command.execute();
        assertEquals(MarkTaskCommand.MESSAGE_TASK_NOT_FOUND, commandResult.getFeedbackToUser());
    }

    @Test
    void testUnmarkTaskCommand_noTask(){
        UnmarkTaskCommand command = new UnmarkTaskCommand(1);
        TaskList tasks = new TaskList();
        command.setData(tasks);
        CommandResult commandResult = command.execute();
        assertEquals(UnmarkTaskCommand.MESSAGE_TASK_NOT_FOUND, commandResult.getFeedbackToUser());
    }

    @Test
    void testMarkTaskCommand_alreadyMarked() throws DuplicateTaskException{
        TaskList tasks = new TaskList();
        Task task = new Todo ("Read book");
        task.markAsDone();
        tasks.addTask(task);
        
        MarkTaskCommand command = new MarkTaskCommand(1);
        command.setData(tasks);
        CommandResult commandResult = command.execute();
        assertEquals(MarkTaskCommand.MESSAGE_TASK_ALREADY_MARKED, commandResult.getFeedbackToUser());
    }

    @Test
    void testUnmarkTaskCommand_alreadyUnmarked() throws DuplicateTaskException{
        TaskList tasks = new TaskList();
        Task task = new Todo ("Read book");
        tasks.addTask(task);
        
        UnmarkTaskCommand command = new UnmarkTaskCommand(1);
        command.setData(tasks);
        CommandResult commandResult = command.execute();
        assertEquals(UnmarkTaskCommand.MESSAGE_TASK_ALREADY_UNMARKED, commandResult.getFeedbackToUser());
    }
    @Test
    void testListTaskCommand_emptyList(){
        TaskList tasks = new TaskList();
        ListTaskCommand command = new ListTaskCommand();
        command.setData(tasks);
        CommandResult commandResult = command.execute();
        assertEquals(ListTaskCommand.MESSAGE_EMPTY_LIST, commandResult.getFeedbackToUser());
    }

    @Test
    void testAddTaskCommand_duplicateTask(){
        TaskList tasks = new TaskList();
        AddTaskCommand command = new AddTaskCommand("deadline", "Read book", "2pm");
        command.setData(tasks);
        command.execute();
        AddTaskCommand command2 = new AddTaskCommand("todo","Read book");
        command2.setData(tasks);
        CommandResult commandResult = command2.execute();
        assertEquals(AddTaskCommand.MESSAGE_DUPLICATE_TASK, commandResult.getFeedbackToUser());
    }

    @Test 
    void testAddTaskCommand_duplicateTask_caseInsensitive(){
        TaskList tasks = new TaskList();
        AddTaskCommand command = new AddTaskCommand("deadline", "Read book", "2pm");
        command.setData(tasks);
        command.execute();
        AddTaskCommand command2 = new AddTaskCommand("todo","read book");
        command2.setData(tasks);
        CommandResult commandResult = command2.execute();
        assertEquals(AddTaskCommand.MESSAGE_DUPLICATE_TASK, commandResult.getFeedbackToUser());
    }

    @Test
    void testFindTaskCommand_foundMatches() throws DuplicateTaskException, MissingTaskArgument{
        TaskList tasks = new TaskList();
        Task task1 = new Deadline("Read book", "2pm");
        Task task2 = new Todo ("Meomeo");
        Task task3 = new Repeat("Meomeomeomeo", "2 days");

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        
        FindTaskCommand findTaskCommand = new FindTaskCommand("meo");
        findTaskCommand.setData(tasks);
        CommandResult commandResult = findTaskCommand.execute();
        assertEquals("Here are the matching tasks in your list: " + 
            "\n1. [T][ ] Meomeo\n2. [R][ ] Meomeomeomeo (repeat: every 2 days)\n", 
            commandResult.getFeedbackToUser());
    }
    
    @Test
    void testFindTaskCommand_caseInsensitive() throws DuplicateTaskException, MissingTaskArgument{
        TaskList tasks = new TaskList();
        Task task1 = new Deadline("Read book", "2pm");
        Task task2 = new Todo ("Meomeo");
        Task task3 = new Repeat("Meomeomeomeo", "2 days");

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        
        FindTaskCommand findTaskCommand = new FindTaskCommand("MeO");
        findTaskCommand.setData(tasks);
        CommandResult commandResult = findTaskCommand.execute();
        assertEquals("Here are the matching tasks in your list: " + 
            "\n1. [T][ ] Meomeo\n2. [R][ ] Meomeomeomeo (repeat: every 2 days)\n", 
            commandResult.getFeedbackToUser());
    }

    @Test
    void testFindTaskCommand_noMatch() throws DuplicateTaskException, MissingTaskArgument{
        TaskList tasks = new TaskList();
        Task task1 = new Deadline("Read book", "2pm");
        Task task2 = new Todo ("Meomeo");
        Task task3 = new Repeat("Meomeomeomeo", "2 days");

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        
        FindTaskCommand findTaskCommand = new FindTaskCommand("ais");
        findTaskCommand.setData(tasks);
        CommandResult commandResult = findTaskCommand.execute();
        assertEquals(FindTaskCommand.MESSAGE_NO_MATCH, commandResult.getFeedbackToUser());
    }
}
