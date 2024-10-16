package seedu.duke.parser;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    @Test
    public void parseCommandAdd() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new Parser("add Tom", mainState).parseCommand();
        assertEquals(true, returnedCommand != null);
    }

    @Test
    public void parseCommandList() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new Parser("list", mainState).parseCommand();
        assertNotNull(returnedCommand);

        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand1 = new Parser("list", taskState).parseCommand();
        assertNotNull(returnedCommand1);
    }

    @Test
    public void parseCommandDel() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new Parser("delete 0", mainState).parseCommand();
        assertNotNull(returnedCommand);

        Command returnedCommand1 = new Parser("delete Tom", mainState).parseCommand();
        try{
            assertNull(returnedCommand1);
        } catch(Exception e){
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void parseCommandSelect() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new Parser("select 0",mainState).parseCommand();
        assertNotNull(returnedCommand);

        Command returnedCommand1 = new Parser("select Tom",mainState).parseCommand();
        try{
            assertNull(returnedCommand1);
        } catch(Exception e){
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void parseCommandMark() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new Parser("mark 0", taskState).parseCommand();
        assertNotNull(returnedCommand);

        Command returnedCommand1 = new Parser("mark Tom",taskState).parseCommand();
        try{
            assertEquals(false, returnedCommand1 != null);
        } catch(Exception e){
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void parseCommandUnmark() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new Parser("mark 0",taskState).parseCommand();
        assertEquals(true, returnedCommand != null);

        Command returnedCommand1 = new Parser("mark Tom", taskState).parseCommand();
        try{
            assertEquals(false, returnedCommand1 != null);
        } catch(Exception e){
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

}
