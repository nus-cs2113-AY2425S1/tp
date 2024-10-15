package seedu.commands;

import java.util.ArrayList;

//@@ Ridiculouswifi
public class HelpCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new AddCommand());
        commands.add(new DeleteCommand());
        commands.add(new ListCommand());
        commands.add(new FilterCommand());
        commands.add(new SortCommand());
        commands.add(new UpdateCommand());

        for (Command command : commands) {
            System.out.println(command.getUsage());
        }
    }

    @Override
    public String getUsage() {
        return "Usage: help";
    }
}
