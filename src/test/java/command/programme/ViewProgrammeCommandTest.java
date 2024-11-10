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

public class ViewProgrammeCommandTest {

    private ProgrammeList programmeList;
    private History history;
    private ViewProgrammeCommand command;

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
    void execute_viewsProgrammeSuccessfully() {
        command = new ViewProgrammeCommand(0);
        CommandResult result = command.execute(programmeList, history);
        assertNotNull(result);

        Programme viewedProgramme = programmeList.getProgramme(0);
        String expectedMessage = String.format(ViewProgrammeCommand.SUCCESS_MESSAGE_FORMAT, viewedProgramme);
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void execute_invalidProgrammeIndex_throwsException() {
        command = new ViewProgrammeCommand(5); // Invalid index
        assertThrows(ProgrammeExceptions.class, () -> command.execute(programmeList, history));
    }

    @Test
    void execute_nullProgrammeList_throwsException() {
        command = new ViewProgrammeCommand(0);
        assertThrows(AssertionError.class, () -> command.execute(null, history));
    }
}
