package seedu.commands;

import seedu.easinternship.InternshipList;
import seedu.ui.UiCommand;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Represents an executable command
 */
public abstract class Command {
    protected final int INDEX_FIELD = 0;
    protected final int INDEX_DATA = 1;
    protected final Logger LOGGER = Logger.getLogger("EasInternship");
    protected UiCommand uiCommand = new UiCommand();
    protected InternshipList internshipsList;

    public void setInternshipsList(InternshipList internships) {
        this.internshipsList = internships;
    }

    /**
     * Executes the command whose implementation
     * is specified by the respective child classes
     */
    public abstract void execute(ArrayList<String> args);
    public abstract String getUsage();
}
