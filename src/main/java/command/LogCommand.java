package command;

public class LogCommand extends Command {
    public static final String COMMAND_WORD = "log";
    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        String completedProgramme = pList.logActiveProgramme();
        ui.showMsg("Congrats! You've successfully completed:\n" + completedProgramme);
    }
}
