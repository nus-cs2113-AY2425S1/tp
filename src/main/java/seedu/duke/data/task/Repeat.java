package seedu.duke.data.task;

import seedu.duke.data.exception.MissingTaskArgument;

public class Repeat extends Task{
    protected String repeat;

    // Jackson requires a default constructor
    public Repeat() {
        super();
    }

    public Repeat(String description, String repeat) throws MissingTaskArgument {
        super(description);
        if(repeat == null || repeat.isEmpty() || repeat.isBlank()) {
            throw new MissingTaskArgument("repeat");
        }
        this.repeat = repeat;
    }

    public Repeat(String description, String repeat, String tag) throws MissingTaskArgument {
        super(description, tag);
        if(repeat == null || repeat.isEmpty() || repeat.isBlank()) {
            throw new MissingTaskArgument("repeat");
        }
        this.repeat = repeat;
    }

    public Repeat(String description, String repeat, boolean isDone) throws MissingTaskArgument {
        super(description, isDone);
        if(repeat == null || repeat.isEmpty() || repeat.isBlank()) {
            throw new MissingTaskArgument("repeat");
        }
        this.repeat = repeat;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    @Override
    public String toString() {
        return "[R]" + super.toString() + " (repeat: every " + repeat + ")";
    }

}
