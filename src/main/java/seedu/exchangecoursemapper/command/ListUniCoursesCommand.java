package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class ListUniCoursesCommand extends Command {
    public static final String LINE_SEPARATOR = "-----------------------------------------------------";
    public static final String NUS_COURSE_CODE_KEY = "nus_course_code";
    public static final String PU_COURSE_CODE_KEY = "pu_course_code";
    public static final String NUS_COURSE_NAME_KEY = "nus_course_name";
    public static final String PU_COURSE_NAME_KEY = "pu_course_name";

    @Override
    public void execute(String userInput) {
        try (JsonReader jsonReader = Json.createReader(new FileReader(FILE_PATH))) {
            JsonObject jsonObject = jsonReader.readObject();
            String puName = getPuName(userInput);
            getUniCourses(jsonObject, puName);
        } catch (IOException e) {
            System.err.println(Exception.fileReadError());
        }
    }

    public String getPuName (String userInput) {
        String puName = userInput.replaceFirst("set", "").trim();

        if (puName.isEmpty()) {
            System.out.println("Please provide a University name.");
            System.out.println(LINE_SEPARATOR);
        }

        return puName;
    }

    public void getUniCourses (JsonObject jsonObject, String puName) {
        String lowerCasePuName = puName.toLowerCase();

        Set<String> universityNames = jsonObject.keySet();
        boolean found = false;

        for (String universityName : universityNames) {
            if (universityName.toLowerCase().equals(lowerCasePuName)) {
                found = true;

                JsonObject universityObject = jsonObject.getJsonObject(universityName);
                JsonArray courseArray = universityObject.getJsonArray("courses");

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
            System.out.println("University not found: " + puName);
            System.out.println(LINE_SEPARATOR);
        }
    }
}
