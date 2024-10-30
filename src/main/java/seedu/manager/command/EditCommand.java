package seedu.manager.command;

/**
 * Represents a command to edit an event to the event list.
 * The add command will store the event's name, time, and venue.
 */
public class EditCommand extends Command{
    public static final String COMMAND_WORD = "edit";
    private static final String EDIT_PARTICIPANT_MESSAGE = "Participant email successfully updated";
    private static final String EDIT_FAILURE_MESSAGE = "Event/Participant not found";
    protected String eventName;
    protected String participantName;
    protected String participantNumber;
    protected String participantEmail;


    //@@author KuanHsienn
    /**
     * Constructs an EditCommand object with the specified participant name, participant number,
     * participant email, and event name.
     *
     * @param participantName The name of the participant to be updated.
     * @param participantNumber The new phone number of the participant.
     * @param participantEmail The new email address of the participant.
     * @param eventName The name of the event associated with the participant.
     */
    public EditCommand(String participantName, String participantNumber, String participantEmail, String eventName) {
        super(false);
        this.participantName = participantName;
        this.participantNumber = participantNumber;
        this.participantEmail = participantEmail;
        this.eventName = eventName;
    }

    @Override
    public void execute() {
        if (participantName == null) {
            return;
        } else {
            boolean isEdited = this.eventList.editParticipant(
                    this.participantName,
                    this.participantNumber,
                    this.participantEmail,
                    this.eventName
            );
            this.message = (isEdited) ? EDIT_PARTICIPANT_MESSAGE : EDIT_FAILURE_MESSAGE;
        }
    }
}
