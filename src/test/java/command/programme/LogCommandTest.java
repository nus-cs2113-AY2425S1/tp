//package command.programme;
//import org.junit.jupiter.api.Test;
//
//
//public class LogCommandTest {
//
//    @Test
//    public void testExecute_logsDayIntoHistory() {
//        ProgrammeList mockprogrammes = mock(ProgrammeList.class);
//        History mockHistory = mock(History.class);
//        Day mockDay = mock(Day.class);
//
//        int progIndex = 0;
//        int dayIndex = 0;
//        LocalDate date = LocalDate.of(2024, 12, 12);
//
//        // Mock ProgrammeList behavior to return the mock Day when getDay() is called
//        when(mockprogrammes.getDay(progIndex, dayIndex)).thenReturn(mockDay);
//
//        LogCommand logCommand = new LogCommand(progIndex, dayIndex, date);
//
//        CommandResult result = logCommand.execute(mockprogrammes, mockHistory);
//
//        verify(mockprogrammes).getDay(progIndex, dayIndex);
//        verify(mockHistory).getRecordByDate(date).logDay(mockDay);
//        assertNotNull(result);
//    }
//}
