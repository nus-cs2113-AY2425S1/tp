package seedu.duke.parser;
import java.util.HashMap;

/**
 * The InputParser class is responsible for parsing user input into commands and arguments.
 * It takes a raw input string and splits it into meaningful components such as the command and its arguments.
 */
public class InputParser {
    public static final String COMMAND = "command";
    public static final String ARGUMENT = "argument";

    /**
     * Parses the user's input into a command and associated arguments.
     * The first word in the input is considered the command, and the rest is treated as arguments.
     * Arguments that start with "/" are treated as named arguments.
     *
     * @param input The raw input string from the user.
     * @return A HashMap containing the parsed command and its arguments.
     */
    public static HashMap<String, String> parseCommands(String input) {
        HashMap<String, String> commandArguments = new HashMap<>();
        String[] splitInput = input.split(" ");

        // check if input is empty
        if (splitInput.length == 0) {
            commandArguments.put(InputParser.COMMAND, "");
            return commandArguments;
        }

        // set first element as command
        commandArguments.put(InputParser.COMMAND, splitInput[0]);

        String argumentDescription = InputParser.ARGUMENT;
        StringBuilder argument = new StringBuilder();

        // parse remaining input
        for (int i = 1; i < splitInput.length; i++) {
            String arg = splitInput[i];

            if (arg.startsWith("/")) {
                if (!argumentDescription.isEmpty()) {
                    commandArguments.put(argumentDescription, argument.toString().strip());
                }

                argumentDescription = arg;
                argument.setLength(0);
            } else {
                argument.append(" ").append(arg);
            }
        }

        // add last argument
        if (!argument.isEmpty()) {
            commandArguments.put(argumentDescription, argument.toString().strip());
        }

        return commandArguments;
    }
}