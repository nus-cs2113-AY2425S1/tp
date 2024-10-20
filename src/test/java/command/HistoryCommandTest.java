package command;

import core.History;
import core.Ui;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class HistoryCommandTest {
    @Test
    public void testIsExit_ReturnsFalse() {
        HistoryCommand historyCommand = new HistoryCommand();
        assertFalse(historyCommand.isExit());
    }

    @Test
    public void testExecute_DoesNotThrowException() {
        Ui mockUi = mock(Ui.class);
        ProgrammeList mockPList = mock(ProgrammeList.class);
        History mockHistory = mock(History.class);

        HistoryCommand historyCommand = new HistoryCommand();

        assertDoesNotThrow(() -> historyCommand.execute(mockUi, mockPList, mockHistory));

    }
}
