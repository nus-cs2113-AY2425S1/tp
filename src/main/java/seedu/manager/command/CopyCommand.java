package seedu.manager.command;

import seedu.manager.event.Event;
import seedu.manager.item.Participant;

import java.util.ArrayList;
import java.util.Optional;

//@@author LTK-1606
/**
 * Represents a command to copy the participant list from one event to another.
 */
public class CopyCommand extends Command {
    public static final String COMMAND_WORD = "copy";

    private static final String EVENT_NOT_FOUND = "Event(s) not found!";
    private static final String PARTICIPANT_NOT_FOUND = "Participant list is empty!";
    private static final String COPY_SUCCESSFUL = "Participant list copied over successfully!";

    protected String copyTo;
    protected String copyFrom;

    /**
     * Creates a {@code CopyCommand} object with specified source and destination event names.
     *
     * @param copyFrom the name of the event from which participants will be copied
     * @param copyTo the name of the event to which participants will be copied
     */
    public CopyCommand(String copyFrom, String copyTo) {
        super(false);
        this.copyFrom = copyFrom;
        this.copyTo = copyTo;
    }

    /**
     * Executes the copy command to transfer participants from one event to another.
     * <p>
     * This method retrieves the events specified by {@code copyFrom} and {@code copyTo}.
     * If both events are found, it checks if the source event has participants. If it does,
     * those participants are copied to the destination event. Appropriate messages are generated
     * based on the outcome of the operation. If either event is not found, an error message is returned.
     * </p>
     */
    @Override
    public void execute() {
        StringBuilder outputMessage = new StringBuilder();

        Optional<Event> eventTo = eventList.getEventByName(copyTo);
        Optional<Event> eventFrom = eventList.getEventByName(copyFrom);

        if (eventTo.isPresent() && eventFrom.isPresent()) {
            if (eventFrom.get().getParticipantList().isEmpty()) {
                outputMessage.append(PARTICIPANT_NOT_FOUND);
            } else {
                ArrayList<Participant> participants = eventFrom.get().getParticipantList();
                ArrayList<Participant> copyOfParticipants = copyParticipantList(participants);
                eventTo.get().setParticipantList(copyOfParticipants);
                outputMessage.append(COPY_SUCCESSFUL);
            }
        } else {
            outputMessage.append(EVENT_NOT_FOUND);
        }

        this.message = outputMessage.toString();
    }

    /**
     * Returns a copy of a given list of {@link Participant}s.
     *
     * @param participants the list of Participants.
     * @return a copy of participants.
     */
    private ArrayList<Participant> copyParticipantList(ArrayList<Participant> participants) {
        ArrayList<Participant> copyOfParticipants = new ArrayList<>();
        for (Participant participant : participants) {
            Participant participantCopy = copyParticipant(participant);
            copyOfParticipants.add(participantCopy);
        }

        return copyOfParticipants;
    }

    /**
     * Returns a copy of a given {@link Participant}.
     *
     * @param participant the participant to be copied.
     * @return a copy of participant.
     */
    private Participant copyParticipant(Participant participant) {
        String name = participant.getName();
        String email = participant.getEmail();

        return new Participant(name, email, false);
    }
}
