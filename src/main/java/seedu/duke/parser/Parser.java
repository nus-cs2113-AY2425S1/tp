package seedu.duke.parser;
import seedu.duke.commands.AddPatientCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeletePatientCommand;
import seedu.duke.commands.ListPatientCommand;
import seedu.duke.commands.ListTaskCommand;
import seedu.duke.commands.SelectPatientCommand;
import seedu.duke.commands.MarkTaskCommand;
import seedu.duke.commands.UnmarkTaskCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.commands.BackCommand;


import static java.lang.Integer.parseInt;

public class Parser {
    String line;
    State state;

    public Parser(String line, State state) {
        this.line = line;
        this.state = state;
    }
    public Command parseCommand() {
        if (line == null || line.isEmpty()){
            System.out.println("Command is empty");
            return null;
        }
        String[] parts = line.split(" ");

        switch (parts[0]) {
        case AddPatientCommand.COMMAND_WORD:
            try {
                return new AddPatientCommand(parts[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Non-Numerical Error");
            }
            break;

        case "list":
            if (state.getState() == StateType.MAIN_STATE) {
                return new ListPatientCommand();
            } else if (state.getState() == StateType.TASK_STATE) {
                return new ListTaskCommand();
            }
            break;

        case "delete":
            try{
                return new DeletePatientCommand(parseInt(parts[1]));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Non-Numerical Error");
            }
            break;

        case "select":
            try{
                return new SelectPatientCommand(parseInt(parts[1]), state);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Non-Numerical Error");
            }
            break;

        case "mark":
            try{
                return new MarkTaskCommand(parseInt(parts[1]));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Non-Numerical Error");
            }
            break;

        case "unmark":
            try{
                return new UnmarkTaskCommand(parseInt(parts[1]));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Non-Numerical Error");
            }
            break;

        case "back":
            return new BackCommand(state); // Pass the global State object created in main to backcommand

        case "exit":
            System.out.println("Exiting program...");
            System.exit(0);  // Terminates the program gracefully
            break;

        default:
            System.out.println("Unknown command");
        }
        return null;
    }
}
