package command;

public class LogCommand extends Command {

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        String completedProgramme = pList.logActiveProgramme();
        ui.showMsg("Congrats! You've successfully completed:\n" + completedProgramme);
    }
}
