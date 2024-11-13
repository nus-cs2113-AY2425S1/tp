package seedu.duke.data.task;

public class Todo extends Task {
    public Todo() {
        this("");
    }

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo(String description, String tag) {
        super(description, tag);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
}
