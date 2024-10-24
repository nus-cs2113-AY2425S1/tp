package seedu.duke.parser;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserTest {
    @Test
    public void parseCommandAdd() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new AddParser().execute("add Tom", mainState);
        assertEquals(true, returnedCommand != null);
    }

    @Test
    public void parseCommandList() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new ListParser().execute("list", mainState);
        assertNotNull(returnedCommand);

        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand1 = new ListParser().execute("list", mainState);
        assertNotNull(returnedCommand1);
    }

    @Test
    public void parseCommandDel() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new DeleteParser().execute("delete 0", mainState);
        assertNotNull(returnedCommand);

        boolean thrown = false;
        try{
            new DeleteParser().execute("delete Tom", mainState);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void parseCommandSelect() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new SelectParser().execute("select 0", mainState);
        assertNotNull(returnedCommand);

        boolean thrown = false;
        try{
            new SelectParser().execute("select Tom", mainState);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void parseCommandMark() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new MarkParser().execute("mark 0", taskState);
        assertNotNull(returnedCommand);

        boolean thrown = false;
        try{
            new MarkParser().execute("mark Tom", taskState);
        } catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void parseCommandUnmark() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new UnmarkParser().execute("mark 0",taskState);
        assertEquals(true, returnedCommand != null);

        boolean thrown = false;
        try{
            new UnmarkParser().execute("mark Tom",taskState);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }
}
