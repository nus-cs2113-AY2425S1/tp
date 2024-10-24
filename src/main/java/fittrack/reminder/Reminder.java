package fittrack.reminder;

import fittrack.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reminder {

    private final LocalDateTime reminderDeadline;
    private final String reminderDescription;
    private final User User;

    public Reminder(String description, LocalDateTime deadline, User user) {
        this.reminderDescription = description;
        this.reminderDeadline = deadline;
        this.User = user;

    }

    public void printReminderDescription() {
        System.out.print(this.reminderDescription + " | " +
                this.reminderDeadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + System.lineSeparator());
    }
}
