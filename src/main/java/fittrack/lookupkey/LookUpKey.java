package fittrack.lookupkey;

import fittrack.enums.Gender;

import java.util.Objects;

public class LookUpKey {
    private final Gender gender;
    private final int age;

    public LookUpKey(Gender gender, int age) {
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
