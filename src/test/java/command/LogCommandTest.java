package command;

import core.History;
import core.Ui;
import org.junit.jupiter.api.Test;
import programme.Day;
import programme.ProgrammeList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

public class LogCommandTest {
    @Test
    public void testIsExit_ReturnsFalse() {
        LogCommand logCommand = new LogCommand(0,0, LocalDate.now());
        assertFalse(logCommand.isExit());
    }

    @Test
    public void testExecute_LogsDayIntoHistory() {
        Ui mockUi = mock(Ui.class);
        ProgrammeList mockPList = mock(ProgrammeList.class);
        History mockHistory = mock(History.class);
        Day mockDay = mock(Day.class);

        int progIndex = 0;
        int dayIndex = 0;
        LocalDate date = LocalDate.of(2024, 12, 12);

        // Mock ProgrammeList behavior to return the mock Day when getDay() is called
        when(mockPList.getDay(progIndex, dayIndex)).thenReturn(mockDay);

        LogCommand logCommand = new LogCommand(progIndex, dayIndex, date);

        logCommand.execute(mockUi, mockPList, mockHistory);

        verify(mockPList).getDay(progIndex, dayIndex);
        verify(mockHistory).logDay(mockDay, date);
        verify(mockUi).showMsg(anyString());
    }
}
