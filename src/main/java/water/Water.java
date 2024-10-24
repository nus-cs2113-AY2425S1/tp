package water;

import java.util.ArrayList;

public class Water {

    private ArrayList<Float> waterList;

    public Water() {
        waterList = new ArrayList<>();
    }

    public void addWater(float water) {
        waterList.add(water);
    }

    public float deleteWater(int index) {
        float waterToBeDeleted = waterList.get(index);
        waterList.remove(index);
        return waterToBeDeleted;
    }

    public ArrayList<Float> getWaterList() {
        return waterList;
    }

}

