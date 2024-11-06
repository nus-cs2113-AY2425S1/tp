package seedu.duke;

public class InputParser {
    private String parseComponent(String input, String prefix) {
        String[] parts = input.split(" ");
        StringBuilder componentBuilder = new StringBuilder();
        boolean isComponentStarted = false;

        for (String part : parts) {
            if (part.startsWith(prefix)) {
                isComponentStarted = true;
                componentBuilder.append(part.substring(prefix.length())).append(" ");
            } else if (isComponentStarted && !part.contains("/")) {
                componentBuilder.append(part).append(" ");
            } else if (isComponentStarted) {
                break;
            }
        }
        return componentBuilder.toString().trim();
    }
}
