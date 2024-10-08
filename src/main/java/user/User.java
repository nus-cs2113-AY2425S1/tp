package user;

public class User {

    public enum Gender {
        MALE,
        FEMALE
    }

    public Gender gender;
    public int age;

    public User(String gender, String age) {
        this.gender = User.Gender.valueOf(gender.toUpperCase());
        this.age = Integer.parseInt(age);
    }

    public void setGender(String gender) {
        this.gender = User.Gender.valueOf(gender.toUpperCase());
    }

    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}
