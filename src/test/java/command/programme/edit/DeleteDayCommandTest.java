package command.programme.edit;

import command.CommandResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.Day;
import programme.ProgrammeList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteDayCommandTest {

    private static final int VALID_PROGRAMME_ID = 0;
    private static final int VALID_DAY_ID = 0;
    private static final int INVALID_PROGRAMME_ID = -2;
    private static final int INVALID_DAY_ID = -1;
    private static final int OUT_OF_RANGE_PROGRAMME_ID = 999;
    private static final int OUT_OF_RANGE_DAY_ID = 999;

    private ProgrammeList programmeList;
    private Day day;
    private DeleteDayCommand command;

    @BeforeEach
    void setUp() {
        // Set up a ProgrammeList with one programme and one day
        programmeList = new ProgrammeList();
        day = new Day("Day 1", new ArrayList<>());
        ArrayList<Day> days = new ArrayList<>();
        days.add(day);
        programmeList.insertProgramme("Mock Programme", days);

        // Initialize DeleteDayCommand with valid IDs
        command = new DeleteDayCommand(VALID_PROGRAMME_ID, VALID_DAY_ID);
    }

    // Test for constructor with valid inputs
    @Test
    void constructor_initializesWithValidParameters() {
        assertDoesNotThrow(() -> new DeleteDayCommand(VALID_PROGRAMME_ID, VALID_DAY_ID));
    }

    // Edge case for constructor: Negative programme ID
    @Test
    void constructor_throwsAssertionErrorIfProgrammeIdIsNegative() {
        assertThrows(AssertionError.class, () -> new DeleteDayCommand(INVALID_PROGRAMME_ID, VALID_DAY_ID));
    }

    // Edge case for constructor: Negative day ID
    @Test
    void constructor_throwsAssertionErrorIfdayIndexIsNegative() {
        assertThrows(AssertionError.class, () -> new DeleteDayCommand(VALID_PROGRAMME_ID, INVALID_DAY_ID));
    }

    // Test for execute method: successfully deletes day and returns success message
    @Test
    void execute_deletesDayFromProgramme_returnsSuccessMessage() {
        String expectedMessage = String.format(DeleteDayCommand.SUCCESS_MESSAGE_FORMAT, day);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        CommandResult actualResult = command.execute(programmeList);
        assertEquals(expectedResult, actualResult);
    }

    // Edge case for execute: Programme list is null
    @Test
    void execute_throwsAssertionErrorIfProgrammesIsNull() {
        assertThrows(AssertionError.class, () -> command.execute(null));
    }

    // Edge case for execute: Nonexistent programme ID
    @Test
    void execute_throwsIndexOutOfBoundsIfProgrammeIdDoesNotExist() {
        DeleteDayCommand invalidCommand = new DeleteDayCommand(OUT_OF_RANGE_PROGRAMME_ID, VALID_DAY_ID);
        assertThrows(IndexOutOfBoundsException.class, () -> invalidCommand.execute(programmeList));
    }

    // Edge case for execute: Nonexistent day ID within existing programme
    @Test
    void execute_throwsIndexOutOfBoundsIfdayIndexDoesNotExist() {
        DeleteDayCommand invalidCommand = new DeleteDayCommand(VALID_PROGRAMME_ID, OUT_OF_RANGE_DAY_ID);
        assertThrows(IndexOutOfBoundsException.class, () -> invalidCommand.execute(programmeList));
    }
}
