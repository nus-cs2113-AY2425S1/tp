package command;

import core.History;
import core.Ui;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InvalidCommandTest {
    @Test
    public void testIsExit_returnsFalse() {
        InvalidCommand invalidCommand = new InvalidCommand();
        assertFalse(invalidCommand.isExit());
    }

    @Test
    public void testExecute_showsInvalidCommandMessage() {
        Ui mockUi = mock(Ui.class);
        ProgrammeList mockPList = mock(ProgrammeList.class);
        History mockHistory = mock(History.class);

        InvalidCommand invalidCommand = new InvalidCommand();

        invalidCommand.execute(mockUi, mockPList, mockHistory);

        //just checked the Ui.showMsg is called, the string input does not matter
        verify(mockUi).showMsg(anyString());
    }
}
