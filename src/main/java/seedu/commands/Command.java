package seedu.commands;

import seedu.duke.InternshipList;
import seedu.ui.UiCommand;

import java.util.ArrayList;
import java.util.logging.Logger;

public abstract class Command {
    protected static final int INDEX_FIELD = 0;
    protected static final int INDEX_DATA = 1;
    protected static final Logger LOGGER = Logger.getLogger("EasInternship");
    protected static UiCommand uiCommand = new UiCommand();
    protected InternshipList internships;

    public void setInternshipList(InternshipList internships) {
        this.internships = internships;
    }

    public abstract void execute(ArrayList<String> args);
    public abstract String getUsage();
}
