package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.data.state.State;

public class Parser {

    public Command parseCommand(String line, State state){
        if (line == null || line.isEmpty()){
            System.out.println("Command is empty");
            return null;
        }
        String[] parts = line.split(" ");

        switch(parts[0]){
        case "todo":
            try{
                return new AddTodoParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "Deadline":
            try{
                return new AddDeadlineParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "add":
            try{
                return new AddParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "list":
            try{
                return new ListParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "delete":
            try{
                return new DeleteParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "select":
            try{
                return new SelectParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "mark":
            try{
                return new MarkParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "unmark":
            try{
                return new UnmarkParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "back":
            try{
                return new BackParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "find":
            try{
                return new FindParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        case "exit":
            try{
                return new ExitParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
            }
            break;

        default:
            System.out.println("Unknown command");
        }
        return null;
    }
}
