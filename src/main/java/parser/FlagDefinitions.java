//@@author nirala-ts
package parser;
import java.util.HashSet;

public class FlagDefinitions {
    public static final String DATE_FLAG = "/t";
  
    public static final String PROGRAMME_FLAG = "/p";
    public static final String DAY_FLAG = "/d";
    public static final String EXERCISE_FLAG = "/e";
  
    public static final String NAME_FLAG = "/n";
    public static final String SETS_FLAG = "/s";
    public static final String REPS_FLAG = "/r";
    public static final String WEIGHT_FLAG = "/w";
    public static final String CALORIES_FLAG = "/c";
  
    public static final String REMOVE_EXERCISE_FLAG = "/xe";
    public static final String ADD_EXERCISE_FLAG = "/ae";
    public static final String UPDATE_EXERCISE_FLAG = "/ue";

    public static final String ADD_DAY_FLAG = "/ad";
    public static final String REMOVE_DAY_FLAG = "/xd";

    public static final String MEAL_INDEX = "/m";
    public static final String WATER_INDEX = "/w";
    public static final String VOLUME_FLAG = "/v";

    public static final HashSet<String> VALID_FLAGS = new HashSet<>();

    static {
        VALID_FLAGS.add(DATE_FLAG);
        VALID_FLAGS.add(PROGRAMME_FLAG);
        VALID_FLAGS.add(DAY_FLAG);
        VALID_FLAGS.add(EXERCISE_FLAG);
        VALID_FLAGS.add(NAME_FLAG);
        VALID_FLAGS.add(SETS_FLAG);
        VALID_FLAGS.add(REPS_FLAG);
        VALID_FLAGS.add(WEIGHT_FLAG);
        VALID_FLAGS.add(CALORIES_FLAG);
        VALID_FLAGS.add(REMOVE_EXERCISE_FLAG);
        VALID_FLAGS.add(ADD_EXERCISE_FLAG);
        VALID_FLAGS.add(UPDATE_EXERCISE_FLAG);
        VALID_FLAGS.add(ADD_DAY_FLAG);
        VALID_FLAGS.add(REMOVE_DAY_FLAG);
        VALID_FLAGS.add(MEAL_INDEX);
        VALID_FLAGS.add(VOLUME_FLAG);
    }
}
