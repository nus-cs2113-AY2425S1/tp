package fittrack.lookupkey;

import fittrack.enums.Gender;
import fittrack.exception.InvalidAgeException;

import java.util.Objects;

public class LookUpKey {
    private final Gender gender;
    private final int age;

    public LookUpKey(Gender gender, int age) throws InvalidAgeException {
        if (age < 12 || age > 24) {
            throw new InvalidAgeException("Age must be between 12 and 24 inclusive.");
        }
        this.gender = gender;
        this.age = age;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LookUpKey lookUpKey = (LookUpKey) object;
        return this.age == lookUpKey.age && this.gender == lookUpKey.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, age);
    }
}
