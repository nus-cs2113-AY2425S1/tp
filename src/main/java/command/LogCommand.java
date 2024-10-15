package command;
import programme.ProgrammeList;
import core.Ui;
import core.History;
import programme.Day;

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
    public void execute(Ui ui,ProgrammeList pList, History history){
        Day completed = pList.getDay(progIndex, dayIndex);
        history.logDay(completed, date);
        String result =  String.format("Congrats! You've successfully completed:%s%n",completed);
        ui.showMsg(result);
    }
}
