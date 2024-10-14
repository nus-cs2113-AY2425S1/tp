package seedu.duke.commands;

import seedu.duke.data.hospital.Hospital;

public abstract class HospitalCommand extends Command {
    protected static Hospital hospital;

    public static void setHospital(Hospital hospital) {
        HospitalCommand.hospital = hospital;
    }

    @Override
    public abstract CommandResult execute();
}

