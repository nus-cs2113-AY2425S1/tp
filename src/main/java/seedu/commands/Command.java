package seedu.commands;

public interface Command {
    void execute(String[] args);
    String getUsage();
}
