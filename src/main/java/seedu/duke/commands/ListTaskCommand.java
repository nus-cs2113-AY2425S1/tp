package seedu.duke.commands;

public class ListTaskCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list! \n" + //
                "%1$s";
    public static final String MESSAGE_EMPTY_LIST = "There are no tasks in your list!";
    
    @Override
    public CommandResult execute() {
        if(tasks.getSize() == 0) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        } else{
            return new CommandResult(String.format(MESSAGE_SUCCESS, tasks));
        }
    }
}
