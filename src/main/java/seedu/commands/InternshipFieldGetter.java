package seedu.commands;

import seedu.duke.Internship;

@FunctionalInterface
public interface InternshipFieldGetter {
    String getField(Internship internship);
}
