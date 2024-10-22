package command.programme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class StartCommandTest {
    @Test
    public void testIsExit_returnsFalse() {
        StartCommand startCommand = new StartCommand(0);
        assertFalse(startCommand.isExit());
    }
}
