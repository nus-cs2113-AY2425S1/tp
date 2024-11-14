package tutorlink.command;

import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

import java.util.HashMap;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
    }

    @Override
    public CommandResult execute(AppState state, HashMap<String, String> arguments) throws TutorLinkException {
        return new CommandResult(Commons.EXIT);
    }

    @Override
    public String[] getArgumentPrefixes() {
        return null;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
