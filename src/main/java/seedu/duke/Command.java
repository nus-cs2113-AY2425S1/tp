package seedu.duke;

public interface Command {
    void execute(String[] args);
    String getUsage();
}
