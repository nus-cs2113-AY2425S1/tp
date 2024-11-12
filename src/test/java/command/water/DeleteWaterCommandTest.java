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

public class DeleteWaterCommandTest {

    private DeleteWaterCommand deleteWaterCommand;
    private float sampleWaterAmount;
    private LocalDate date;

    @Mock
    private History mockHistory;

    @Mock
    private DailyRecord mockDailyRecord;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);

        sampleWaterAmount = 1.5f;
        date = LocalDate.now();

        when(mockHistory.getRecordByDate(date)).thenReturn(mockDailyRecord);

        when(mockDailyRecord.removeWaterFromRecord(0)).thenReturn(sampleWaterAmount);

        deleteWaterCommand = new DeleteWaterCommand(0, date);
    }

    @Test
    public void testExecuteHappyPath() {
        CommandResult expectedResult = new CommandResult(sampleWaterAmount +
                " liters of water has been deleted");

        CommandResult result = deleteWaterCommand.execute(mockHistory);

        verify(mockDailyRecord).removeWaterFromRecord(0);
        assertEquals(expectedResult, result,
                "Execution should return a CommandResult with the correct success message.");
    }

    @Test
    public void testExecuteEdgeCaseNullDailyRecord() {
        when(mockHistory.getRecordByDate(date)).thenReturn(null);

        assertThrows(AssertionError.class, () -> deleteWaterCommand.execute(mockHistory), "Executing " +
                "DeleteWaterCommand without a valid DailyRecord should throw an AssertionError.");
    }

    @Test
    public void testExecuteEdgeCaseInvalidIndex() {
        // Set up DailyRecord mock to throw IndexOutOfBoundsException when an invalid index is used
        when(mockDailyRecord.removeWaterFromRecord(5)).thenThrow(new IndexOutOfBoundsException("Invalid index"));

        DeleteWaterCommand invalidIndexCommand = new DeleteWaterCommand(5, date);

        assertThrows(IndexOutOfBoundsException.class, () -> invalidIndexCommand.execute(mockHistory), "Executing " +
                "DeleteWaterCommand with an invalid index should throw IndexOutOfBoundsException.");
    }

    @Test
    public void testConstructorEdgeCaseNegativeIndex() {
        assertThrows(AssertionError.class, () -> new DeleteWaterCommand(-1, date), "Creating " +
                "DeleteWaterCommand with negative index should throw AssertionError.");
    }
}
