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
     * Parses the given user input into command arguments
     *
     * @param line Line that a user inputs
     * @return HashMap of Arguments, mapping the argument to its value given
     */
    public HashMap<String, String> parseCommandToArguments(String line) {
        logger.log(Level.INFO, "Parsing command: " + line);
        HashMap<String, String> argumentsList = new HashMap<>();
        String[] lineArgs = line.split(" ");

        // Command
        if (lineArgs.length == 0) {
            argumentsList.put(Parser.ARGUMENT_COMMAND,"");
            return argumentsList;
        }
        assert lineArgs.length > 0;
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
                currArgumentName = lineArgs[i].replace("/", "");
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
        logger.log(Level.INFO, "Finish parsing command: " + line);
        return argumentsList;
    }

    /**
     * Matches the argument list to a related command and runs said command
     *
     * @param argumentsList List of arguments
     * @param expenseList List of expenses
     * @return Whether to continue running the program
     * @throws WheresMyMoneyException If command fails to run
     */
    public boolean commandMatching(HashMap<String, String> argumentsList, ExpenseList expenseList) 
            throws WheresMyMoneyException {
        switch(argumentsList.get(Parser.ARGUMENT_COMMAND)) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        case "add":
            new AddCommand(argumentsList).execute(expenseList);
            break;
        case "edit":
            new EditCommand(argumentsList).execute(expenseList);
            break;
        case "delete":
            new DeleteCommand(argumentsList).execute(expenseList);
            break;
        case "list":
            new ListCommand(argumentsList).execute(expenseList);
            break;
        case "load":
            new LoadCommand(argumentsList).execute(expenseList);
            break;
        case "save":
            new SaveCommand(argumentsList).execute(expenseList);
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
