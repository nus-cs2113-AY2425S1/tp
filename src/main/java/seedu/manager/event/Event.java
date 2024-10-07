package seedu.manager.event;

public abstract class Event {
    private final String eventName;
    private String startTime;
    private String endTime;
    private String venue;
    private String description;
    public Event(String eventName, String startTime, String endTime, String venue) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
    }
    public String getEventName() {
        return eventName;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getVenue() {
        return venue;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
}
