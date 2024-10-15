package command;
import core.Ui;
import programme.ProgrammeList;
import programme.Day;
import core.History;

public class LogCommand extends Command {
    public static final String COMMAND_WORD = "log";
    private String date;
    private int progIndex;
    private int dayIndex;

    public LogCommand(int progIndex, int dayIndex, String date){
        this.progIndex = progIndex;
        this.dayIndex = dayIndex;
        this.date = date;
    }

    @Override
    public String execute(ProgrammeList pList, History history){
        Day completed = pList.getDay(progIndex, dayIndex);
        history.logDay(completed, date);
        String result =  String.format("Congrats! You've successfully completed:%s%n",completed);
        return result;
    }
}
