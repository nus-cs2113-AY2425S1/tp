package command;

public class ExitCommand extends Command {
    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){}
}
