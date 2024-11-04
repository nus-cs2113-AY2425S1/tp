package command.water;

import command.CommandResult;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import programme.ProgrammeList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class WaterCommandTest {

    private WaterCommand waterCommand;
    private LocalDate date;

    @Mock
    private History mockHistory;

    @Mock
    private ProgrammeList mockProgrammeList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        date = LocalDate.now();
        waterCommand = new TestWaterCommand(date);  // Using a concrete subclass for testing
    }

    @Test
    public void testConstructorHappyPath() {
        assertEquals(date, waterCommand.date, "Date should be initialized correctly in WaterCommand.");
    }

    @Test
    public void testConstructorEdgeCaseNullDate() {
        assertThrows(AssertionError.class, () -> new TestWaterCommand(null),
                "Creating WaterCommand with null date should throw an AssertionError.");
    }

    @Test
    public void testExecuteWithProgrammeListAndHistoryHappyPath() {
        CommandResult expected = new CommandResult("Executed with history");

        CommandResult result = waterCommand.execute(mockProgrammeList, mockHistory);

        assertEquals(expected, result, "Execute should return a CommandResult with the correct message.");
        verify(mockHistory).getRecordByDate(date);
    }

    // Concrete subclass of WaterCommand for testing purposes
    private static class TestWaterCommand extends WaterCommand {
        public TestWaterCommand(LocalDate date) {
            super(date);
        }

        @Override
        public CommandResult execute(History history) {
            history.getRecordByDate(date);
            return new CommandResult("Executed with history");
        }
    }
}
