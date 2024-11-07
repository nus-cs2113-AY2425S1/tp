package command.history;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryCommandTest {

    private HistoryCommand historyCommand;
    private History history;
    private ProgrammeList programmeList;

    @BeforeEach
    public void setUp() {
        historyCommand = new HistoryCommand();
        history = new History();
        programmeList = new ProgrammeList(); // Assuming ProgrammeList is initialized this way
    }

    @Test
    public void testExecuteHappyPathSingleRecord() {
        // Happy Path: Single Record in History
        LocalDate date = LocalDate.now();
        DailyRecord dailyRecord = new DailyRecord();
        history.logRecord(date, dailyRecord);

        CommandResult expectedResult = new CommandResult(history.toString());

        // Act
        CommandResult result = historyCommand.execute(programmeList, history);

        // Assert
        assertEquals(expectedResult, result, "Execution with a single record should output the correct history.");
    }

    @Test
    public void testExecuteEdgeCaseEmptyHistory() {
        // Edge Case: Empty History
        CommandResult expectedResult = new CommandResult("No history available.");

        // Act
        CommandResult result = historyCommand.execute(programmeList, history);

        // Assert
        assertEquals(expectedResult, result, "Execution with an empty history should return 'No history available.'");
    }

    @Test
    public void testExecuteEdgeCaseMultipleRecordsInHistory() {
        // Edge Case: Multiple Records in History
        LocalDate date1 = LocalDate.now().minusDays(1);
        LocalDate date2 = LocalDate.now();

        DailyRecord record1 = new DailyRecord();
        DailyRecord record2 = new DailyRecord();

        history.logRecord(date1, record1);
        history.logRecord(date2, record2);

        CommandResult expectedResult = new CommandResult(history.toString());

        // Act
        CommandResult result = historyCommand.execute(programmeList, history);

        // Assert
        assertEquals(expectedResult, result, "Execution with multiple records should output the correct history.");
    }
}

