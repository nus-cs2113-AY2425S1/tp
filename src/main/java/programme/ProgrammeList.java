package programme;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ProgrammeList {

    int currentActiveProgramme;
    private final ArrayList<Programme> programmeList;

    public ProgrammeList() {
        programmeList = new ArrayList<>();
    }

    public ArrayList<Programme> getProgrammeList() {
        return programmeList;
    }

    public int getProgrammeListSize(){
        return programmeList.size();
    }

    public Programme insertProgramme(String programmeName, ArrayList<Day> days) {
        Programme programmeToAdd = new Programme(programmeName, days);
        programmeList.add(programmeToAdd);
        return programmeToAdd;
    }

    public Programme deleteProgram(int index){
        if (index < 0 || index >= programmeList.size()) {
            System.out.println("invalid index");
            return null; // Return null if the index is invalid
        }

        Programme programmeToDelete = programmeList.get(index);
        programmeList.remove(index);
        return programmeToDelete;
    }

    public Programme getProgramme(int index){
        return programmeList.get(index);
    }

    public Programme startProgramme(int startIndex) {
        currentActiveProgramme = startIndex;
        return programmeList.get(currentActiveProgramme);
    }

    public Day getDay(int progIndex, int dayIndex) {
        if (dayIndex  == -1) {
            dayIndex = currentActiveProgramme;
        }
        Programme progContent = programmeList.get(progIndex);
        return progContent.getDay(dayIndex);
    }

    public Day deleteDay(int progIndex, int dayIndex) {
        return programmeList.get(progIndex).deleteDay(dayIndex);
    }

    public void insertDay(int progIndex, Day day){
        programmeList.get(progIndex).insertDay(day);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < programmeList.size(); i++) {
            Programme programme = programmeList.get(i);
            if (i == currentActiveProgramme) {
                str.append("*Active* ");
            }
            str.append(programme).append("\n");
        }
        return str.toString();
    }

    public JsonObject toJson() {
        Gson gson = new Gson();
        return gson.toJsonTree(this).getAsJsonObject();
    }

    public static ProgrammeList fromJson(JsonObject jsonObject) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, ProgrammeList.class);
    }
}
