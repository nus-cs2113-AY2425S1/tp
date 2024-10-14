package seedu.duke.commands;

public class AddPatientCommand extends HospitalCommand {
    private String name;

    public AddPatientCommand(String name) {
        this.name = name;
    }

    @Override
    public CommandResult execute() {
        hospital.addPatient(name);
        String resultMessage = "added Patient: " + name;
        System.out.println(resultMessage);
        return new CommandResult(resultMessage);
    }
}
