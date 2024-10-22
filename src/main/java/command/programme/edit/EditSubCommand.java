package command.programme.edit;



import programme.ProgrammeList;

public abstract class EditSubCommand {
    protected final int progId;
    protected int dayId;

    public EditSubCommand(int progId, int dayId){
        this.progId = progId;
        this.dayId = dayId;
    }

    public EditSubCommand(int progId){
        this.progId = progId;
    }

    public int getDayId() {
        return dayId;
    }

    public abstract String execute(ProgrammeList pList);
}
