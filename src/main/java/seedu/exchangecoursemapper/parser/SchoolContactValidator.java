package seedu.exchangecoursemapper.parser;

import javax.json.JsonObject;

import static seedu.exchangecoursemapper.constants.JsonKey.EMAIL_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.NUMBER_KEY;

public class SchoolContactValidator {

    /**
     * Returns if the provided contact type is a valid type.
     *
     * @param contactType The contact type to be checked.
     * @return true if the contact type is valid (either EMAIL_KEY or NUMBER_KEY), false otherwise.
     */
    public boolean isValidContactType(String contactType) {
        return EMAIL_KEY.equals(contactType) || NUMBER_KEY.equals(contactType);
    }

    /**
     * Returns if the provided school name exists.
     *
     * @param jsonObject The JSON object containing the data.
     * @param schoolName The name of the school to be checked.
     * @return true if the school name exists in the JSON object, false otherwise.
     */
    public boolean isSchoolValid(JsonObject jsonObject, String schoolName) {
        for (String key : jsonObject.keySet()) {
            if (key.toLowerCase().equals(schoolName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
