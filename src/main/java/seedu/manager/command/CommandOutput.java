package seedu.manager.command;

/**
 * Represents the output of the executed command
 */
public class CommandOutput {
    private final String message;
    private final boolean canExit;

    /**
     * Constructs a new CommandOutput with a given message and whether the program can be exited from
     *
     * @param message The given message
     * @param canExit Whether the program can be exited from
     */
    public CommandOutput(String message, boolean canExit) {
        this.message = message;
        this.canExit = canExit;
    }

    /**
     * Get the message attribute of CommandOutput
     *
     * @return the message of command output
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns true if the program can be exited from,
     * returns false otherwise.
     *
     * @return Whether the program can be exited from
     */
    public boolean getCanExit() {
        return canExit;
    }
}
