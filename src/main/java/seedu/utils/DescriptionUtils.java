package seedu.utils;

import seedu.message.ErrorMessages;

public class DescriptionUtils {
    public static boolean isValidDescription(String description) {
        try {
            return (description == null || description.length() <= 120) ;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String parseDescription(String description) throws IllegalArgumentException {
        if (!isValidDescription(description)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_STRING_FORMAT + description
                    + ErrorMessages.INVALID_DESCRIPTION_GUIDE);
        }
        return description;
    }
}
