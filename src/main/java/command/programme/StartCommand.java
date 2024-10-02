package command.programme;
import command.Command;

public class StartCommand extends Command {
    public static final String COMMAND_WORD = "start";

    public StartCommand(int startIndex){
        super(startIndex);
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        int startIndex = getTarget();
        Programme startedProgramme = startProgramme(startIndex);
        ui.showMsg("Ok! Started Programme:\n" + startedProgramme.toString());
    }
}
