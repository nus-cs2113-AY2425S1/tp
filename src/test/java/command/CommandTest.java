package command;

import core.History;
import core.Ui;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandTest {
    @Test
    public void testIsExit_returnFalse() {
        Command command = new TestCommand();
        assertFalse(command.isExit());
    }

    @Test
    public void testExecute() {
        Ui mockUi = mock(Ui.class);
        ProgrammeList mockPList = mock(ProgrammeList.class);
        History mockHistory = mock(History.class);

        Command command = new TestCommand();

        command.execute(mockUi, mockPList, mockHistory);

        verify(mockUi).showMsg("Test Command Executed");
    }
}
