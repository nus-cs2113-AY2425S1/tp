package seedu.commands;

import seedu.easinternship.Internship;

@FunctionalInterface
public interface InternshipFieldGetter {
    String getField(Internship internship);
}
