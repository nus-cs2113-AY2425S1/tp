package exceptions;

public class WaterExceptions extends RuntimeException {
    public WaterExceptions(String message) {
        super(message);
    }

    public static WaterExceptions doesNotExist() {
        return new WaterExceptions("Water log does not exist");
    }
}
