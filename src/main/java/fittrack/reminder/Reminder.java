package fittrack.reminder;

import fittrack.storage.Saveable;
import fittrack.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reminder extends Saveable {

    private final LocalDateTime reminderDeadline;
    private final String reminderDescription;
    private User User;

    public Reminder(String description, LocalDateTime deadline, User user) {
        this.reminderDescription = description;
        this.reminderDeadline = deadline;
        this.User = user;

    }


    public void printReminderDescription() {
        System.out.print(this.reminderDescription + " | " +
                this.reminderDeadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + System.lineSeparator());
    }

    // Saves in the format [ Reminder | Description | Deadline | User ]
    @Override
    public String toSaveString() {
        return "Reminder" + " | " + reminderDescription + " | " +
                reminderDeadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " | " + User.toString();
    }

    public static Reminder fromSaveString(String saveString) {
        // Split the string by the " | " delimiter
        String[] stringData = saveString.split(" \\| ");

        // Check if the format is correct
        if (stringData.length < 3 || !stringData[0].equals("Reminder")) {
            throw new IllegalArgumentException("Invalid save string format for Reminder: " + saveString);
        }

        String reminderDescription = stringData[1];
        LocalDateTime reminderDeadline = LocalDateTime.parse(stringData[2],
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        String[] reminderUserData = stringData[3].split(" ");

        User reminderUser = new User(reminderUserData[0], reminderUserData[1]);

        return new Reminder(reminderDescription, reminderDeadline, reminderUser);
    }

    /**
     * Finds and returns a list of reminders from the main reminder list that occur within the next week.
     *
     * @param mainReminderList The main list of reminders to be checked.
     * @return {@code upcomingReminderList} Containing reminders that occur within the next week from now.
     */
    public static ArrayList<Reminder> findUpcomingReminders(ArrayList<Reminder> mainReminderList) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextWeek = now.plusWeeks(1);
        ArrayList<Reminder> upcomingReminderList = new ArrayList<>();

        for (Reminder reminder : mainReminderList) {
            if (reminder.reminderDeadline.isAfter(now) && reminder.reminderDeadline.isBefore(nextWeek)) {
                upcomingReminderList.add(reminder);
            }
        }
        return upcomingReminderList;
    }
}
