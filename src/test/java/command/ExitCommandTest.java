package command;

import core.History;
import core.Ui;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ExitCommandTest {
    @Test
    public void testIsExit_ReturnsTrue() {
        ExitCommand exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }

    @Test
    public void testExecute_DoesNotThrowException() {
        Ui mockUi = mock(Ui.class);
        ProgrammeList mockPList = mock(ProgrammeList.class);
        History mockHistory = mock(History.class);

        ExitCommand exitCommand = new ExitCommand();

        assertDoesNotThrow(() -> exitCommand.execute(mockUi, mockPList, mockHistory));

    }
}
