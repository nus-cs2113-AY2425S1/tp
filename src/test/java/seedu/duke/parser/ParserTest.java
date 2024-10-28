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
        Command returnedCommand = new AddParser().execute("add Tom /tag tag", mainState);
        assertEquals(true, returnedCommand != null);

        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand1 = new AddParser().execute("add Tom /tag tag", taskState);
        assertEquals(true, returnedCommand1 == null);

        boolean thrown = false;
        try{
            new AddParser().execute("Invalid command", mainState);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void parseCommandTodo() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new AddTodoParser().execute("todo CS2113 /tag tag", taskState);
        assertEquals(true, returnedCommand != null);

        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand1 = new AddTodoParser().execute("todo CS2113 /tag tag", mainState);
        assertEquals(true, returnedCommand1 == null);

        boolean thrown = false;
        try{
            new AddTodoParser().execute("Invalid command", taskState);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void parseCommandDeadline() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new AddDeadlineParser().execute("deadline CS2113 /by deadline", taskState);
        assertEquals(true, returnedCommand != null);

        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand1 = new AddDeadlineParser().execute("deadline CS2113 /by deadline", mainState);
        assertEquals(true, returnedCommand1 == null);

        boolean thrown = false;
        try{
            new AddDeadlineParser().execute("Invalid command", taskState);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void parseCommandRepeat() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new AddRepeatParser().execute("repeat Tom /every occurrence", taskState);
        assertEquals(true, returnedCommand != null);

        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand1 = new AddRepeatParser().execute("repeat Tom /every occurrence", mainState);
        assertEquals(true, returnedCommand1 == null);

        boolean thrown = false;
        try{
            new AddDeadlineParser().execute("Invalid command", taskState);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void parseCommandList() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new ListParser().execute("list", mainState);
        assertNotNull(returnedCommand);

        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand1 = new ListParser().execute("list", taskState);
        assertNotNull(returnedCommand1);
    }

    @Test
    public void parseCommandDel() {
        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand = new DeleteParser().execute("delete 0", mainState);
        assertEquals(true, returnedCommand != null);

        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand1 = new DeleteParser().execute("delete 0", taskState);
        assertEquals(true, returnedCommand1 != null);

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

        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand1 = new SelectParser().execute("select 0", taskState);
        assertEquals(true, returnedCommand1 == null);

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
        assertEquals(true, returnedCommand != null);

        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand1 = new MarkParser().execute("mark 0", mainState);
        assertEquals(true, returnedCommand1 == null);

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
        Command returnedCommand = new UnmarkParser().execute("unmark 0",taskState);
        assertEquals(true, returnedCommand != null);

        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand1 = new UnmarkParser().execute("unmark 0", mainState);
        assertEquals(true, returnedCommand1 == null);

        boolean thrown = false;
        try{
            new UnmarkParser().execute("mark Tom",taskState);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void parseCommandBack() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new BackParser().execute("back",taskState);
        assertEquals(true, returnedCommand != null);
    }

    @Test
    public void parseCommandFind() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new FindParser().execute("find key",taskState);
        assertEquals(true, returnedCommand != null);

        State mainState = new State(StateType.MAIN_STATE);
        Command returnedCommand1 = new FindParser().execute("find key",mainState);
        assertEquals(true, returnedCommand1 != null);

        boolean thrown = false;
        try{
            new FindParser().execute("Invalid command",taskState);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void parseCommandExit() {
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new ExitParser().execute("exit",taskState);
        assertEquals(true, returnedCommand != null);
    }

    @Test
    public void parser(){
        State mainState = new State(StateType.MAIN_STATE);
        State taskState = new State(StateType.TASK_STATE);
        Command returnedCommand = new Parser().parseCommand("add Tom",mainState);
        assertEquals(true, returnedCommand != null);

        Command returnedCommand1 = new Parser().parseCommand("todo task",taskState);
        assertEquals(true, returnedCommand1 != null);

        Command returnedCommand2 = new Parser().parseCommand("deadline task",taskState);
        assertEquals(true, returnedCommand2 != null);

        Command returnedCommand3 = new Parser().parseCommand("repeat task",taskState);
        assertEquals(true, returnedCommand3 != null);

        Command returnedCommand4 = new Parser().parseCommand("list",taskState);
        assertEquals(true, returnedCommand4 != null);

        Command returnedCommand5 = new Parser().parseCommand("delete 1",taskState);
        assertEquals(true, returnedCommand5 != null);

        Command returnedCommand6 = new Parser().parseCommand("select 1",mainState);
        assertEquals(true, returnedCommand6 != null);

        Command returnedCommand7 = new Parser().parseCommand("mark 1",taskState);
        assertEquals(true, returnedCommand7 != null);

        Command returnedCommand8 = new Parser().parseCommand("unmark 1",taskState);
        assertEquals(true, returnedCommand8 != null);

        Command returnedCommand9 = new Parser().parseCommand("back",taskState);
        assertEquals(true, returnedCommand9 != null);

        Command returnedCommand10 = new Parser().parseCommand("exit",taskState);
        assertEquals(true, returnedCommand10 != null);

        Command returnedCommand11 = new Parser().parseCommand("",taskState);
        assertEquals(true, returnedCommand11 == null);

        Command returnedCommand12 = new Parser().parseCommand("todo",taskState);
        assertEquals(true, returnedCommand12 == null);

        Command returnedCommand13 = new Parser().parseCommand("deadline",taskState);
        assertEquals(true, returnedCommand13 == null);

        Command returnedCommand14 = new Parser().parseCommand("repeat",taskState);
        assertEquals(true, returnedCommand14 == null);

        Command returnedCommand15 = new Parser().parseCommand("add",mainState);
        assertEquals(true, returnedCommand15 == null);

        Command returnedCommand16 = new Parser().parseCommand("delete",mainState);
        assertEquals(true, returnedCommand16 == null);

        Command returnedCommand17 = new Parser().parseCommand("select",mainState);
        assertEquals(true, returnedCommand17 == null);

        Command returnedCommand18 = new Parser().parseCommand("mark",taskState);
        assertEquals(true, returnedCommand18 == null);

        Command returnedCommand19 = new Parser().parseCommand("unmark",taskState);
        assertEquals(true, returnedCommand19 == null);

        Command returnedCommand20 = new Parser().parseCommand("find",mainState);
        assertEquals(true, returnedCommand20 == null);

        Command returnedCommand21 = new Parser().parseCommand("back",taskState);
        assertEquals(true, returnedCommand21 != null);

        Command returnedCommand22 = new Parser().parseCommand("exit",mainState);
        assertEquals(true, returnedCommand22 != null);

    }
}
