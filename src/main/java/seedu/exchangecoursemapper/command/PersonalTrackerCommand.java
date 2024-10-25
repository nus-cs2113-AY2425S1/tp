package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.storage.Storage;

public abstract class PersonalTrackerCommand {
    public abstract void execute(String userInput, Storage storage);
}
