package seedu.manager.command;

import seedu.manager.event.Event;
import seedu.manager.item.Participant;

import java.util.ArrayList;
import java.util.Optional;

public class FindCommand extends Command{
    private static final String FIND_SUCCESS_MESSAGE = "Person(s) found!";
    private static final String FIND_EVENT_FAILURE_MESSAGE = "Event not found!";
    private static final String FIND_FAILURE_MESSAGE = "Person not found!";

    protected String personName;
    protected String eventName;
    protected Optional<Event> event;

    public FindCommand(String eventName, String personName) {
        super(false);
        this.eventName = eventName;
        this.personName = personName;
    }

    @Override
    public void execute() {
        StringBuilder outputMessage = new StringBuilder();
        ArrayList<Participant> participantsFound;

        event = eventList.getEventByName(this.eventName);
        if (event.isPresent()) {
            participantsFound = event.get().findParticipants(personName);

            if (!participantsFound.isEmpty()) {
                outputMessage.append(FIND_SUCCESS_MESSAGE + "\n");

                // print out the list of people.
                for (int i = 0; i < participantsFound.size(); i++) {
                    outputMessage.append(String.format("%d. %s\n", i + 1, participantsFound.get(i).toString()));
                }

            } else {
                outputMessage.append(FIND_FAILURE_MESSAGE);
            }

        } else {
            outputMessage.append(FIND_EVENT_FAILURE_MESSAGE);
        }

        this.message = outputMessage.toString();
    }

}
