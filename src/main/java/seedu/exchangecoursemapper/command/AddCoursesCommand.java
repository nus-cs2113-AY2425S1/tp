package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.storage.Storage;

public class AddCoursesCommand extends Command {

    Storage storage = new Storage();

    @Override
    public void execute(String userInput) {
        try {
            String description = trimString(userInput);
            String[] descriptionSubstrings = parseAddCommand(description);

            String nusCourse = descriptionSubstrings[0].trim();
            String pu = descriptionSubstrings[1].trim();
            String puCourse = descriptionSubstrings[2].trim();

            String courseToStore = nusCourse + " | " + pu + " | " + puCourse;
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
            throw new IllegalArgumentException("Please provide a valid course name!");
        }

        return outputSubstrings[1];
    }

    public String[] parseAddCommand(String input) {
        String lowerCaseInput = input.toLowerCase();

        if ((!lowerCaseInput.contains("/pu") || !lowerCaseInput.contains("/coursepu"))) {
            throw new IllegalArgumentException("Please provide all of the valid commands: /pu, /coursepu!");
        }

        String[] inputSubstrings = lowerCaseInput.split("/coursepu|/pu");

        if (inputSubstrings.length < 3) {
            throw new IllegalArgumentException("Please provide a valid PU or PU's course code!");
        }

        if (inputSubstrings[1].trim().isEmpty() || inputSubstrings[2].trim().isEmpty()
                || inputSubstrings[0].trim().isEmpty()) {
            throw new IllegalArgumentException("Do not leave the description empty!");
        }

        return inputSubstrings;
    }

    public void printAddMessage(String addCourse) {
        System.out.println("You have successfully added the course: " + addCourse);
    }
}
