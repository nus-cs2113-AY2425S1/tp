package command.programme;

import command.CommandResult;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.Day;
import programme.Programme;
import programme.ProgrammeList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateProgrammeCommandTest {

    private final String VALID_PROGRAMME_NAME = "New Programme";
    private final String EMPTY_PROGRAMME_NAME = "";
    private final ArrayList<Day> VALID_PROGRAMME_CONTENTS = new ArrayList<>();
    private final ArrayList<Day> NULL_PROGRAMME_CONTENTS = null;
    private ProgrammeList programmeList;
    private History history;
    private CreateProgrammeCommand command;

    @BeforeEach
    void setUp() {
        programmeList = new ProgrammeList();
        history = new History();
        command = new CreateProgrammeCommand(VALID_PROGRAMME_NAME, VALID_PROGRAMME_CONTENTS);
    }

    // Happy path test for the "execute" method
    @Test
    void execute_createsProgrammeSuccessfully_returnsSuccessMessage() {
        String expectedMessage = String.format(CreateProgrammeCommand.SUCCESS_MESSAGE_FORMAT, new Programme(VALID_PROGRAMME_NAME, VALID_PROGRAMME_CONTENTS));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        CommandResult actualResult = command.execute(programmeList, history);
        assertEquals(expectedResult, actualResult);
    }

    // Edge case test: Programme list is null
    @Test
    void execute_throwsAssertionErrorIfProgrammesIsNull() {
        assertThrows(AssertionError.class, () -> new CreateProgrammeCommand(VALID_PROGRAMME_NAME, NULL_PROGRAMME_CONTENTS));
    }

    // Edge case test: Programme name is empty
    @Test
    void execute_createsProgrammeWithEmptyName() {
        assertThrows(AssertionError.class, () -> new CreateProgrammeCommand(EMPTY_PROGRAMME_NAME, VALID_PROGRAMME_CONTENTS));
    }
}
