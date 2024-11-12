package seedu.manager.event;

import seedu.manager.enumeration.Priority;
import seedu.manager.item.Item;
import seedu.manager.item.Participant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

//@@author MatchaRRR
/**
 * The Event class represents an event with a name, time, and venue.
 * It provides methods to access and modify the time and venue of the event.
 */
public class Event {
    protected ArrayList<Participant> participantList;
    private ArrayList<Item> itemList;
    private String eventName;
    private LocalDateTime eventTime;
    private String eventVenue;
    private boolean isDone;
    private Priority eventPriority;

    //@@author LTK-1606
    /**
     * Constructs an Event with the specified name, time, venue and priority.
     *
     * @param eventName  the name of the event
     * @param eventTime  the time duration of the event
     * @param eventVenue the venue of the event
     * @param eventPriority the priority level of the event
     */
    public Event(String eventName, LocalDateTime eventTime, String eventVenue, Priority eventPriority) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventVenue = eventVenue;
        this.eventPriority = eventPriority;
        this.participantList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.isDone = false;
    }

    /**
     * Constructs an Event with the specified name, time, venue, priority and whether it is marked done.
     *
     * @param eventName  the name of the event.
     * @param eventTime  the time duration of the event.
     * @param eventVenue the venue of the event.
     * @param eventPriority the priority level of the event.
     * @param isDone {@code true} if the event is marked done, {@code false otherwise}.
     */
    public Event(String eventName, LocalDateTime eventTime, String eventVenue, Priority eventPriority,
                 boolean isDone) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventVenue = eventVenue;
        this.eventPriority = eventPriority;
        this.participantList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.isDone = isDone;
    }

    /**
     * Retrieves the number of participants in the participant list.
     *
     * @return the count of participants currently in the list.
     */
    public int getParticipantCount() {
        return this.participantList.size();
    }

    /**
     * Returns the number of items in the event's item list.
     *
     * @return the number of items in the event's item list.
     */
    public int getItemCount() {
        return itemList.size();
    }

    //@@author MatchaRRR
    /**
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @return the event time
     */
    public LocalDateTime getEventTime() {
        return eventTime;
    }

    /**
     * @return the event venue
     */
    public String getEventVenue() {
        return eventVenue;
    }

    //@@author LTK-1606
    /**
     * @return the event's participant list
     */
    public ArrayList<Participant> getParticipantList() {
        return this.participantList;
    }

    //@@author jemehgoh
    /**
     * @return the item list of the event.
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    //@@author LTK-1606
    /**
     * @return the event priority
     */
    public Priority getEventPriority() {
        return eventPriority;
    }

    /**
     * @return a String of the event priority.
     */
    public String getEventPriorityString() {
        return String.format("%s", eventPriority);
    }

    /**
     * Sets a participant for the event.
     *
     * @param participantList the new participant list
     */
    public void setParticipantList(ArrayList<Participant> participantList) {
        this.participantList = participantList;
    }

    //@@author jemehgoh
    /**
     * Sets if the event is done or not done
     *
     * @param isDone if the event is done
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * @return 'Y' if event is marked done, 'N' otherwise
     */
    public String markIfDone() {
        return (this.isDone) ? "Y" : "N";
    }

    /**
     * @return true if the event is marked done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    //@@author LTK-1606
    /**
     * Adds a participant to the participant list for the event.
     *
     * @param participantName the name of the participant to be added to the list.
     * @param isPresent {@code true} if the participant is to be present, {@code false} otherwise.
     * @return the name of the added participant.
     */
    public String addParticipant(String participantName, String participantEmail,
            boolean isPresent) {
        String name = getDuplicateParticipantName(participantName);
        Participant participant = new Participant(name, participantEmail, isPresent);
        this.participantList.add(participant);
        return name;
    }

    //@@author jemehgoh
    /**
     * Returns the name of an item added to the event's item list, with a given name.
     *
     * @param itemName the name of the item to be added.
     * @return the name of the item added.
     */
    public String addItem(String itemName, boolean isPresent) {
        String name = getDuplicateItemName(itemName);
        Item item = new Item(name, isPresent);
        itemList.add(item);
        return name;
    }

    //@@author LTK-1606
    /**
     * Removes a participant from the participant list.
     *
     * <p>
     * This method attempts to remove the specified participant from the list of
     * participants associated with the event. It returns {@code true} if the
     * participant was successfully removed, and {@code false} if the participant
     * was not found in the list.
     * </p>
     *
     * @param participantName the name of the participant to be removed from the list.
     * @return {@code true} if the participant was successfully removed;
     *         {@code false} if the participant was not found in the list.
     */
    public boolean removeParticipant(String participantName) {
        return this.participantList.removeIf((participant) ->
                (participant.getName().equalsIgnoreCase(participantName)));
    }

    //@@author jemehgoh
    /**
     * Returns true if an item with the given name is successfully removed from the item list, returns false
     *         otherwise.
     *
     * @param itemName the name of the item to be removed.
     * @return {@code true} if an item with itemName is successfully removed, {@code false} otherwise.
     */
    public boolean removeItem(String itemName) {
        return itemList.removeIf((item) -> (item.getName().equalsIgnoreCase(itemName)));
    }

    //@@author MatchaRRR
    /**
     * Updates the details of an event.
     *
     * @param eventNewName The new name of the event.
     * @param eventTime The new time of the event.
     * @param eventVenue The new venue of the event.
     * @param eventPriority The new priority of the event.
     */
    public void updateEvent(String eventNewName, LocalDateTime eventTime, String eventVenue, Priority eventPriority) {
        this.eventName = eventNewName;
        this.eventTime = eventTime;
        this.eventVenue = eventVenue;
        this.eventPriority = eventPriority;
    }

    //@@author KuanHsienn
    /**
     * Updates the details of a participant in this event.
     *
     * @param participantName the name of the participant to be updated.
     * @param newEmail       the new email address of the participant.
     * @return the updated participant name if the details were updated. Otherwise, returns an empty string.
     */
    public String updateParticipant(String participantName, String newName, String newEmail) {
        for (Participant participant : this.participantList) {
            if (participant.getName().equalsIgnoreCase(participantName)) {
                String nameToSet = getUpdatedParticipantName(newName, participant);
                participant.setName(nameToSet);
                participant.setEmail(newEmail);
                return nameToSet;
            }
        }
        return "";
    }

    //@@author MatchaRRR
    /**
     * Updates the details of an item.
     *
     * @param itemName The name of original item.
     * @param itemNewName The name of the new item.
     * @return the updated name of the item if the details were updated; an empty string otherwise.
     */
    public String updateItem(String itemName, String itemNewName) {
        for (Item item : this.itemList) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                String updatedName = getUpdatedItemName(itemNewName, item);
                item.setName(updatedName);
                item.setPresent(false);
                return updatedName;
            }
        }
        return "";
    }

    //@@author jemehgoh
    /**
     * Returns true if the participant with the given name can be marked present or absent.
     *         Returns false otherwise.
     *
     * @param participantName the participant name.
     * @param isPresent true if participant is to be marked present, false if he is to be marked absent.
     * @return {@code true} if the participant with participantName has been marked present or absent,
     *         {@code false} otherwise.
     */
    public boolean markParticipantByName(String participantName, boolean isPresent) {
        Optional<Participant> participant = getParticipantByName(participantName);
        return markParticipant(participant, isPresent);
    }

    /**
     * Returns the participant in the participant list with the given name.
     * If the participant is not in the participant list, returns null.
     *
     * @param participantName the name of the participant.
     * @return the participant in the participant list with participantName, or null if
     *     no such participant exists.
     */
    private Optional<Participant> getParticipantByName(String participantName) {
        for (Participant participant : participantList) {
            if (participant.getName().equalsIgnoreCase(participantName)) {
                return Optional.of(participant);
            }
        }

        return Optional.empty();
    }

    /**
     * Returns true if the given participant can be marked present or absent. Returns false otherwise.
     *
     * @param participant the participant.
     * @param isPresent true if participant is to be marked present, false if he is to be marked absent.
     * @return {@code true} if the participant with participantName has been marked present or absent,
     *     {@code false} otherwise.
     */
    private boolean markParticipant(Optional<Participant> participant, boolean isPresent) {
        if (participant.isEmpty()) {
            return false;
        }

        participant.get().setPresent(isPresent);
        return true;
    }

    /**
     * Returns true if the item with the given name can be marked present or absent.
     *         Returns false otherwise.
     *
     * @param itemName the name of the item.
     * @param isPresent true if the item is to be marked present, false if it is to be marked absent.
     * @return {@code true} if the item with itemName has been marked present or absent,
     *         {@code false} otherwise.
     */
    public boolean markItemByName(String itemName, boolean isPresent) {
        Optional<Item> item = getItemByName(itemName);
        return markItem(item, isPresent);
    }

    /**
     * Returns the {@code Item} with the given name in the item list.
     *
     * @param itemName the given item name
     * @return the {@code Item} with name itemName, or null if the item is not found.
     */
    private Optional<Item> getItemByName(String itemName) {
        for (Item item : itemList) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    /**
     * Returns true if an item has been marked present or absent, returns false otherwise.
     *
     * @param item the name of the item to be marked.
     * @param isPresent true if the item is to be marked present, false otherwise.
     * @return true if the item has been successfully marked, false otherwise.
     */
    private boolean markItem(Optional<Item> item, boolean isPresent) {
        if (item.isEmpty()) {
            return false;
        }

        item.get().setPresent(isPresent);
        return true;
    }

    //@@author LTK-1606
    /**
     * Finds participants in the event whose names contain the specified person name.
     * <p>
     * This method iterates through the list of participants and checks if their names
     * contain the given {@code personName}, ignoring case and leading/trailing spaces.
     * If a match is found, the participant is added to the result list.
     * </p>
     *
     * @param personName the name or part of the name of the participant to search for
     * @return a list of {@code Participant} objects whose names contain the specified {@code personName}
     */
    public ArrayList<Participant> findParticipants(String personName) {
        ArrayList<Participant> participants = new ArrayList<>();
        for (Participant participant : this.participantList) {
            if (participant.getName().toLowerCase().contains(personName.trim().toLowerCase())) {
                participants.add(participant);
            }
        }
        return participants;
    }

    //@@author glenn-chew
    /**
     * Formats eventTime to a string in "yyyy-MM-dd HH:mm" format
     *
     * @return eventTime as a formated {@link String} object.
     */
    public String getEventTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return formatter.format(eventTime);
    }

    //@@author MatchaRRR
    /**
     * Returns a string representation of the event, indicating its name, time and venue.
     *
     * @return A string that shows the event's name, time and venue.
     */
    @Override
    public String toString(){
        String eventTimeString = getEventTimeString();
        return String.format("Event name: %s / Event time: %s / Event venue: %s / Event Priority: %s / Done: %s",
                eventName, eventTimeString, eventVenue, eventPriority, markIfDone());
    }

    //@@author jemehgoh
    /**
     * Returns a participant's name with an indexed suffix.
     * The suffix value varies based on the number of participants with the same name.
     *
     * @param name the given participant name.
     * @return name with an indexed suffix.
     */
    private String getDuplicateParticipantName(String name) {
        int index = 1;
        String duplicateName = name;

        while (getParticipantByName(duplicateName).isPresent()) {
            duplicateName = String.format("%s(%d)", name, index);
            index++;
        }

        return duplicateName;
    }

    /**
     * Returns an item's name with an indexed suffix.
     * The suffix value varies based on the number of items with the same name.
     *
     * @param name the given item name.
     * @return name with an indexed suffix.
     */
    private String getDuplicateItemName(String name) {
        int index = 1;
        String duplicateName = name;

        while (getItemByName(duplicateName).isPresent()) {
            duplicateName = String.format("%s(%d)", name, index);
            index++;
        }

        return duplicateName;
    }

    /**
     * Returns an updated participant name for editing participant details.
     *
     * @param name the given new participant name.
     * @param participant the participant to be edited.
     * @return the updated version of name.
     */
    private String getUpdatedParticipantName(String name, Participant participant) {
        int index = 1;
        String updatedName = name;

        while (getParticipantByName(updatedName).isPresent() &&
                !updatedName.equalsIgnoreCase(participant.getName())) {
            updatedName = String.format("%s(%d)", name, index);
            index++;
        }

        return updatedName;
    }

    /**
     * Returns an updated item name for editing item details.
     *
     * @param name the given new item name.
     * @param item the item to be edited.
     * @return the updated version of name.
     */
    private String getUpdatedItemName(String name, Item item) {
        int index = 1;
        String updatedName = name;

        while (getItemByName(updatedName).isPresent() && !updatedName.equalsIgnoreCase(item.getName())) {
            updatedName = String.format("%s(%d)", name, index);
            index++;
        }

        return updatedName;
    }
}
