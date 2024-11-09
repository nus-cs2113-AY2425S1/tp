package seedu.duke.data.task;

import seedu.duke.data.exception.MissingTaskArgument;

public class Deadline extends Task{
    protected String by;

    // Jackson requires a default constructor
    public Deadline() {
        super();
    }

    public Deadline(String description, String by) throws MissingTaskArgument {
        super(description);
        if(by == null || by.isEmpty() || by.isBlank()) {
            throw new MissingTaskArgument("deadline");
        }
        this.by = by;
    }

    public Deadline(String description, String by, String tag) throws MissingTaskArgument {
        super(description, tag);
        if(by == null || by.isEmpty() || by.isBlank()) {
            throw new MissingTaskArgument("deadline");
        }
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) throws MissingTaskArgument {
        super(description, isDone);
        if(by == null || by.isEmpty() || by.isBlank()) {
            throw new MissingTaskArgument("deadline");
        }
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
