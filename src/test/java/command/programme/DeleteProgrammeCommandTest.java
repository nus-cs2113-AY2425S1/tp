// @@author nirala-ts

package command.programme;

import command.CommandResult;
import exceptions.ProgrammeExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;
import programme.Day;
import programme.Programme;
import history.History;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteProgrammeCommandTest {

    private static final int VALID_PROGRAMME_ID = 0;
    private static final int INVALID_PROGRAMME_ID = -2;
    private static final int OUT_OF_RANGE_PROGRAMME_ID = 999;

    private ProgrammeList programmeList;
    private Programme expectedProgramme;
    private DeleteProgrammeCommand command;
    private History history;
    
    @BeforeEach
    void setUp() {
        programmeList = new ProgrammeList();
        history = new History();
        Day day = new Day("Day 1", new ArrayList<>());
        ArrayList<Day> days = new ArrayList<>();
        days.add(day);
        programmeList.insertProgramme("Mock Programme", days);

        expectedProgramme = new Programme("Mock Programme", days);
        command = new DeleteProgrammeCommand(VALID_PROGRAMME_ID);
    }

    @Test
    void constructor_initializesWithValidParameters() {
        assertDoesNotThrow(() -> new DeleteProgrammeCommand(VALID_PROGRAMME_ID));
    }

    @Test
    void constructor_throwsAssertionErrorIfProgrammeIdIsNegative() {
        assertThrows(AssertionError.class, () -> new DeleteProgrammeCommand(INVALID_PROGRAMME_ID));
    }

    @Test
    void execute_deletesDayFromProgramme_returnsSuccessMessage() {
        String expectedMessage = String.format(DeleteProgrammeCommand.SUCCESS_MESSAGE_FORMAT, expectedProgramme);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        CommandResult actualResult = command.execute(programmeList, history);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void execute_throwsIndexOutOfBoundsIfProgrammeIdDoesNotExist() {
        DeleteProgrammeCommand invalidCommand = new DeleteProgrammeCommand(OUT_OF_RANGE_PROGRAMME_ID);
        assertThrows(ProgrammeExceptions.class, () -> invalidCommand.execute(programmeList, history),
                "Expected IndexOutOfBoundsBuffBuddyException for an out-of-range programme ID");
    }
}
