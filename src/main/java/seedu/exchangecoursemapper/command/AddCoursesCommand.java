package seedu.exchangecoursemapper.command;

public class AddCoursesCommand extends Command {

    @Override
    public void execute(String userInput) {
        String description = trimString(userInput);
        String[] descriptionSubstring = parseAddCommand(description);

        String nusCourse = descriptionSubstring[0];
        String pu = descriptionSubstring[1];
        String puCourse = descriptionSubstring[2];

        String courseToStore = nusCourse + " | " + pu + " | " + puCourse;
        items.add(courseToStore);
    }

    public String trimString(String string) {
        String trimmedString = string.trim();
        String[] outputSubstrings = trimmedString.split(" ",2);

        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            System.out.println("Please provide a valid course name!");
        }

        //return the trimmed description without the command
        return outputSubstrings[1];
    }

    public String[] parseAddCommand(String input) {
        String lowerCaseInput = input.toLowerCase();
        if (!lowerCaseInput.contains("/pu") || !lowerCaseInput.contains("/pu_course")) {
            System.out.println("Please provide all of the valid commands: /pu, /pu_course!");
            return null;
        }

        String[] inputSubstrings = lowerCaseInput.split("/pu|/pu_course");
        if (inputSubstrings.length < 3) {
            System.out.println("Please provide a valid PU or PU's course code!");
            return null;
        }

        return inputSubstrings;
    }
}
