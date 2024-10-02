package command.programme;
import command.Command;

public class EditCommand extends Command {
    private int dayIndex;
    private String editType; // create, update, or remove
    private int exerciseIndex;
    private String[] args; // Arguments like weight, reps, sets

    public EditCommand(int progIndex, int dayIndex, String editType, int exerciseIndex, String[] args) {
        super(progIndex);
        this.dayIndex = dayIndex;
        this.editType = editType;
        this.exerciseIndex = exerciseIndex;
        this.args = args;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history) {
        Programme programme = pList.getProgramme(progIndex);
        Day day = programme.getDay(dayIndex);

    }
}
