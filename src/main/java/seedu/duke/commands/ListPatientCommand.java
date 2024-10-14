package seedu.duke.commands;


public class ListPatientCommand extends HospitalCommand {
    public ListPatientCommand() {
    }

    @Override
    public CommandResult execute() {
        hospital.printList();
        return new CommandResult("Here are the patients in your list!");
    }
}
