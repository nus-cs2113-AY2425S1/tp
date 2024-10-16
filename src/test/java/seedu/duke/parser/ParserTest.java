package seedu.duke.parser;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommandAdd() {
        Command returnedCommand = new Parser("add Tom",0).parseCommand();
        assertEquals(true, returnedCommand != null);
    }

    @Test
    public void parseCommandList() {
        Command returnedCommand = new Parser("add Tom",0).parseCommand();
        assertEquals(true, returnedCommand != null);

        Command returnedCommand1 = new Parser("add Tom",1).parseCommand();
        assertEquals(true, returnedCommand1 != null);
    }

    @Test
    public void parseCommandDel() {
        Command returnedCommand = new Parser("delete 0",0).parseCommand();
        assertEquals(true, returnedCommand != null);

        Command returnedCommand1 = new Parser("delete Tom",0).parseCommand();
        try{
            assertEquals(false, returnedCommand1 != null);
        } catch(Exception e){
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void parseCommandSelect() {
        Command returnedCommand = new Parser("select 0",0).parseCommand();
        assertEquals(true, returnedCommand != null);

        Command returnedCommand1 = new Parser("select Tom",0).parseCommand();
        try{
            assertEquals(false, returnedCommand1 != null);
        } catch(Exception e){
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void parseCommandMark() {
        Command returnedCommand = new Parser("mark 0",0).parseCommand();
        assertEquals(true, returnedCommand != null);

        Command returnedCommand1 = new Parser("mark Tom",0).parseCommand();
        try{
            assertEquals(false, returnedCommand1 != null);
        } catch(Exception e){
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void parseCommandUnmark() {
        Command returnedCommand = new Parser("mark 0",0).parseCommand();
        assertEquals(true, returnedCommand != null);

        Command returnedCommand1 = new Parser("mark Tom",0).parseCommand();
        try{
            assertEquals(false, returnedCommand1 != null);
        } catch(Exception e){
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

}
