package command.programme;
import command.Command;

public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        String allProgrammes = pList.toString();
        ui.showMsg("Listing programmes:\n" + allProgrammes);
    }
}
