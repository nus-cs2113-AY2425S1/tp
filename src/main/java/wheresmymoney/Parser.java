package wheresmymoney;

import wheresmymoney.command.AddCommand;
import wheresmymoney.command.ByeCommand;
import wheresmymoney.command.Command;
import wheresmymoney.command.DeleteCommand;
import wheresmymoney.command.EditCommand;
import wheresmymoney.command.HelpCommand;
import wheresmymoney.command.ListCommand;
import wheresmymoney.command.LoadCommand;
import wheresmymoney.command.SaveCommand;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;
import java.util.logging.Level;

public class Parser {
    public static final String ARGUMENT_COMMAND = "command";
    public static final String ARGUMENT_MAIN = "main";
    public static final String ARGUMENT_CATEGORY = "category";
    public static final String ARGUMENT_PRICE = "price";
    public static final String ARGUMENT_DESCRIPTION = "description";
    public static final String ARGUMENT_DATE_ADDED = "dateAdded";

    /**
     * Gets command from words.
     * @param words String list of arguments
     * @return command word
     */
    private static String getCommandFromWords(String[] words){
        // Command
        if (words.length == 0) {
            return "";
        }
        assert words.length > 0;
        return(words[0]);
    }

    /**
     * Packs command from words into an existing argument map.
     * @param argumentsMap Arguments Mapping
     * @param words String list of arguments
     */
    private static void packCommandToExistingArgumentsMap(HashMap<String, String> argumentsMap, String[] words) {
        argumentsMap.put(Parser.ARGUMENT_COMMAND,getCommandFromWords(words));
    }

    /**
     * Packs following arguments from words into an existing argument map.
     * @param argumentsMap Arguments Mapping
     * @param words String list of arguments
     */
    private static void packFollowingArgumentsToExistingArgumentsMap(HashMap<String, String> argumentsMap,
                                                                     String[] words) {
        // Arguments
        String currArgumentName = Parser.ARGUMENT_MAIN;
        StringBuilder currArgument = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            if (words[i].isEmpty()) { // Should be redundant but just in case
                continue;
            }
            if (words[i].charAt(0) == '/') {
                // New argument
                if (!currArgument.toString().isEmpty()){
                    argumentsMap.put(currArgumentName, currArgument.toString().strip());
                }
                currArgumentName = words[i].replace("/", "");
                currArgument.setLength(0);
            } else {
                // Add on to existing argument
                currArgument.append(" ").append(words[i]);
            }
        }

        // Add last command
        if (!currArgument.toString().isEmpty()) {
            argumentsMap.put(currArgumentName, currArgument.toString().strip());
        }
    }

    /**
     * Packs words into a new argument map.
     * @param words String list of arguments/words
     */
    private static HashMap<String, String> packWordsToArgumentsMap(String[] words) {
        HashMap<String, String> argumentsList = new HashMap<>();
        packCommandToExistingArgumentsMap(argumentsList, words);
        packFollowingArgumentsToExistingArgumentsMap(argumentsList, words);
        return argumentsList;
    }

    /**
     * Parses the given user input into command arguments.
     * @param line Line that a user inputs
     * @return HashMap of Arguments, mapping the argument to its value given
     */
    public static HashMap<String, String> parseLineToArgumentsMap(String line) {
        Logging.log(Level.INFO, "Parsing Line: " + line);
        String[] words = line.split(" ");
        return packWordsToArgumentsMap(words);
    }

    /**
     * Matches the argument list to a related command and runs said command
     *
     * @param argumentsMap List of arguments
     * @return Whether to continue running the program
     * @throws wheresmymoney.exception.WheresMyMoneyException If command fails to run
     */
    public static Command commandMatching(HashMap<String, String> argumentsMap)
            throws WheresMyMoneyException {
        switch(argumentsMap.get(Parser.ARGUMENT_COMMAND)) {
        case "bye":
            return new ByeCommand(argumentsMap);
        case "add":
            return new AddCommand(argumentsMap);
        case "edit":
            return new EditCommand(argumentsMap);
        case "delete":
            return new DeleteCommand(argumentsMap);
        case "list":
            return new ListCommand(argumentsMap);
        case "load":
            return new LoadCommand(argumentsMap);
        case "save":
            return new SaveCommand(argumentsMap);
        case "help":
            return new HelpCommand(argumentsMap);
        default:
            throw new InvalidInputException("No valid command given!");
        }
    }

    public static Command parseInputToCommand(String line) throws WheresMyMoneyException {
        return commandMatching(parseLineToArgumentsMap(line));
    }
}
