package fittrack.fitnessgoal;
import fittrack.user.User;
import java.time.LocalDateTime;

public class AddFitnessGoal {

    private String goalDescription;
    private LocalDateTime deadline;

    public AddFitnessGoal(String goalDescription, LocalDateTime deadline) {
        this.goalDescription = goalDescription;
        this.deadline = deadline;

    }

    public void addGoal(User user) {
        if (deadline == null) {
            System.out.println("Goal added: " + goalDescription + ". No deadline set."
                + System.lineSeparator());
        } else {
            System.out.println("Goal added: " + goalDescription + " with deadline: " + deadline
            +System.lineSeparator());
        }
    }
}
