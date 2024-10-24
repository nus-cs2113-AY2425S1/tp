package command.programme;
import command.CommandResult;
import dailyrecord.DailyRecord;
import programme.ProgrammeList;
import programme.Day;
import history.History;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static common.Utils.NULL_INTEGER;

public class LogCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "log";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final int progIndex;
    private final int dayIndex;
    private final LocalDate date;


    public LogCommand(int progIndex, int dayIndex, LocalDate date){
        assert progIndex == NULL_INTEGER || progIndex >= 0 : "Program index must be valid";
        assert dayIndex >= 0 : "Day index must be non-negative";
        assert date != null : "Date must not be null";

        this.progIndex = progIndex;
        this.dayIndex = dayIndex;
        this.date = date;
      
        logger.log(
                Level.INFO,
                "LogCommand initialized with progIndex: {0}, dayIndex: {1}, date: {2}",
                new Object[]{progIndex, dayIndex, date}
        );
    }

    @Override
    public CommandResult execute(ProgrammeList pList, History history){
        logger.log(
                Level.INFO,
                "Executing LogCommand with progIndex: {0}, dayIndex: {1}, date: {2}",
                new Object[]{progIndex, dayIndex, date}
        );

        assert pList != null : "ProgrammeList must not be null";
        assert history != null : "History must not be null";

        Day completed = pList.getDay(progIndex, dayIndex);

        assert completed != null : "Completed Day must not be null";

        DailyRecord dailyRecord = history.getRecordByDate(date);
        if(dailyRecord == null) {
            dailyRecord = new DailyRecord(completed);
        }

        assert dailyRecord != null : "DailyRecord must not be null";

        dailyRecord.logDay(completed);

        String result =  String.format("Congrats! You've successfully completed:%n%s",completed);

        logger.log(Level.INFO, "LogCommand executed successfully for day: {0}", completed);
        return new CommandResult(result);
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }

        if (!(o instanceof LogCommand that)){
            return false;
        }

        logger.log(Level.WARNING,"LogCommand this: {0}, that: {1}", new Object[]{this.progIndex, that.progIndex});
        boolean isProgIndexEqual =  (progIndex == that.progIndex);
        boolean isDayIndexEqual = (dayIndex == that.dayIndex);
        boolean isDateEqual = Objects.equals(date, that.date);

        return (isProgIndexEqual && isDayIndexEqual && isDateEqual);
    }

    public LocalDate getDate() {
        return date;
    }
}
