package command.programme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCommandTest {
    @Test
    public void testIsExit_returnsFalse() {
        DeleteCommand deleteCommand = new DeleteCommand(0);
        assertFalse(deleteCommand.isExit());
    }
}
