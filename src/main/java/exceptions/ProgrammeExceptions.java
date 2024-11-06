package exceptions;

public class ProgrammeExceptions extends BuffBuddyExceptions {

    public ProgrammeExceptions(String message) {
        super(message);
    }

    public static ProgrammeExceptions programmeMissingName() {
        return new ProgrammeExceptions("Programme is missing a name!");
    }

    public static ProgrammeExceptions programmeEditMissingFlags() {
        return new ProgrammeExceptions("Programme edit command is missing required flags. " +
                "Please refer to the user guide for details.");
    }

    public static ProgrammeExceptions missingDayName() {
        return new ProgrammeExceptions("Missing Day Name, please provide one.");
    }

    public static ProgrammeExceptions doesNotExist(String name) {
        return new ProgrammeExceptions(name + " does not exist.");
    }
}
