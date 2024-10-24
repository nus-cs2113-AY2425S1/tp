package fittrack.enums;

public enum Exercise {
    PULL_UP("PU"),
    SHUTTLE_RUN("SR"),
    SIT_AND_REACH("SAR"),
    SIT_UP("SU"),
    STANDING_BROAD_JUMP("SBJ"),
    WALK_AND_RUN("WAR");

    private final String userInput;

    Exercise(String userInput) {
        this.userInput = userInput;
    }

    //Returns an Exercise enum object given a string, throws an exception if an invalid string is given
    public static Exercise fromUserInput(String input) {
        for(Exercise exercise : Exercise.values()) {
            if(exercise.userInput.equals(input)) {
                return exercise;
            }
        }
        throw new IllegalArgumentException("Invalid exercise input: " + input);
    }
}
