package command.programme;
import command.Command;

public class StartCommand extends Command {

    public StartCommand(int startIndex){
        super(startIndex);
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        int startIndex = getTarget();
        String startedProgramme = startProgramme(startIndex);
        ui.showMsg("Ok! Started Programme:\n" + startedProgramme);
    }
}
