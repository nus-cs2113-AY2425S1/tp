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
     * Prints the message of the command output.
     */
    public void printMessage() {
        System.out.println(this.message);
    }

    /**
     * Returns true if the program can be exited from,
     * returns false otherwise.
     *
     * @return Whether the program can be exited from
     */
    public boolean ifCanExit() {
        return canExit;
    }
}
