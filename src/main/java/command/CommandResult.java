package command;

public record CommandResult(String message) {

    @Override
    public String message() {
        return String.format(message);
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
