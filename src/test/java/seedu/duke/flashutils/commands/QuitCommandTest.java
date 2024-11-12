package seedu.duke.flashutils.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuitCommandTest {

    @Test
    public void testQuitCommandSuccessMessage() {
        QuitCommand command = new QuitCommand();
        CommandResult result = command.execute();

        assertEquals("Quit Flash Session", result.getFeedbackToUser());
    }

}
