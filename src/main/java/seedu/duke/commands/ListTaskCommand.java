package seedu.duke.commands;

public class ListTaskCommand extends TaskCommand {
    public static final String COMMAND_WORD = "list";
    
    @Override
    public CommandResult execute() {
        tasks.printList();
        return new CommandResult("Here are the tasks in your list!");
    }
}
