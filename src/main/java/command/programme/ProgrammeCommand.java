// @@author TVageesan
package command.programme;
import command.Command;

import java.util.logging.Logger;

import static common.Utils.NULL_INTEGER;

public abstract class ProgrammeCommand extends Command {
    protected final Logger logger = Logger.getLogger(this.getClass().getName());
    protected int programmeIndex;
    protected int dayIndex;

    public ProgrammeCommand(int programmeIndex, int dayIndex) {
        this(programmeIndex);
        assert dayIndex >= 0 : "dayIndex must not be negative";
        this.dayIndex = dayIndex;
    }

    public ProgrammeCommand(int programmeIndex) {
        // We accept NULL_INTEGER as a valid programmeIndex as it is a optional argument
        assert programmeIndex == NULL_INTEGER || programmeIndex >= 0 : "Program index must be valid";
        this.programmeIndex = programmeIndex;
    }
    
    public ProgrammeCommand(){}

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }

        if (!(other instanceof ProgrammeCommand that)){
            return false;
        }

        boolean isProgIndexEqual = this.programmeIndex == that.programmeIndex;
        boolean isDayIndexEqual = this.dayIndex == that.dayIndex;
        return isProgIndexEqual && isDayIndexEqual;
    }
}
