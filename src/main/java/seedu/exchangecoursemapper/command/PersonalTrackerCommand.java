package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.storage.Storage;

public abstract class PersonalTrackerCommand extends Command {
    public abstract void execute(String userInput, Storage storage);
}
