package seedu.exchangecoursemapper.command;

public abstract class Command {
    public static final String FILE_PATH = "./data/database.json";
    public abstract void execute(String userInput);
    //Use this abstract class for other future commands
}
