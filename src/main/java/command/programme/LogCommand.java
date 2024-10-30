package command.programme;
import command.CommandResult;
import history.DailyRecord;
import programme.ProgrammeList;
import programme.Day;
import history.History;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;

public class LogCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "log";

    private final LocalDate date;


    public LogCommand(int programmeIndex, int dayIndex, LocalDate date){
        super(programmeIndex, dayIndex);

        assert dayIndex >= 0 : "Day index must be non-negative";
        assert date != null : "Date must not be null";

        this.date = date;
      
        logger.log(
                Level.INFO,
                "LogCommand initialized with progIndex: {0}, dayIndex: {1}, date: {2}",
                new Object[]{programmeIndex, dayIndex, date}
        );
    }

    @Override
    public CommandResult execute(ProgrammeList programmes, History history){
        logger.log(
                Level.INFO,
                "Executing LogCommand with progIndex: {0}, dayIndex: {1}, date: {2}",
                new Object[]{programmeIndex, dayIndex, date}
        );

        assert programmes != null : "ProgrammeList must not be null";
        assert history != null : "History must not be null";

        Day completed = programmes.getDay(programmeIndex, dayIndex);

        assert completed != null : "Completed Day must not be null";

        DailyRecord dailyRecord = history.getRecordByDate(date);
        dailyRecord.logDay(completed);
        history.logRecord(date, dailyRecord);

        logger.log(Level.INFO, "LogCommand executed successfully for day: {0}", completed);

        String result =  String.format("Congrats! You've successfully completed:%n%s",completed);
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

        logger.log(
                Level.INFO,"Comparing LogCommand with this: {0}, that: {1}",
                new Object[]{this.programmeIndex, that.programmeIndex}
        );

        boolean isProgrammeIndexEqual =  (programmeIndex == that.programmeIndex);
        boolean isDayIndexEqual = (dayIndex == that.dayIndex);
        boolean isDateEqual = Objects.equals(date, that.date);

        return (isProgrammeIndexEqual && isDayIndexEqual && isDateEqual);
    }
}
