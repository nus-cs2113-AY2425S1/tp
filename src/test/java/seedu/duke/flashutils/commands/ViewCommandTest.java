package seedu.duke.flashutils.commands; 

import org.junit.jupiter.api.Test;
import seedu.duke.flashutils.types.FlashCardSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewCommandTest {
      
    @Test
    public void viewCommandTestEmpty() {
        FlashCardSet module1 = new FlashCardSet("Module 1"); 
        assertTrue(module1.getFlashCardSet().isEmpty());
    }

    @Test
    public void viewCommandTestConstructor() {
        FlashCardSet module2 = new FlashCardSet("Module 2"); 
        ViewCommand command = new ViewCommand(module2); 
        assertEquals(module2, command.getTargetSet());
    }
}
