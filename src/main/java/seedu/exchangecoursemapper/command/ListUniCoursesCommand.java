package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.exception.UnknownUniversityException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import static seedu.exchangecoursemapper.constants.JsonKey.PU_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.PU_COURSE_NAME_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.NUS_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.NUS_COURSE_NAME_KEY;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class ListUniCoursesCommand extends Command {

    @Override
    public void execute(String userInput) {
        try (JsonReader jsonReader = Json.createReader(new FileReader(FILE_PATH))) {
            JsonObject jsonObject = jsonReader.readObject();
            String puName = getPuName(userInput);
            getUniCourses(jsonObject, puName);
        } catch (IOException e) {
            System.err.println(Exception.fileReadError());
        } catch (UnknownUniversityException e) {
            System.err.println(e.getMessage());
            System.out.println(LINE_SEPARATOR);
        } catch (java.lang.Exception e) {
            System.err.println(e.getMessage());
            System.out.println(LINE_SEPARATOR);
        }
    }

    public String getPuName (String userInput) throws java.lang.Exception {
        String puName = userInput.replaceFirst("set", "").trim();

        if (puName.isEmpty()) {
            throw new IllegalArgumentException("Please provide a University name.");
        }

        return puName;
    }

    public void getUniCourses (JsonObject jsonObject, String puName) throws UnknownUniversityException {
        String lowerCasePuName = puName.toLowerCase();

        Set<String> universityNames = jsonObject.keySet();
        boolean found = false;

        for (String universityName : universityNames) {
            if (universityName.toLowerCase().equals(lowerCasePuName)) {
                found = true;

                JsonObject universityObject = jsonObject.getJsonObject(universityName);
                JsonArray courseArray = universityObject.getJsonArray("courses");

                if (courseArray == null) {
                    throw new IllegalArgumentException("No courses found for university: " + puName);
                }

                for (int i = 0; i < courseArray.size(); i++) {
                    JsonObject courseObject = courseArray.getJsonObject(i);
                    String puCourseCode = courseObject.getString(PU_COURSE_CODE_KEY);
                    String puCourseName = courseObject.getString(PU_COURSE_NAME_KEY);
                    String nusCourseCode = courseObject.getString(NUS_COURSE_CODE_KEY);
                    String nusCourseName = courseObject.getString(NUS_COURSE_NAME_KEY);

                    System.out.println(puCourseCode + ": " + puCourseName);
                    System.out.println(nusCourseCode + ": " + nusCourseName);
                    System.out.println(LINE_SEPARATOR);
                }
                break;
            }
        }
        if (!found && (!puName.isEmpty())) {
            throw new UnknownUniversityException("University not found: " + puName);
        }
    }
}
