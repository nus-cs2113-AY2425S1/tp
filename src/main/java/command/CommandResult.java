package command;

public class CommandResult {
    private final String message;

    public CommandResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        CommandResult that = (CommandResult) other;
        return message.equals(that.message);
    }
}
