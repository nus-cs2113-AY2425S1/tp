package command;

public class LogCommand extends Command {
    public static final String COMMAND_WORD = "log";
    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        Programme completed = pList.logActiveProgramme();
        history.log(completed);
        ui.showMsg("Congrats! You've successfully completed:\n" + completed.toString());
    }
}
