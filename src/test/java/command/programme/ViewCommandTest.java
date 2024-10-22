package command.programme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ViewCommandTest {
    @Test
    public void testIsExit_returnsFalse() {
        ViewCommand viewCommand = new ViewCommand(0);
        assertFalse(viewCommand.isExit());
    }

    @Test
    public void testNegativeInteger_throwsException() {}
}
