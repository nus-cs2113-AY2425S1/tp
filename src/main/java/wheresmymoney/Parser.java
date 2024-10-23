package wheresmymoney;
import wheresmymoney.command.AddCommand;
import wheresmymoney.command.DeleteCommand;
import wheresmymoney.command.EditCommand;
import wheresmymoney.command.ListCommand;
import wheresmymoney.command.LoadCommand;
import wheresmymoney.command.SaveCommand;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    public static final String ARGUMENT_COMMAND = "command";
    public static final String ARGUMENT_MAIN = "main";
    public static final String ARGUMENT_CATEGORY = "category";
    public static final String ARGUMENT_PRICE = "price";
    public static final String ARGUMENT_DESCRIPTION = "description";

    private static Logger logger = Logger.getLogger("Foo");
    /**
     * Gets command from words.
     * @param words String list of arguments
     * @return command word
     */
    private String getCommandFromWords(String[] words){
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
    private void packCommandToExistingArgumentsMap(HashMap<String, String> argumentsMap, String[] words) {
        argumentsMap.put(Parser.ARGUMENT_COMMAND,getCommandFromWords(words));
    }

    /**
     * Packs following arguments from words into an existing argument map.
     * @param argumentsMap Arguments Mapping
     * @param words String list of arguments
     */
    private void packFollowingArgumentsToExistingArgumentsMap(HashMap<String, String> argumentsMap, String[] words) {
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
                currArgumentName = words[i];
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
    private HashMap<String, String> packWordsToArgumentsMap(String[] words) {
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
    public HashMap<String, String> parseCommandToArgumentsMap(String line) {
        logger.log(Level.INFO, "Parsing command: " + line);
        String[] words = line.split(" ");
        return packWordsToArgumentsMap(words);
    }

    /**
     * Matches the argument list to a related command and runs said command
     *
     * @param argumentsMap List of arguments
     * @param expenseList List of expenses
     * @return Whether to continue running the program
     * @throws WheresMyMoneyException If command fails to run
     */
    public boolean commandMatching(HashMap<String, String> argumentsMap, ExpenseList expenseList)
            throws WheresMyMoneyException {
        switch(argumentsMap.get(Parser.ARGUMENT_COMMAND)) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        case "add":
            new AddCommand(argumentsMap).execute(expenseList);
            break;
        case "edit":
            new EditCommand(argumentsMap).execute(expenseList);
            break;
        case "delete":
            new DeleteCommand(argumentsMap).execute(expenseList);
            break;
        case "list":
            new ListCommand(argumentsMap).execute(expenseList);
            break;
        case "load":
            new LoadCommand(argumentsMap).execute(expenseList);
            break;
        case "save":
            new SaveCommand(argumentsMap).execute(expenseList);
            break;
        case "help":
            Ui.displayHelp();
            break;
        default:
            throw new WheresMyMoneyException("No valid command given!");
        }
        return true;
    }
}
