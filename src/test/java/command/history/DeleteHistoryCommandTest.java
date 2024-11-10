package command.history;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static common.Utils.formatDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteHistoryCommandTest {

    private DeleteHistoryCommand deleteHistoryCommand;
    private History history;
    private LocalDate testDate;

    @BeforeEach
    public void setUp() {
        history = new History();
        testDate = LocalDate.now();
        deleteHistoryCommand = new DeleteHistoryCommand(testDate);
    }

    @Test
    public void testExecuteHappyPathDeleteExistingRecord() {
        // Happy Path: Record exists and is deleted successfully
        DailyRecord dailyRecord = new DailyRecord();
        history.logRecord(testDate, dailyRecord);

        CommandResult expectedResult = new CommandResult("Deleted record: \n" + dailyRecord.toString());

        // Act
        CommandResult result = deleteHistoryCommand.execute(history);

        // Assert
        assertEquals(expectedResult, result, "Execution should return the deleted record for the specified date.");
    }

    @Test
    public void testExecuteEdgeCaseNoRecordForDate() {
        // Edge Case: No record exists for the specified date
        CommandResult expectedResult = new CommandResult("No record found at " + formatDate(testDate));

        // Act
        CommandResult result = deleteHistoryCommand.execute(history);

        // Assert
        assertEquals(expectedResult, result,
                "Execution should return 'No record found' when no record exists for the specified date.");
    }

    @Test
    public void testExecuteEdgeCaseRecordExistsForDifferentDate() {
        // Edge Case: Record exists, but for a different date
        LocalDate differentDate = testDate.minusDays(1);
        DailyRecord dailyRecord = new DailyRecord();
        history.logRecord(differentDate, dailyRecord);

        CommandResult expectedResult = new CommandResult("No record found at " + formatDate(testDate));

        // Act
        CommandResult result = deleteHistoryCommand.execute(history);

        // Assert
        assertEquals(expectedResult, result,
                "Execution should return 'No record found' if the record exists only for a different date.");
    }
}

