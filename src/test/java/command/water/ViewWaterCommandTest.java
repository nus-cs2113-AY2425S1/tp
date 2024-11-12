package command.water;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import water.Water;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewWaterCommandTest {

    private ViewWaterCommand viewWaterCommand;
    private LocalDate date;

    @Mock
    private History mockHistory;

    @Mock
    private DailyRecord mockDailyRecord;

    @Mock
    private Water mockWater;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        date = LocalDate.now();

        // Set up History mock to return a DailyRecord for the specified date
        when(mockHistory.getRecordByDate(date)).thenReturn(mockDailyRecord);

        // Set up DailyRecord mock to return a Water object
        when(mockDailyRecord.getWaterFromRecord()).thenReturn(mockWater);

        when(mockWater.toString()).thenReturn("Sample Water Record");

        viewWaterCommand = new ViewWaterCommand(date);
    }

    @Test
    public void testExecuteHappyPath() {
        String expectedWaterMessage = "Water intake for " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                + ": \n\n" + "Sample Water Record";
        CommandResult expectedResult = new CommandResult(expectedWaterMessage);

        CommandResult result = viewWaterCommand.execute(mockHistory);

        verify(mockHistory).getRecordByDate(date);
        verify(mockDailyRecord).getWaterFromRecord();

        assertEquals(expectedResult, result,
                "Execution should return a CommandResult with the correct water record output.");
    }



    @Test
    public void testExecuteEdgeCaseNullDailyRecord() {
        when(mockHistory.getRecordByDate(date)).thenReturn(null);

        assertThrows(AssertionError.class, () -> viewWaterCommand.execute(mockHistory), "Executing " +
                "ViewWaterCommand with a null DailyRecord should throw an AssertionError.");
    }

    @Test
    public void testConstructorEdgeCaseNullDate() {
        assertThrows(AssertionError.class, () -> new ViewWaterCommand(null), "Creating " +
                "ViewWaterCommand with a null date should throw an AssertionError.");
    }
}
