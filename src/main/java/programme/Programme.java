package programme;

import java.util.ArrayList;

public class Programme {
    private final String programmeName;
    private final ArrayList<Day> dayList;

    public Programme(String programmeName, ArrayList<Day> dayList) {
        this.programmeName = programmeName;
        this.dayList = dayList;
    }

    public Day getDay(int index) {
        return dayList.get(index);
    }

    public void insertDay(Day day) {
        dayList.add(day);
    }

    public int getDayCount() {
        return dayList.size();
    }

    public Day deleteDay(int index){
        if (index < 0 || index >= dayList.size()) {
            System.out.println("Invalid index");
            return null; // Return null if the index is invalid
        }
        Day toBeDeleted = dayList.get(index);
        dayList.remove(index);
        return toBeDeleted;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(programmeName).append("\n\n");

        for (int i = 0; i < dayList.size(); i++) {
            str.append("Day ").append(i+1).append(": ").append(dayList.get(i)).append("\n");
        }

        return str.toString();
    }
}
