package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;

import java.io.IOException;
import javax.json.JsonObject;

public class ObtainEmailCommand extends Command {
    @Override
    public void execute(String userInput) {
        try  {
            JsonObject jsonObject = super.createJsonObject();
            String schoolName = getSchoolName(userInput).toLowerCase();
            String matchingSchool = findMatchingSchool(jsonObject, schoolName);

            if (matchingSchool != null) {
                JsonObject schoolInfo = jsonObject.getJsonObject(matchingSchool);
                handleEmail(schoolInfo, matchingSchool);
            } else {
                System.out.println("Error: Unknown university - " + schoolName);
            }

        } catch (IOException e) {
            System.err.println(Exception.fileReadError());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
