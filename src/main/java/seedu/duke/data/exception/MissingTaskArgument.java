package seedu.duke.data.exception;

public class MissingTaskArgument extends Exception {
    public MissingTaskArgument(String type) {
        super(createErrorMessage(type));
    }

    public static String createErrorMessage(String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("Missing arguments for task type: ").append(type).append("\n");
        sb.append("The required arguments for task type ").append(type).append(" are: [");
        switch (type.toLowerCase()) {
        case "todo":
            sb.append("description");
            break;
        case "deadline":
            sb.append("description, due date");
            break;
        case "repeat":
            sb.append("description, repeat interval");
            break;
        default:
            return "Unknown task type: " + type;
        }
        sb.append("]");
        return sb.toString();
    }
}
