package fittrack.reminder;

import fittrack.user.User;

import java.time.LocalDateTime;

// Reminder functionality implementation task list:
// Implement reminder class
// (TODO) Implement reminder-related command parsing (in Parser class)
// (TODO) Implement automatic user reminder functionality
//
// (TODO) Implement reminder list functionality
// (TODO) Implement reminder delete functionality
// (TODO) Implement reminder storage read/write functionality
// (TODO) Implement functionality to edit reminder frequency / thresholds

//
// Add UI messages for reminder add/delete/list
// Add User Guide entries for reminder add/delete/list
//
// Rename ambiguous session-add message name

public class Reminder {

    private LocalDateTime sessionDatetime;
    private String reminderDescription;
    private User User;

    public Reminder(String description, LocalDateTime deadline, User) {
        this.reminderDescription = description;
        this.sessionDatetime = deadline;

    }

}
