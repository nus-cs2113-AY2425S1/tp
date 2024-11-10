// @@author nirala-ts

package command.programme;

import command.CommandResult;
import exceptions.ProgrammeExceptions;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.Day;
import programme.Programme;
import programme.ProgrammeList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StartProgrammeCommandTest {

    private ProgrammeList programmeList;
    private History history;
    private StartProgrammeCommand command;

    @BeforeEach
    void setUp() {
        programmeList = new ProgrammeList();
        history = new History();

        ArrayList<Day> days1 = new ArrayList<>();
        days1.add(new Day("Day 1", new ArrayList<>()));
        programmeList.insertProgramme("Programme 1", days1);

        ArrayList<Day> days2 = new ArrayList<>();
        days2.add(new Day("Day 2", new ArrayList<>()));
        programmeList.insertProgramme("Programme 2", days2);
    }

    @Test
    void execute_startsProgrammeSuccessfully() {
        command = new StartProgrammeCommand(0);
        CommandResult result = command.execute(programmeList, history);
        assertNotNull(result);
        Programme startedProgramme = programmeList.getProgramme(0);
        String expectedMessage = String.format(StartProgrammeCommand.SUCCESS_MESSAGE_FORMAT, startedProgramme);
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void execute_invalidProgrammeIndex_throwsException() {
        command = new StartProgrammeCommand(5);
        assertThrows(ProgrammeExceptions.class, () -> command.execute(programmeList, history));
    }

    @Test
    void execute_nullProgrammeList_throwsException() {
        command = new StartProgrammeCommand(0);
        assertThrows(AssertionError.class, () -> command.execute(null, history));
    }
}

