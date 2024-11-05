package command.history;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HistoryCommandTest {

    private HistoryCommand historyCommand;

    @Mock
    private History mockHistory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        historyCommand = new HistoryCommand();
    }



    @Test
    public void testExecuteWithHistory() {
        // Arrange
        List<String> sampleHistory = new ArrayList<>();
        sampleHistory.add("Sample History Entry 1");
        sampleHistory.add("Sample History Entry 2");

        when(mockHistory.getHistory()).thenReturn((LinkedHashMap<LocalDate, DailyRecord>) sampleHistory);
        CommandResult expectedResult = new CommandResult(sampleHistory.toString());

        // Act
        CommandResult result = historyCommand.execute(null, mockHistory);

        // Assert
        assertEquals(expectedResult, result, "Execution should return a CommandResult with the full history.");
    }

    @Test
    public void testExecuteWithEmptyHistory() {
        // Arrange
        when(mockHistory.getHistory()).thenReturn("");
        CommandResult expectedResult = new CommandResult("No history available.");

        // Act
        CommandResult result = historyCommand.execute(null, mockHistory);

        // Assert
        assertEquals(expectedResult, result, "Execution should return a CommandResult indicating no history is available.");
    }
}
