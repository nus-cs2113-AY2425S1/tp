package fittrack.exception;

import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;

import java.time.LocalDateTime;

import static fittrack.enums.Exercise.isValidExercise;
import static fittrack.messages.Messages.FEMALE_GENDER;
import static fittrack.messages.Messages.INVALID_EXERCISE_ACRONYM_MESSAGE;
import static fittrack.messages.Messages.INVALID_EXERCISE_DETAILS_MESSAGE;
import static fittrack.messages.Messages.INVALID_PULL_UP_REPETITIONS_MESSAGE;
import static fittrack.messages.Messages.INVALID_SESSION_INDEX_MESSAGE;
import static fittrack.messages.Messages.INVALID_SESSION_NAME_MESSAGE;
import static fittrack.messages.Messages.INVALID_SHUTTLE_RUN_TIMING_MESSAGE;
import static fittrack.messages.Messages.INVALID_SIT_AND_REACH_DISTANCE_MESSAGE;
import static fittrack.messages.Messages.INVALID_SIT_UP_REPETITIONS_MESSAGE;
import static fittrack.messages.Messages.INVALID_STANDING_BROAD_JUMP_DISTANCE_MESSAGE;
import static fittrack.messages.Messages.INVALID_USER_INFO_MESSAGE;
import static fittrack.messages.Messages.INVALID_WALK_AND_RUN_TIMING_MESSAGE;
import static fittrack.messages.Messages.MALE_GENDER;

public class ParserExceptions extends RuntimeException {
    public static String[] parseUserInfo(String userInput) {
        if(!userInput.contains(" ")) {
            throw new IllegalArgumentException(INVALID_USER_INFO_MESSAGE);
        }
        String[] userInfo = userInput.split(" ");
        if(userInfo.length != 2) {
            throw new IllegalArgumentException(INVALID_USER_INFO_MESSAGE);
        }
        return userInfo;
    }

    public static User validUser(String gender, String age) {
        if (!(gender.equals(MALE_GENDER) || gender.equals(FEMALE_GENDER))) {
            throw new IllegalArgumentException(INVALID_USER_INFO_MESSAGE);
        }
        if (Integer.parseInt(age) < 12 || Integer.parseInt(age) > 24) {
            throw new IllegalArgumentException(INVALID_USER_INFO_MESSAGE);
        }
        return new User(gender, age);
    }

    public static TrainingSession validSession(String description, User user) {
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_SESSION_NAME_MESSAGE);
        }
        return new TrainingSession(LocalDateTime.now(), description, user);
    }

    public static int validSessionIndex(int indexToDelete, int sessionListSize) {
        if(indexToDelete < 0 || indexToDelete >= sessionListSize) {
            throw new IllegalArgumentException(INVALID_SESSION_INDEX_MESSAGE);
        }
        return indexToDelete;
    }

    public static String[] validEditDetails(String description, int sessionListSize)
            throws IllegalArgumentException, NullPointerException {
        if(!description.contains(" ")) {
            throw new IllegalArgumentException(INVALID_EXERCISE_DETAILS_MESSAGE);
        }

        String[] editDetails = description.split(" ", 3);

        if(editDetails.length != 3) {
            throw new IllegalArgumentException(INVALID_EXERCISE_DETAILS_MESSAGE);
        }

        int sessionIndex = Integer.parseInt(editDetails[0]) - 1;
        String exerciseAcronym = editDetails[1];
        String exerciseData = editDetails[2];

        if(sessionIndex < 0 || sessionIndex >= sessionListSize) {
            throw new IllegalArgumentException(INVALID_SESSION_INDEX_MESSAGE);
        }

        if(!isValidExercise(exerciseAcronym)) {
            throw new IllegalArgumentException(INVALID_EXERCISE_ACRONYM_MESSAGE + exerciseAcronym);
        }

        switch(exerciseAcronym) {
        case "PU":
            if(Integer.parseInt(exerciseData) < 0) {
                throw new IllegalArgumentException(INVALID_PULL_UP_REPETITIONS_MESSAGE + exerciseData);
            }
            break;
        case "SR":
            if(!exerciseData.contains(".")) {
                throw new IllegalArgumentException(INVALID_SHUTTLE_RUN_TIMING_MESSAGE + exerciseData);
            }
            exerciseData = exerciseData.replace(".", "");
            if(Integer.parseInt(exerciseData) < 0) {
                throw new IllegalArgumentException(INVALID_SHUTTLE_RUN_TIMING_MESSAGE + exerciseData);
            }
            break;
        case "SAR":
            if(Integer.parseInt(exerciseData) < 0) {
                throw new IllegalArgumentException(INVALID_SIT_AND_REACH_DISTANCE_MESSAGE + exerciseData);
            }
            break;
        case "SU":
            if(Integer.parseInt(exerciseData) < 0) {
                throw new IllegalArgumentException(INVALID_SIT_UP_REPETITIONS_MESSAGE + exerciseData);
            }
            break;
        case "SBJ":
            if(Integer.parseInt(exerciseData) < 0) {
                throw new IllegalArgumentException(INVALID_STANDING_BROAD_JUMP_DISTANCE_MESSAGE + exerciseData);
            }
            break;
        case "WAR":
            if(!exerciseData.contains(":")) {
                throw new IllegalArgumentException(INVALID_WALK_AND_RUN_TIMING_MESSAGE + exerciseData);
            }
            String[] warTime = exerciseData.split(":");
            if(warTime.length != 2) {
                throw new IllegalArgumentException(INVALID_WALK_AND_RUN_TIMING_MESSAGE + exerciseData);
            }
            if(Integer.parseInt(warTime[0]) < 0 || Integer.parseInt(warTime[1]) < 0) {
                throw new IllegalArgumentException(INVALID_WALK_AND_RUN_TIMING_MESSAGE + exerciseData);
            }
            break;
        default:
            break;
        }
        return editDetails;
    }
}
