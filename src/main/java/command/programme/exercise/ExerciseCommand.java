package command.programme.exercise;


import programme.ProgrammeList;

public abstract class ExerciseCommand {
    protected int progId;
    protected int dayId;

    public ExerciseCommand(int progId, int dayId){
        this.progId = progId;
        this.dayId = dayId;
    }

    public abstract String execute(ProgrammeList pList);
}
