package seedu.duke.parser;
import seedu.duke.commands.AddPatientCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeletePatientCommand;
import seedu.duke.commands.ListPatientCommand;
import seedu.duke.commands.ListTaskCommand;
import seedu.duke.commands.SelectPatientCommand;
import seedu.duke.commands.MarkTaskCommand;
import seedu.duke.commands.UnmarkTaskCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Integer.parseInt;

public class Parser {
    private static final Logger logger = Logger.getLogger("Parser");
    String line;
    int state;

    public Parser(String line, int state) {
        this.line = line;
        this.state = state;
        logger.log(Level.INFO, "Starting Parser Class...");
        assert state == 0 || state == 1 : "state should be 0 or 1";
    }
    public Command parseCommand() {
        String[] parts = line.split(" ");

        switch (parts[0]) {
        case AddPatientCommand.COMMAND_WORD:
            try {
                return new AddPatientCommand(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
                logger.log(Level.WARNING, "Add Patient Command Error: Non-Numerical Error");
            }
            break;

        case "list":
            if (state == 0) {
                return new ListPatientCommand();
            } else if (state == 1) {
                return new ListTaskCommand();
            }
            break;

        case "delete":
            try{
                return new DeletePatientCommand(parseInt(parts[1]));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out-of-bounds Error");
                logger.log(Level.WARNING, "Delete Patient Command Error: Out-of-bounds Error");
            } catch (NumberFormatException e){
                System.out.println("Non-Numeric Error");
                logger.log(Level.WARNING, "Delete Patient Command Error: Non-Numerical Error");
            }
            break;

        case "select":
            try{
                return new SelectPatientCommand(parseInt(parts[1]));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out-of-bounds Error");
                logger.log(Level.WARNING, "Select Command Error: Out-of-bounds Error");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
                logger.log(Level.WARNING, "Select Command Error: Non-Numerical Error");
            }
            break;

        case "mark":
            try{
                return new MarkTaskCommand(parseInt(parts[1]));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out-of-bounds Error");
                logger.log(Level.WARNING, "Mark Command Error: Out-of-bounds Error");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
                logger.log(Level.WARNING, "Mark Command Error: Non-Numerical Error");
            }
            break;

        case "unmark":
            try{
                return new UnmarkTaskCommand(parseInt(parts[1]));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out-of-bounds Error");
                logger.log(Level.WARNING, "Select Command Error: Out-of-bounds Error");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
                logger.log(Level.WARNING, "Select Command Error: Non-Numerical Error");
            }
            break;

        default:
            System.out.println("Unknown command");
            logger.log(Level.WARNING, "Parser Command Error: No such command!");
        }
        return null;
    }
}
