package seedu.duke.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommandAdd() {
        try {
            Assertions.assertEquals(0, new Parser("add",1).parseCommand());
        } catch (Exception e) {
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void parseCommandAddIndex() {
        try {
            Assertions.assertEquals(1, new Parser("add 1",1).parseCommand());
        } catch (Exception e) {
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void parseCommandlist() {
        Assertions.assertEquals(1, new Parser("list",0).parseCommand());
        Assertions.assertEquals(2, new Parser("list",1).parseCommand());
    }

    @Test
    public void parseCommandDel() {
        try {
            Assertions.assertEquals(0, new Parser("delete", 1).parseCommand());
        } catch (Exception e) {
            assertEquals("Non-Numerical Error", e.getMessage());
        }
    }

    @Test
    public void parseCommandDelIndex() {
        Assertions.assertEquals(4, new Parser("delete 5",1).parseCommand());
    }
}
