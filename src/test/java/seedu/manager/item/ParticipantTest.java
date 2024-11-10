package seedu.manager.item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParticipantTest {

    @Test
    public void setPresent_setTrue_present() {
        Participant participant = new Participant("Participant","example@gmail.com",
                false);
        participant.setPresent(true);
        assertEquals("X", participant.markIfPresent());
    }

    @Test
    public void setPresent_setFalse_notPresent() {
        Participant participant = new Participant("Participant",  "example@gmail.com",
                false);
        participant.setPresent(false);
        assertEquals(" ", participant.markIfPresent());
    }

    @Test
    public void toString_setTrue_present() {
        Participant participant = new Participant("Participant", "example@gmail.com",
                false);
        participant.setPresent(true);
        assertEquals("Name: Participant / Email: example@gmail.com [X]",
                participant.toString());
    }
}
