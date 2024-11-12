package command.water;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddWaterCommandTest {

    private AddWaterCommand addWaterCommand;
    private float waterAmount;
    private LocalDate date;

    @Mock
    private History mockHistory;

    @Mock
    private DailyRecord mockDailyRecord;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);

        waterAmount = 1.5f;
        date = LocalDate.now();

        when(mockHistory.getRecordByDate(date)).thenReturn(mockDailyRecord);

        addWaterCommand = new AddWaterCommand(waterAmount, date);
    }

    @Test
    public void testExecuteHappyPath() {
        CommandResult expectedResult = new CommandResult(waterAmount + " liters of water has been added");

        CommandResult result = addWaterCommand.execute(mockHistory);

        verify(mockDailyRecord).addWaterToRecord(waterAmount);
        assertEquals(expectedResult, result,
                "Execution should return a CommandResult with the correct success message.");
    }

    @Test
    public void testExecuteEdgeCaseNullDailyRecord() {
        // Set up History mock to return null for the DailyRecord
        when(mockHistory.getRecordByDate(date)).thenReturn(null);

        assertThrows(AssertionError.class, () -> addWaterCommand.execute(mockHistory),
                "Executing AddWaterCommand without a valid DailyRecord should throw an AssertionError.");
    }

    @Test
    public void testExecuteEdgeCaseNegativeWaterAmount() {
        assertThrows(AssertionError.class, () -> new AddWaterCommand(-1.0f, date),
                "Creating AddWaterCommand with a negative water amount should throw AssertionError.");
    }
}
