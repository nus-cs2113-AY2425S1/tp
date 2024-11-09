package seedu.duke.data.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import seedu.duke.data.exception.MissingTaskArgument;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Todo.class, name = "todo"),
    @JsonSubTypes.Type(value = Deadline.class, name = "deadline"),
    @JsonSubTypes.Type(value = Repeat.class, name = "repeat")
})
public class Task {
    private String description;
    private boolean isDone;
    private String tag;

    public Task() {
        this("");
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    //constructor for tag
    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /** Change the name of the getter method to match the JSON key */
    @JsonProperty("isDone")
    public boolean getDone() {
        return isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }
    public static Task createTask(
        String type,
        String... args
    ) throws MissingTaskArgument, EmptyTaskDescription, UnknownTaskType {
        try{
            if(args[0].isEmpty() || args[0].isBlank()) {
                throw new EmptyTaskDescription();
            }
            switch (type.toLowerCase()) {
            case "todo":
                // args[0] is the description, args[1] is the optional tag
                return new Todo(args[0], args.length > 1 ? args[1] : "");
            case "deadline":
                // args[0] is the description, args[1] is the due date, args[2] is the optional tag
                return new Deadline(args[0], args[1], args.length > 2 ? args[2] : "");
            case "repeat":
                // args[0] is description, args[1] is the repeat interval, args[2] is the optional tag
                return new Repeat(args[0], args[1], args.length > 2 ? args[2] : "");
            default:
                throw new UnknownTaskType(type);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            throw new MissingTaskArgument(type);
        }
    }
    public static class UnknownTaskType extends Exception {
        public UnknownTaskType(String type) {
            super("Unknown task type: " + type);
        }
    }
    


    public static class EmptyTaskDescription extends Exception {
        public EmptyTaskDescription() {
            super("Task description cannot be empty");
        }
    }


    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description + (tag != null && !tag.isEmpty() ? " [" + tag + "]" : "");
    }

    /**
     * Returns true if both tasks have the same description.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;
            return description.toLowerCase().equals(other.description.toLowerCase());
        }
        return false;
    }
}
