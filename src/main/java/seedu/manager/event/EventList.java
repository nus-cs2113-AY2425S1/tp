package seedu.manager.event;

import seedu.manager.enumeration.Priority;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

/**
 * The EventList class manages a list of Event objects.
 * It provides methods to manage an event list.
 */
public class EventList  {
    private final ArrayList<Event> eventList;

    /**
     * Default constructor that initializes an empty event list.
     */
    public EventList(){
        eventList = new ArrayList<>();
    }

    /**
     * Constructor that initializes EventList with a given list of event.
     *
     * @param otherEventList The initial list of tasks.
     */
    public EventList(EventList otherEventList) {
        this.eventList = new ArrayList<>(otherEventList.eventList);
    }

    /**
     * @return The size of the event list.
     */
    public int getListSize() {
        return eventList.size();
    }

    /**
     * @return The event list
     */
    public ArrayList<Event> getList() {
        return this.eventList;
    }

    /**
     * @param index The index of event in the list (0 based indexing)
     * @return The specific event in the event list.
     */
    public Event getEvent(int index) {
        return eventList.get(index);
    }

    /**
     * Adds an event to the event list.
     *
     * @param event the event to be added to the list
     */
    public void addEvent(Event event) {
        eventList.add(event);
    }

    /**
     * Adds a new event to the event list.
     *
     * <p>
     * This method creates a new {@link Event} object with the specified event name,
     * time, and venue, and adds it to the event list.
     * </p>
     *
     * @param eventName the name of the event to be added.
     * @param time      the time of the event.
     * @param venue     the venue where the event will take place.
     * @param priority the priority level of the event
     * @return the name of the updated event.
     */
    public String addEvent(String eventName, LocalDateTime time,
            String venue, Priority priority) {
        return addEvent(eventName, time, venue, priority, false);
    }

    /**
     * Adds a new {@code Event} with the given parameters and adds it to the event list.
     *
     * @param eventName the name of the event to be added.
     * @param time the time of the event.
     * @param venue the venue where the event will take place.
     * @param priority the priority level of the event.
     * @param isDone {@code true} if the event is marked done, {@code false otherwise}.
     * @return the name of the added event.
     */
    public String addEvent(String eventName, LocalDateTime time, String venue,
            Priority priority, boolean isDone) {
        String name = getDuplicateEventName(eventName);
        Event newEvent = new Event(name, time, venue, priority, isDone);
        eventList.add(newEvent);
        return name;
    }

    //@@author LTK-1606
    /**
     * Returns true if a participant can be added to a specified event,
     * returns false otherwise.
     *
     * @param name the name of the participant to be added.
     * @param email the email address of the participant
     * @param eventName the name of the event to which the participant will be added.
     * @return the updated participant name if the participant can be added to the event,
     *         an empty string otherwise.
     */
    public String addParticipantToEvent(String name, String email, String eventName) {
        return addParticipantToEvent(name, email, false, eventName);
    }

    /**
     * Returns true if a participant can be added to a specified event,
     * returns false otherwise.
     *
     * @param name the name of the participant to be added.
     * @param email the email address of the participant.
     * @param isPresent {@code true} if the participant is to be marked present, {@code false} otherwise.
     * @param eventName the name of the event to which the participant will be added.
     * @return the updated participant name if the participant can be added to the event,
     *         an empty string otherwise.
     */
    public String addParticipantToEvent(String name, String email, boolean isPresent,
            String eventName) {
        for (Event event : eventList) {
            if (event.getEventName().equals(eventName)) {
                return event.addParticipant(name, email, isPresent);
            }
        }
        return "";
    }

    //@@author jemehgoh
    /**
     * Returns the added name of an item with a given name if it is successfully added to a given event.
     * Otherwise, returns false.
     *
     * @param itemName the name of the item to be added.
     * @param eventName the name of the event the item is to be added to.
     * @return the added item name if the item is successfully added to the event, an empty string otherwise.
     */
    public String addItemToEvent(String itemName, String eventName) {
        return addItemToEvent(itemName, false, eventName);
    }

    /**
     * Returns the added name of an item with a given name if it is successfully added to a given event.
     * Otherwise, returns false.
     *
     * @param itemName the name of the item to be added.
     * @param eventName the name of the event the item is to be added to.
     * @return the added item name if the item is successfully added to the event, an empty string otherwise.
     */
    public String addItemToEvent(String itemName, boolean isPresent, String eventName) {
        assert itemName != null : "Item name should not be null";
        Optional<Event> event = getEventByName(eventName);
        if (event.isPresent()) {
            return event.get().addItem(itemName, isPresent);
        }
        return "";
    }

    //@@author jemehgoh
    /**
     * Returns an event in the event list with a specified name.
     * Returns null if the event is not found.
     *
     * @param eventName The specified name
     * @return the event with a specified name, or null if the event is not found
     */
    public Optional<Event> getEventByName(String eventName) {
        for (Event event : eventList) {
            if (event.getEventName().equals(eventName)) {
                return Optional.of(event);
            }
        }
        return Optional.empty();
    }

    //@@author KuanHsienn
    /**
     * Removes an event from the event list by its name.
     *
     * @param eventName the name of the event to be removed.
     * @return {@code true} if the event was successfully removed;
     *         {@code false} if no event with the specified name was found.
     */
    public boolean removeEvent(String eventName) {
        for (Event event : eventList) {
            if (event.getEventName().equals(eventName)) {
                eventList.remove(event);
                return true;
            }
        }
        return false;
    }

    //@@author LTK-1606
    /**
     * Removes a participant from a specified event.
     *
     * <p>
     * This method searches for the event with the given name in the event list and
     * attempts to remove the specified participant from that event. If the event is
     * found and the participant is successfully removed, it returns {@code true}.
     * If the event does not exist or the participant is not found, it returns
     * {@code false}.
     * </p>
     *
     * @param participantName the name of the participant to be removed from the event.
     * @param eventName      the name of the event from which the participant will be removed.
     * @return {@code true} if the participant was successfully removed;
     *         {@code false} if the event does not exist or the participant was not found.
     */
    public boolean removeParticipantFromEvent(String participantName, String eventName) {
        for (Event event : eventList) {
            if (event.getEventName().equals(eventName)) {
                return event.removeParticipant(participantName);
            }
        }
        return false;
    }

    //@@author jemehgoh
    /**
     * Returns true if an item with a given name is successfully removed from an event, returns false otherwise.
     *
     * @param itemName the name of the item to be removed.
     * @param eventName the name of the event the item is to be removed from.
     * @return {@code true} if the item is successfully removed from the vent, {@code false} otherwise.
     */
    public boolean removeItemFromEvent(String itemName, String eventName) {
        assert itemName != null : "Item name should not be null";
        Optional<Event> event = getEventByName(eventName);
        if (event.isPresent()) {
            return event.get().removeItem(itemName);
        }
        return false;
    }

    //@@author MatchaRRR
    /**
     * Edits the details of an event in a specified event.
     *
     * <p>
     * This method searches for the event with the given name in the event list and update the event's information.
     * If the event is found and the participant is successfully updated, it returns {@code true}.
     * If the event does not exist, it returns {@code false}.
     * </p>
     *
     * @param eventName The name of the event to be edited.
     * @param eventNewName The new name of the event.
     * @param eventTime The new time of the event.
     * @param eventVenue The new venue of the event.
     * @param eventPriority The new priority of the event.
     * @return the updated name of the event if the event details were updated. Otherwise, returns an empty string.
     */
    public String editEvent(String eventName, String eventNewName, LocalDateTime eventTime, String eventVenue,
            Priority eventPriority) {
        for (Event event : eventList) {
            if (event.getEventName().equals(eventName)) {
                String name = getUpdatedEventName(eventNewName, event);
                event.updateEvent(name, eventTime, eventVenue, eventPriority);
                return name;
            }
        }
        return "";
    }

    //@@author KuanHsienn
    /**
     * Edits the details of a participant in a specified event.
     *
     * <p>
     * This method searches for the event with the given name in the event list and
     * attempts to update the specified participant's email. If the event
     * is found and the participant is successfully updated, it returns {@code true}.
     * If the event does not exist or the participant is not found, it returns
     * {@code false}.
     * </p>
     *
     * @param currentName the name of the participant to be edited.
     * @param newName        the new name of the participant.
     * @param email          the new email address of the participant.
     * @param eventName      the name of the event associated with the participant.
     * @return the updated participant name if the participant was successfully edited;
     *         an empty string if the event does not exist or the participant was not found.
     */
    public String editParticipant(String currentName, String newName, String email, String eventName) {
        for (Event event : eventList) {
            if (event.getEventName().equals(eventName)) {
                return event.updateParticipant(currentName, newName, email);
            }
        }
        return "";
    }

    //@@author MatchaRRR
    /**
     * Edits the details of an event in a specified event.
     *
     * <p>
     * This method searches for the event with the given name in the event list and update the event's information.
     * If the event is found and the participant is successfully updated, it returns {@code true}.
     * If the event does not exist, it returns {@code false}.
     * </p>
     *
     * @param itemName the name of original item.
     * @param itemNewName the name of the new item.
     * @param eventName the name of the event the item is in.
     * @return the updated name of the item if the item was successfully edited;
     *         an empty string if the item or event specified does not exist.
     */
    public String editItem(String itemName, String itemNewName, String eventName) {
        for (Event event : eventList) {
            if (event.getEventName().equals(eventName)) {
                return event.updateItem(itemName, itemNewName);
            }
        }
        return "";
    }

    //@@author LTK-1606
    /**
     * Sort the event list by name, alphabetically.
     */
    public void sortByName(){
        eventList.sort(Comparator.comparing(event -> event.getEventName().toLowerCase()));
    }

    /**
     *  Sort the event list by time in chronological order.
     */
    public void sortByTime() {
        eventList.sort(Comparator.comparing(Event::getEventTime));
    }

    /**
     *  Sort the event list by priority level from highest to lowest priority.
     */
    public void sortByPriority() {
        eventList.sort(Comparator.comparing(Event::getEventPriority));
    }
  
    /**
     * Filters events in the event list by the specified priority level.
     *
     * @param priority the priority level to filter events by
     * @return an {@code EventList} containing only events with the specified priority
     */
    public EventList filterByPriority(Priority priority) {
        EventList filteredList = new EventList();

        for (Event event : eventList) {
            if (event.getEventPriority() == priority) {
                filteredList.addEvent(event);
            }
        }

        return filteredList;
    }

    //@@author glenn-chew
    /**
     *  Filters events in the event list to display only events with names containing the keyword.
     *
     * @param keyword the keyword that the event is filtered by.
     * @return an {@code EventList} containing only events that the keyword can be found in the event name.
     */
    public EventList filterByName(String keyword) {
        EventList filteredList = new EventList();

        for (Event event : eventList) {
            if (event.getEventName().toLowerCase().contains(keyword)) {
                filteredList.addEvent(event);
            }
        }

        return filteredList;
    }

    //@@author glenn-chew
    /**
     *  Filters events in the event list to display only events that of the specified date.
     *
     * @param eventDate the date that the event is filtered by.
     * @return an {@code EventList} containing only events with of the specified date.
     */
    public EventList filterByDate(LocalDate eventDate) {
        EventList filteredList = new EventList();

        for (Event event : eventList) {
            if (event.getEventTime().toLocalDate().equals(eventDate)) {
                filteredList.addEvent(event);
            }
        }

        return filteredList;
    }

    //@@author glenn-chew
    /**
     *  Filters events in the event list to display only events that of the specified time.
     *
     * @param eventTime the date that the event is filtered by.
     * @return an {@code EventList} containing only events with of the specified time.
     */
    public EventList filterByTime(LocalTime eventTime) {
        EventList filteredList = new EventList();

        for (Event event : eventList) {
            if (event.getEventTime().toLocalTime().equals(eventTime)) {
                filteredList.addEvent(event);
            }
        }

        return filteredList;
    }

    //@@author glenn-chew
    /**
     *  Filters events in the event list to display only events that of the specified date-time.
     *
     * @param eventDateTime the date that the event is filtered by.
     * @return an {@code EventList} containing only events with of the specified date-time.
     */
    public EventList filterByDateTime(LocalDateTime eventDateTime) {
        EventList filteredList = new EventList();

        for (Event event : eventList) {
            if (event.getEventTime().equals(eventDateTime)) {
                filteredList.addEvent(event);
            }
        }

        return filteredList;
    }

    //author jemehgoh
    /**
     * Returns an event's name with an indexed suffix.
     * The suffix value varies based on the number of event with the same name in the event list.
     *
     * @param name the given event name.
     * @return name with an indexed suffix.
     */
    private String getDuplicateEventName(String name) {
        int index = 1;
        String duplicateName = name;

        while (getEventByName(duplicateName).isPresent()) {
            duplicateName = String.format("%s(%d)", name, index);
            index++;
        }

        return duplicateName;
    }

    /**
     * Returns an updated event name for editing event details.
     *
     * @param name the given new event name.
     * @param event the event to be edited.
     * @return the updated version of name.
     */
    private String getUpdatedEventName(String name, Event event) {
        int index = 1;
        String updatedName = name;

        while (getEventByName(updatedName).isPresent() && !updatedName.equals(event.getEventName())) {
            updatedName = String.format("%s(%d)", name, index);
            index++;
        }

        return updatedName;
    }
}
