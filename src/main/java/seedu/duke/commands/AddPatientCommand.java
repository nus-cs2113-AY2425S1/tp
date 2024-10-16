package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddPatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the hospital";

    private static final Logger logger = Logger.getLogger(AddPatientCommand.class.getName());

    private String name;

    static {
        logger.setLevel(Level.SEVERE); // Only show warnings and errors
    }

    public AddPatientCommand(String name) {
        this.name = name;
    }

    @Override
    public CommandResult execute() {
        assert name != null && !name.isEmpty() : "Patient name should not be null or empty";

        if (hospital.isDuplicatePatient(name)) {
            logger.log(Level.SEVERE, "Duplicate patient detected: {0}", name);
            System.out.println(MESSAGE_DUPLICATE_PATIENT);
            return new CommandResult(MESSAGE_DUPLICATE_PATIENT);
        }

        hospital.addPatient(name);
        String resultMessage = String.format(MESSAGE_SUCCESS, name);
        System.out.println(resultMessage);

        return new CommandResult(resultMessage);
    }
}
