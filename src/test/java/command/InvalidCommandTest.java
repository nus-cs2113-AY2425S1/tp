package command;

import history.History;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;


public class InvalidCommandTest {

    @Test
    public void testExecute_showsInvalidCommandMessage() {
        ProgrammeList mockPList = mock(ProgrammeList.class);
        History mockHistory = mock(History.class);

        InvalidCommand invalidCommand = new InvalidCommand();
        CommandResult result = invalidCommand.execute(mockPList, mockHistory);
        assertNotNull(result);
    }
}
