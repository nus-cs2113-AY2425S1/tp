package command.programme;

import command.Command;

public abstract class ProgrammeCommand extends Command {
    protected int progId;
    protected int dayId;

    public ProgrammeCommand(int progId, int dayId) {
        this.progId = progId;
        this.dayId = dayId;
        assert progId >= 0 : "progId must not be negative";
        assert dayId >= 0 : "dayId must not be negative";
    }

    public ProgrammeCommand(int progId) {
        this.progId = progId;
        assert progId >= 0 : "progId must not be negative";
    }

    public ProgrammeCommand(){}

    public int getProgId() {
        return progId;
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }

        if (!(other instanceof ProgrammeCommand that)){
            return false;
        }

        boolean isProgIndexEqual = this.progId == that.progId;
        boolean isDayIndexEqual = this.dayId == that.dayId;
        return isProgIndexEqual && isDayIndexEqual;
    }
}
