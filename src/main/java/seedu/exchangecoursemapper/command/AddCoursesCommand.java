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

        //throws an error when there is no user input after the add command.
        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide the nus course code, " +
                    "name of partner university (PU) and the PU course code.");
        }

        //returns user inputs without the "add" command
        return outputSubstrings[1];
    }

    public String[] parseAddCommand(String input) {
        input = input.replaceAll("(?i)/pu", "/pu")
                .replaceAll("(?i)/coursepu", "/coursepu")
                .trim().replaceAll(" +", " ");

        //throws an error when only one keyword is present
        if ((!input.contains("/pu") || !input.contains("/coursepu"))) {
            throw new IllegalArgumentException("Please provide all of the valid commands: /pu, /coursepu!");
        }

        if (input.contains("/pu/coursepu") || input.contains("/coursepu/pu")){
            throw new IllegalArgumentException("Commands shouldn't be adjacent to one another!");
        }

        String[] inputSubstrings = input.split(" /coursepu | /pu ");

        if (inputSubstrings.length < 3) {
            throw new IllegalArgumentException("Please provide a valid NUS course code or PU or PU's course code!");
        }

        return inputSubstrings;
    }

    public void printAddMessage(String addCourse) {
        System.out.println("You have successfully added the course: " + addCourse);
    }
}
