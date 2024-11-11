package fittrack.fitnessgoal;

import fittrack.user.User;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteFitnessGoal {

    private static final Logger LOGGER = Logger.getLogger(DeleteFitnessGoal.class.getName());
    private final String goalDescription;

    public DeleteFitnessGoal(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public void deleteGoal(User user) {
        if (user == null) {
            LOGGER.log(Level.WARNING, "User object is null. Goal deletion cannot proceed.");
            return;
        }

        boolean deleted = user.deleteGoal(goalDescription);

        if (deleted) {
            LOGGER.log(Level.INFO, "Successfully deleted goal: " + goalDescription);
        } else {
            LOGGER.log(Level.INFO, "Goal not found: " + goalDescription);
        }
    }
}
