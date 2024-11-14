package tutorlink.attendance;

public class Attendance {
    private String attendanceVenue;
    private String attendanceDateTime; //modify this to accept LocalDateTime
    private boolean isPresent;

    public Attendance(String attendanceVenue, String localDateTime, boolean isPresent){
        this.attendanceVenue = attendanceVenue;
        this.attendanceDateTime = localDateTime;
        this.isPresent = isPresent;
    }

    public String getAttendanceDateTime() {
        return attendanceDateTime;
    }
    public String getAttendanceVenue() {
        return attendanceVenue;
    }
    public boolean getIsPresent() {
        return isPresent;
    }
}
