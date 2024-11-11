package seedu.utils;

import seedu.message.ErrorMessages;

public class DescriptionUtils {
    public static boolean isValidDescription(String description) {
        return (description == null || description.length() <= 50) ;
    }

    public static String parseDescription(String description) throws IllegalArgumentException {
        if (!isValidDescription(description)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_STRING_FORMAT + description
                    + ErrorMessages.INVALID_DESCRIPTION_GUIDE);
        }
        return description;
    }
}
