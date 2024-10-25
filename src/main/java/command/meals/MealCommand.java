package command.meals;

import command.Command;
import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import meal.MealList;
import programme.ProgrammeList;

import java.time.LocalDate;

public abstract class MealCommand extends Command {

    protected LocalDate date;

    public MealCommand(LocalDate date) {
        this.date = date;
    }

    public MealList getMealList(History history) {
        DailyRecord record = history.getRecordByDate(date);
        if(record == null) {
            record = new DailyRecord(new MealList());
        }
        return record.getMealList();
    }

    public abstract CommandResult execute(History history);

    @Override
    public CommandResult execute (ProgrammeList pList, History history) {
        return execute(history);
    }
}
