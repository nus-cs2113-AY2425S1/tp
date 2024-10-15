package command;
import core.Ui;
import programme.ProgrammeList;
import programme.Day;
import core.History;

import java.time.LocalDateTime;


public class LogCommand extends Command {
    public static final String COMMAND_WORD = "log";
  
    private final int progIndex;
    private final int dayIndex;
    private final LocalDateTime date;


    public LogCommand(int progIndex, int dayIndex, LocalDateTime date){
        this.progIndex = progIndex;
        this.dayIndex = dayIndex;
        this.date = date;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        Day completed = pList.getDay(progIndex, dayIndex);
        history.logDay(completed, date);
        ui.showMsg("Congrats! You've successfully completed:\n" + completed.toString());
    }
}
