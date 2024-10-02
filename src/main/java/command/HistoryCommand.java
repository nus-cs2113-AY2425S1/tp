package command;

public class HistoryCommand extends Command {
    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        ui.showMsg("Your workout history: \n" + history.toString());
    }
}
