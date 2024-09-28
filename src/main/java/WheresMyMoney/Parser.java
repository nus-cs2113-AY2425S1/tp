package WheresMyMoney;
import java.util.HashMap;

public class Parser{
    public static final String ARGUMENT_COMMAND = "command";
    public static final String ARGUMENT_MAIN = "main";

    /**
     * Parses the given user input into command arguments
     * @param line Line that a user inputs
     * @return HashMap of Arguments, mapping the argument to its value given
     */
    public HashMap<String, String> parseCommandToArguments(String line) {
        HashMap<String, String> argumentsList = new HashMap<>();
        String[] lineArgs = line.split(" ");

        // Command
        if (lineArgs.length <= 0) {
            argumentsList.put(Parser.ARGUMENT_COMMAND,"");
            return argumentsList;
        }
        argumentsList.put(Parser.ARGUMENT_COMMAND,lineArgs[0]);

        // Arguments
        String currArgumentName = Parser.ARGUMENT_MAIN;
        StringBuilder currArgument = new StringBuilder();

        for (int i=1; i<lineArgs.length; i++) {
            if (lineArgs[i].isEmpty()) { // Should be redundant but just in case
                continue;
            }
            if (lineArgs[i].charAt(0) == '/') {
                // New argument
                if (!currArgument.toString().isEmpty()){
                    argumentsList.put(currArgumentName, currArgument.toString().strip());
                }
                currArgumentName = lineArgs[i];
                currArgument.setLength(0);
            } else {
                // Add on to existing argument
                currArgument.append(" ").append(lineArgs[i]);
            }
        }
        // Add last command
        if (!currArgument.toString().isEmpty()) {
            argumentsList.put(currArgumentName, currArgument.toString().strip());
        }
        return argumentsList;
    }

    /**
     * Matches the argument list to a related command and runs said command
     * @param argumentsList List of arguments
     * @return Whether to continue running the program
     * @throws Exception If command fails to run
     */
    public boolean commandMatching(HashMap<String, String> argumentsList) throws WheresMyMoneyException {
        switch(argumentsList.get(Parser.ARGUMENT_COMMAND)){
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        default:
            System.out.println("No valid command given!");
            break;
        }
        return true;
    }
}