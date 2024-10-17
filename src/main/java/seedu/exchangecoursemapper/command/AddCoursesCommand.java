package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.exception.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddCoursesCommand extends Command {

    Storage storage = new Storage();
    private static final Logger logger = Logger.getLogger(AddCoursesCommand.class.getName());

    @Override
    public void execute(String userInput) {
        try {
            logger.log(Level.INFO, "Trim spaces and remove 'add' command. ");
            String description = trimString(userInput);
            logger.log(Level.INFO, "Check user input and split it into substrings");
            String[] descriptionSubstrings = parseAddCommand(description);

            logger.log(Level.INFO, "Extract from NUS course code, PU and PU course code from array.");
            String nusCourse = descriptionSubstrings[0].trim();
            String pu = descriptionSubstrings[1].trim();
            String puCourse = descriptionSubstrings[2].trim();

            logger.log(Level.INFO,"Format output");
            String courseToStore = nusCourse + " | " + pu + " | " + puCourse;
            logger.log(Level.INFO, "Adding course to storage: " + courseToStore);
            storage.addCourse(courseToStore);

            printAddMessage(courseToStore);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public String trimString(String string) {
        String trimmedString = string.trim();
        String[] outputSubstrings = trimmedString.split(" ", 2);

        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            logger.log(Level.WARNING, "No input after add keyword.");
            throw new IllegalArgumentException(Exception.noInputAfterAdd());
        }

        logger.log(Level.INFO, "Return trimmed input without 'add' command");
        return outputSubstrings[1];
    }

    public String[] parseAddCommand(String input) {

        input = input.replaceAll("(?i)/pu", "/pu")
                .replaceAll("(?i)/coursepu", "/coursepu")
                .trim().replaceAll(" +", " ");

        if ((!input.contains("/pu") || !input.contains("/coursepu"))) {
            logger.log(Level.WARNING, "Missing /pu or /coursepu keyword.");
            throw new IllegalArgumentException(Exception.missingKeyword());
        }

        if (input.contains("/pu/coursepu") || input.contains("/coursepu/pu")){
            logger.log(Level.WARNING, "Concurrent keywords.");
            throw new IllegalArgumentException(Exception.adjacentInputError());
        }

        String[] inputSubstrings = input.split(" /coursepu | /pu ");

        if (inputSubstrings.length < 3) {
            logger.log(Level.WARNING, "Invalid course code or partner university.");
            throw new IllegalArgumentException(Exception.invalidCourseCodes());
        }

        return inputSubstrings;
    }

    public void printAddMessage(String addCourse) {
        System.out.println("You have successfully added the course: " + addCourse);
    }
}
