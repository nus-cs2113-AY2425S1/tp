//@@author Bev-low
package parser.command.factory;

import command.Command;
import command.InvalidCommand;
import command.water.AddWaterCommand;
import command.water.DeleteWaterCommand;
import command.water.ViewWaterCommand;
import exceptions.WaterException;
import parser.FlagParser;

import java.time.LocalDate;

import static parser.FlagDefinitions.VOLUME_FLAG;
import static parser.FlagDefinitions.WATER_INDEX;
import static parser.ParserUtils.parseDate;
import static parser.ParserUtils.splitArguments;

/**
 * A factory class to create and return specific water-related commands based on input arguments.
 */
public class WaterCommandFactory {
    public static final String COMMAND_WORD = "water";

    /**
     * Parses the argument string to determine which water-related command to create.
     * The first word of the argument string is used to identify the specific command (e.g., add, delete, view).
     *
     * @param argumentString the input arguments provided by the user
     * @return a {@code Command} object representing the water command to be executed
     * @throws AssertionError if {@code argumentString} is null
     */
    public Command parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String[] inputArguments = splitArguments(argumentString);
        String subCommandString = inputArguments[0];
        String arguments = inputArguments[1];

        return switch (subCommandString) {
        case AddWaterCommand.COMMAND_WORD -> prepareAddCommand(arguments);
        case DeleteWaterCommand.COMMAND_WORD -> prepareDeleteCommand(arguments);
        case ViewWaterCommand.COMMAND_WORD -> prepareViewCommand(arguments);
        default -> new InvalidCommand();
        };
    }

    /**
     * Prepares the AddWaterCommand by parsing the volume of water to be added and date.
     *
     * @param argumentString the argument string containing the flags and values
     * @return an {@code AddWaterCommand} with the parsed water volume and date
     * @throws IllegalArgumentException if required flags are missing
     */
    public Command prepareAddCommand(String argumentString) {
        FlagParser flagParser = new FlagParser(argumentString);

        flagParser.validateRequiredFlags(VOLUME_FLAG);

        float water = flagParser.getFloatByFlag(VOLUME_FLAG);

        if (water < 0){
            throw WaterException.volumeOutOfBounds();
        }

        LocalDate date = flagParser.getDateByFlag("/t");

        return new AddWaterCommand(water, date);
    }

    /**
     * Prepares the DeleteWaterCommand by parsing the index of water to be deleted and the date.
     *
     * @param argumentString the argument string containing the flags and values
     * @return a {@code DeleteWaterCommand} with the parsed index and date
     * @throws IllegalArgumentException if required flags are missing
     */
    public Command prepareDeleteCommand(String argumentString) {
        FlagParser flagParser = new FlagParser(argumentString);

        flagParser.validateRequiredFlags(WATER_INDEX);

        int waterIndexToDelete = flagParser.getIndexByFlag(WATER_INDEX);
        LocalDate date = flagParser.getDateByFlag("/t");

        return new DeleteWaterCommand(waterIndexToDelete, date);
    }

    /**
     * Prepares the ViewWaterCommand to view water logs for a specific date.
     *
     * @param argumentString the argument string containing the date
     * @return a {@code ViewWaterCommand} with the parsed date
     */
    public Command prepareViewCommand(String argumentString) {
        LocalDate date = parseDate(argumentString);
        return new ViewWaterCommand(date);
    }
}
