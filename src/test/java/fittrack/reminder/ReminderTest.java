package fittrack.reminder;


import fittrack.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ReminderTest {

    private Reminder reminder;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("MALE","24");
        LocalDateTime deadline = LocalDateTime.of(2024, 11, 20, 15, 30);
        reminder = new Reminder("Doctor's appointment", deadline, user);
    }

    @Test
    void testGetDescription() {
        assertEquals("Doctor's appointment", reminder.getDescription());
    }

    @Test
    void testGetDeadlineString() {
        assertEquals("20/11/2024 15:30", reminder.getDeadlineString());
    }

    @Test
    void testToSaveString() {
        String expected = "Reminder|Doctor's appointment|20/11/2024 15:30|MALE 24";
        assertEquals(expected, reminder.toSaveString());
    }

    @Test
    void testFromSaveString() {
        String saveString = "Reminder|Doctor's appointment|20/11/2024 15:30|MALE 24";
        Reminder loadedReminder = Reminder.fromSaveString(saveString);

        assertEquals("Doctor's appointment", loadedReminder.getDescription());
        assertEquals("20/11/2024 15:30", loadedReminder.getDeadlineString());
    }

    @Test
    void testFromSaveStringInvalidFormat() {
        String invalidSaveString = "Reminder | Missing deadline | ";
        assertThrows(IllegalArgumentException.class, () -> {
            Reminder.fromSaveString(invalidSaveString);
        });
    }

    @Test
    void testFindUpcomingReminders() {
        ArrayList<Reminder> reminders = new ArrayList<>();
        reminders.add(reminder); // Reminder for 20/11/2024, assumed within a week from now

        LocalDateTime upcomingDeadline = LocalDateTime.now().plusDays(5);
        Reminder upcomingReminder = new Reminder("Dentist appointment", upcomingDeadline, user);
        reminders.add(upcomingReminder);

        ArrayList<Reminder> upcomingReminders = Reminder.findUpcomingReminders(reminders);

        assertTrue(upcomingReminders.contains(upcomingReminder));
        assertFalse(upcomingReminders.contains(reminder)); // Assumes reminder is not within a week
    }

    @Test
    void testFindUpcomingRemindersNoUpcoming() {
        ArrayList<Reminder> reminders = new ArrayList<>();
        reminders.add(reminder); // Not within a week

        ArrayList<Reminder> upcomingReminders = Reminder.findUpcomingReminders(reminders);
        assertTrue(upcomingReminders.isEmpty());
    }
}
