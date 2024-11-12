package command.history;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static common.Utils.formatDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewHistoryCommandTest {

    private ViewHistoryCommand viewHistoryCommand;
    private History history;
    private LocalDate testDate;

    @BeforeEach
    public void setUp() {
        history = new History();
        testDate = LocalDate.now();
        viewHistoryCommand = new ViewHistoryCommand(testDate);
    }

    @Test
    public void testExecuteHappyPathSingleRecordForDate() {
        // Happy Path: Record exists for the specified date
        DailyRecord dailyRecord = new DailyRecord();
        history.logRecord(testDate, dailyRecord);

        CommandResult expectedResult = new CommandResult(dailyRecord.toString());

        // Act
        CommandResult result = viewHistoryCommand.execute(history);

        // Assert
        assertEquals(expectedResult, result, "Execution should return the correct record for the specified date.");
    }

    @Test
    public void testExecuteEdgeCaseNoRecordForDate() {
        // Edge Case: No record exists for the specified date
        CommandResult expectedResult = new CommandResult("No record found for " + formatDate(testDate));

        // Act
        CommandResult result = viewHistoryCommand.execute(history);

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

        CommandResult expectedResult = new CommandResult("No record found for " + formatDate(testDate));

        // Act
        CommandResult result = viewHistoryCommand.execute(history);

        // Assert
        assertEquals(expectedResult, result,
                "Execution should return 'No record found' if the record exists only for a different date.");
    }
}

