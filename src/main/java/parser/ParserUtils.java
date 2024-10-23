package parser;

import programme.Day;
import programme.Exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParserUtils {
    private static final Logger logger = Logger.getLogger(ParserUtils.class.getName());

    public static int parseIndex(String indexString, String errorMessage) {
        assert indexString != null : "Input indexString must not be null";
        assert errorMessage != null : "Input errorMessage must not be null";

        if (indexString.isEmpty()){
            throw new IllegalArgumentException(errorMessage + "Value was not provided.");
        }

        try {
            int index = Integer.parseInt(indexString.trim()) - 1;
            if (index < 0) {
                throw new IllegalArgumentException(errorMessage + "It must be a positive number.");
            }
            logger.log(Level.INFO, "Successfully parsed index: {0}", index);
            return index;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage + "Please provide a valid number.");
        }
    }

    public static LocalDate parseDate(String dateString) {
        assert dateString != null && !dateString.trim().isEmpty() : "Date string must not be null or empty";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: dd-MM-yyyy. " +
                    "Error: " + e.getParsedString(), e);
        }
    }

    public static Day parseDay(String dayString) {
        assert dayString != null : "Day string must not be null";

        String[] dayParts  = dayString.split("/e");
        String dayName = dayParts[0].trim();
        if (dayName.isEmpty()) {
            throw new IllegalArgumentException("Day name cannot be empty. Please enter a valid day name.");
        }

        Day day = new Day(dayName);

        for (int j = 1; j < dayParts.length; j++) {
            String exerciseString = dayParts[j].trim();
            Exercise exercise = parseExercise(exerciseString);
            day.insertExercise(exercise);
        }

        logger.log(Level.INFO, "Parsed day successfully: {0}", dayName);
        return day;
    }

    public static Exercise parseExercise(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        FlagParser flagParser = new FlagParser(argumentString);
        String[] requiredFlags = {"/n", "/s", "/r", "/w"};
        flagParser.validateRequiredFlags(flagParser, requiredFlags);

        String name = flagParser.getFlagValue("/n");
        int sets = parseIndex(flagParser.getFlagValue("/s"), "Invalid sets value. ");
        int reps = parseIndex(flagParser.getFlagValue("/r"), "Invalid reps value. ");
        int weight = parseIndex(flagParser.getFlagValue("/s"), "Invalid weight value. ");

        logger.log(Level.INFO, "Parsed exercise successfully with name: {0}, set: {1}, rep: {2}" +
                " weight: {3}", new Object[]{name, sets, reps, weight});

        return new Exercise(sets, reps, weight, name);
    }
}
