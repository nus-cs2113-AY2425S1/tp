package parser.command;

import command.Command;
import command.InvalidCommand;
import command.meals.AddMealCommand;
import command.meals.DeleteMealCommand;
import command.meals.ViewMealCommand;
import meal.Meal;
import parser.FlagParser;

import java.time.LocalDate;

import static parser.ParserUtils.splitArguments;

public class MealCommandFactory {
    public static final String COMMAND_WORD = "meal";

    public Command parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String[] inputArguments = splitArguments(argumentString);
        String subCommandString = inputArguments[0];
        String arguments = inputArguments[1];

        return switch (subCommandString) {
        case AddMealCommand.COMMAND_WORD -> prepareAddCommand(arguments);
        case DeleteMealCommand.COMMAND_WORD -> prepareDeleteCommand(arguments);
        case ViewMealCommand.COMMAND_WORD -> prepareViewCommand(arguments);
        default -> new InvalidCommand();
        };
    }

    public Command prepareAddCommand(String argumentString) {
        FlagParser flagParser = new FlagParser(argumentString);

        flagParser.validateRequiredFlags("/d");

        String mealName = flagParser.getStringByFlag("/n");
        int mealCalories = flagParser.getIntegerByFlag("/c");
        LocalDate date = flagParser.getDateByFlag("/t");

        Meal mealToAdd = new Meal(mealName, mealCalories);

        return new AddMealCommand(mealToAdd, date);
    }

    public Command prepareDeleteCommand(String argumentString) {
        FlagParser flagParser = new FlagParser(argumentString);

        flagParser.validateRequiredFlags("/d");

        int mealIndexToDelete = flagParser.getIndexByFlag("/m");
        LocalDate date = flagParser.getDateByFlag("/t");

        return new DeleteMealCommand(mealIndexToDelete, date);
    }

    public Command prepareViewCommand(String argumentString) {
        FlagParser flagParser = new FlagParser(argumentString);
        flagParser.validateRequiredFlags("/d");

        LocalDate date = flagParser.getDateByFlag("/t");

        return new ViewMealCommand(date);
    }
}
