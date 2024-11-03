package fittrack.exception;

import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;

import java.time.LocalDateTime;

public class UiExceptions extends RuntimeException {
    public static String[] parseUserInfo(String userInput) {
        if(!userInput.contains(" ")) {
            throw new IllegalArgumentException("Please provide a valid gender and age (e.g. male 12).");
        }
        String[] userInfo = userInput.split(" ");
        if(userInfo.length != 2) {
            throw new IllegalArgumentException("Please provide a valid gender and age (e.g. male 12).");
        }
        return userInfo;
    }

    public static TrainingSession newValidSession(String description, User user) {
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide a session name");
        }
        return new TrainingSession(LocalDateTime.now(), description, user);
    }

    public static User newValidUser(String gender, String age) {
        if (!(gender.equals("male") || gender.equals("female"))) {
            throw new IllegalArgumentException("Please provide a valid gender and age (e.g. male 12).");
        }
        if (Integer.parseInt(age) <= 0) {
            throw new IllegalArgumentException("Please provide a valid gender and age (e.g. male 12).");
        }
        return new User(gender, age);
    }
}
