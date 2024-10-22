package command;

import history.History;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class HistoryCommandTest {

    @Test
    public void testExecute_doesNotThrowException() {
        ProgrammeList mockPList = mock(ProgrammeList.class);
        History mockHistory = mock(History.class);

        HistoryCommand historyCommand = new HistoryCommand();

        assertDoesNotThrow(() -> historyCommand.execute(mockPList, mockHistory));
    }

    @Test
    public void testExecute_showsHistoryWithData() {
        ProgrammeList mockPList = mock(ProgrammeList.class);
        History mockHistory = mock(History.class);

        //Mock a history Data
        String historyData = "12/12/2024: ONE - Bench Press 3x12 30kg, Squat 3x12 50kg";
        when(mockHistory.toString()).thenReturn(historyData);

        HistoryCommand historyCommand = new HistoryCommand();

        CommandResult result = historyCommand.execute(mockPList, mockHistory);

        assertNotNull(result);
    }
}
