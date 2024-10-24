package seedu.duke.commands;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.data.task.Task;
import seedu.duke.data.task.TaskList;


public class FindTaskCommand extends Command{
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Here are the matching tasks in your list: \n%1$s";
    public static final String MESSAGE_NO_MATCH = "There are no matching tasks in your list.";

    public static final Logger LOGGER = Logger.getLogger(FindTaskCommand.class.getName());
    
    private final String keyword;

    static {
        LOGGER.setLevel(Level.SEVERE); // Only show warnings and errors
    }
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        assert keyword != null : "Keyword should not be null";

        try{
            ArrayList<Task> foundTasks = tasks.findTasks(keyword);
            return new CommandResult(String.format(MESSAGE_SUCCESS, foundListToString(foundTasks)));
        } catch (TaskList.TaskNotFoundException e) {
            LOGGER.log(Level.SEVERE, "No matching tasks found: {0}", keyword);
            return new CommandResult(MESSAGE_NO_MATCH);
        } 
    }
    
    public String foundListToString(ArrayList<Task> foundTasks) {
        StringBuilder foundList = new StringBuilder();
        for (int i = 0; i < foundTasks.size(); i++) {
            foundList.append(i + 1).append(". ").append(foundTasks.get(i)).append("\n");
        }
        return foundList.toString();
    }
    
}
