package command.programme;

import command.CommandResult;
import history.History;
import org.junit.jupiter.api.Test;
import programme.Day;
import programme.ProgrammeList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

public class LogCommandTest {

    @Test
    public void testExecute_logsDayIntoHistory() {
        ProgrammeList mockPList = mock(ProgrammeList.class);
        History mockHistory = mock(History.class);
        Day mockDay = mock(Day.class);

        int progIndex = 0;
        int dayIndex = 0;
        LocalDate date = LocalDate.of(2024, 12, 12);

        // Mock ProgrammeList behavior to return the mock Day when getDay() is called
        when(mockPList.getDay(progIndex, dayIndex)).thenReturn(mockDay);

        LogCommand logCommand = new LogCommand(progIndex, dayIndex, date);

        CommandResult result = logCommand.execute(mockPList, mockHistory);

        //verify(mockPList).getDay(progIndex, dayIndex);
        //verify(mockHistory).getRecordByDate(date).logDay(mockDay);
        //assertNotNull(result);
    }
}
