package command;

public class InvalidCommand extends Command {
    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        ui.showMsg("Invalid command.");
    }
}
