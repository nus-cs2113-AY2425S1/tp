package seedu.commands;

import seedu.EasInternship.Internship;

@FunctionalInterface
public interface InternshipFieldGetter {
    String getField(Internship internship);
}
