package seedu.manager.event;

public class Event {
    private final String eventName;
    private String eventTime;
    private String eventVenue;
    public Event(String eventName, String eventTime, String eventVenue) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventVenue = eventVenue;
    }
    public String getEventName() {
        return eventName;
    }
    public String getEventTime() {
        return eventTime;
    }
    public String getEventVenue() {
        return eventVenue;
    }
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }
}
