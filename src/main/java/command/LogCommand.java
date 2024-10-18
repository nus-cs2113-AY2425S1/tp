package command;
import core.Ui;
import programme.ProgrammeList;
import programme.Day;
import core.History;

import java.time.LocalDate;


public class LogCommand extends Command {
    public static final String COMMAND_WORD = "log";

    private final int progIndex;
    private final int dayIndex;
    private final LocalDate date;

    public LogCommand(int progIndex, int dayIndex, LocalDate date){
        this.progIndex = progIndex;
        this.dayIndex = dayIndex;
        this.date = date;
    }

    @Override
    public void execute(Ui ui,ProgrammeList pList, History history){
        Day completed = pList.getDay(progIndex, dayIndex);
        history.logDay(completed, date);
        String result =  String.format("Congrats! You've successfully completed:%s%n",completed);
        ui.showMsg(result);
    }
}
