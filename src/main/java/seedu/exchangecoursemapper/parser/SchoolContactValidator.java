package seedu.exchangecoursemapper.parser;

import javax.json.JsonObject;

import static seedu.exchangecoursemapper.constants.JsonKey.EMAIL_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.NUMBER_KEY;

public class SchoolContactValidator {

    public boolean isValidContactType(String contactType) {
        return EMAIL_KEY.equals(contactType) || NUMBER_KEY.equals(contactType);
    }

    public boolean isSchoolValid(JsonObject jsonObject, String schoolName) {
        for (String key : jsonObject.keySet()) {
            if (key.toLowerCase().equals(schoolName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}

