package seedu.manager.command;

/**
 * Represents a command to edit an event to the event list.
 * The edit command will store the event's name, the participant's information.
 */
public class EditParticipantCommand extends Command{
    public static final String COMMAND_WORD = "edit";
    private static final String EDIT_PARTICIPANT_MESSAGE = "The following participant's details has been updated:\n";
    private static final String EDIT_FAILURE_MESSAGE = "Event/Participant not found!";
    protected String eventName;
    protected String participantName;
    protected String newParticipantName;
    protected String participantEmail;


    //@@author KuanHsienn
    /**
     * Constructs an EditParticipantCommand object with the specified participant name, participant number,
     * participant email, and event name.
     *
     * @param participantName The name of the participant.
     * @param newParticipantName The new name of the participant.
     * @param participantEmail The new email address of the participant.
     * @param eventName The name of the event associated with the participant.
     */
    public EditParticipantCommand(String participantName, String newParticipantName, String participantEmail,
            String eventName) {
        super(false);
        this.participantName = participantName;
        this.newParticipantName = newParticipantName;
        this.participantEmail = participantEmail;
        this.eventName = eventName;
    }

    /**
     * Executes the edit operation, updating the participant's contact information if the
     * participant is associated with the specified event. If successful, a confirmation message
     * is set; otherwise, an error message is set.
     */
    @Override
    public void execute() {
        assert participantName != null: "Participant name cannot be null.";
        String updatedName = eventList.editParticipant(participantName, newParticipantName, participantEmail,
                eventName);
        if (updatedName.equalsIgnoreCase("")) {
            message = EDIT_FAILURE_MESSAGE;
        } else {
            message = getOutputMessage(updatedName, participantEmail);
        }
    }

    /**
     * Returns a success output message with the given participant details.
     *
     * @param name the updated name of the participant.
     * @param email the updated email of the participant.
     * @return an output message with name, email, and the event name.
     */
    private String getOutputMessage(String name, String email) {
        StringBuilder outputMessage = new StringBuilder();
        outputMessage.append(EDIT_PARTICIPANT_MESSAGE);
        String formattedString = String.format("Participant name: %s / Participant email: %s / Event name: %s",
                name, email, eventName);
        outputMessage.append(formattedString);
        return outputMessage.toString();
    }
}
