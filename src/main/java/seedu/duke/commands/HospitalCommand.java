package seedu.duke.commands;

import seedu.duke.data.hospital.Hospital;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class HospitalCommand extends Command {
    protected static Hospital hospital;

    private static final Logger logger = Logger.getLogger(HospitalCommand.class.getName());

    static {
        logger.setLevel(Level.SEVERE); // Only show warnings and errors
    }

    public static void setHospital(Hospital hospital) {
        assert hospital != null : "Hospital instance should not be null";
        HospitalCommand.hospital = hospital;
        logger.log(Level.INFO, "Hospital instance has been set.");
    }

    @Override
    public abstract CommandResult execute();
}
