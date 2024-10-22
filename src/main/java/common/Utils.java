package common;

public class Utils {

    public static boolean isNull(int val) {
        return val == -1;
    }

    public static boolean isNull(String val) {
        return val == null || val.isEmpty();
    }
}
