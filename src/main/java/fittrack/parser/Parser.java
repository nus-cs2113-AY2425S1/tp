package fittrack.parser;
import fittrack.fitnessgoal.Goal;
import fittrack.healthprofile.FoodEntry;
import fittrack.healthprofile.FoodWaterIntake;
import fittrack.healthprofile.WaterEntry;
import fittrack.trainingsession.TrainingSession;
import fittrack.reminder.Reminder;
import fittrack.user.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static fittrack.enums.Exercise.fromUserInput;

import static fittrack.exception.ParserExceptions.validSession;
import static fittrack.exception.ParserExceptions.validSessionIndex;
import static fittrack.exception.ParserExceptions.validUser;
import static fittrack.exception.ParserExceptions.parseUserInfo;
import static fittrack.exception.ParserExceptions.validEditDetails;
import static fittrack.messages.Messages.ADD_FOOD_COMMAND;
import static fittrack.messages.Messages.ADD_GOAL_COMMAND;
import static fittrack.messages.Messages.ADD_REMINDER_COMMAND;
import static fittrack.messages.Messages.ADD_SESSION_COMMAND;
import static fittrack.messages.Messages.ADD_WATER_COMMAND;
import static fittrack.messages.Messages.DELETE_FOOD_COMMAND;
import static fittrack.messages.Messages.DELETE_GOAL_COMMAND;
import static fittrack.messages.Messages.DELETE_REMINDER_COMMAND;
import static fittrack.messages.Messages.DELETE_SESSION_COMMAND;
import static fittrack.messages.Messages.DELETE_WATER_COMMAND;
import static fittrack.messages.Messages.EDIT_EXERCISE_COMMAND;
import static fittrack.messages.Messages.EDIT_MOOD_COMMAND;
import static fittrack.messages.Messages.GRAPH_PERFORMANCE_COMMAND;
import static fittrack.messages.Messages.GRAPH_POINTS_COMMAND;
import static fittrack.messages.Messages.HELP_COMMAND;
import static fittrack.messages.Messages.INDEX_OUT_OF_BOUNDS_MESSAGE;
import static fittrack.messages.Messages.INVALID_DATETIME_MESSAGE;
import static fittrack.messages.Messages.INVALID_INDEX_NON_NUMERIC_MESSAGE;
import static fittrack.messages.Messages.INVALID_SESSION_INDEX_MESSAGE;
import static fittrack.messages.Messages.LIST_FOOD_COMMAND;
import static fittrack.messages.Messages.LIST_GOAL_COMMAND;
import static fittrack.messages.Messages.LIST_DAILY_INTAKE_COMMAND;
import static fittrack.messages.Messages.LIST_REMINDER_COMMAND;
import static fittrack.messages.Messages.LIST_SESSIONS_COMMAND;
import static fittrack.messages.Messages.LIST_UPCOMING_REMINDER_COMMAND;
import static fittrack.messages.Messages.LIST_WATER_COMMAND;
import static fittrack.messages.Messages.SEPARATOR;
import static fittrack.messages.Messages.SET_USER_COMMAND;
import static fittrack.messages.Messages.VIEW_SESSION_COMMAND;
import static fittrack.parser.ParserHelpers.parseDeadline;
import static fittrack.storage.Storage.updateSaveFile;
import static fittrack.ui.Ui.beginSegment;
import static fittrack.ui.Ui.printAddedReminder;
import static fittrack.ui.Ui.printAddedSession;
import static fittrack.ui.Ui.printDeletedReminder;
import static fittrack.ui.Ui.printDeletedSession;
import static fittrack.ui.Ui.printHelp;
import static fittrack.ui.Ui.printPerformanceGraph;
import static fittrack.ui.Ui.printPointGraph;
import static fittrack.ui.Ui.printReminderList;
import static fittrack.ui.Ui.printSessionList;
import static fittrack.ui.Ui.printSessionView;
import static fittrack.ui.Ui.printUnrecognizedInputMessage;
import static fittrack.ui.Ui.printUpcomingReminders;
import static fittrack.ui.Ui.printUpdatedMood;
import static fittrack.ui.Ui.printUser;


public class Parser {

    private static void printGoalList(ArrayList<String> goalList) {
        if (goalList.isEmpty()){
            System.out.println("Your goals list is currently empty.");
        } else {
            System.out.println("Your goals:");
            for (int i = 0; i < goalList.size(); i++) {
                System.out.println((i + 1) + ". " + goalList.get(i));
            }
        }
    }

    private static void printAddedGoal(ArrayList<Goal> goalList) {
        if (goalList.isEmpty()) {
            System.out.println("Your goals list is currently empty.");
        } else {
            Goal lastGoal = goalList.get(goalList.size() - 1); // Get the last added goal
            System.out.println("Goal added: " + lastGoal.getDescription());
            if (lastGoal.getDeadline() != null) {
                System.out.println("Deadline: " +
                    lastGoal.getDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            } else {
                System.out.println("No deadline set.");
            }
        }
    }

    private static void printDeletedGoal(ArrayList<Goal> goalList, String goalDescription) {
        System.out.println("Deleted goal: " + goalDescription);
        if (goalList.isEmpty()) {
            System.out.println("Your goals list is now empty.");
        } else {
            System.out.println("Current goals:");
            for (int i = 0; i < goalList.size(); i++) {
                System.out.println((i + 1) + ". " + goalList.get(i).getDescription());
            }
        }
    }

    public static void parse(User user, String input, ArrayList<TrainingSession> sessionList,
                             ArrayList<Reminder> reminderList, ArrayList<Goal> goalList, FoodWaterIntake
                                     foodWaterList) throws IOException {
        assert input != null : "Input must not be null";
        assert user != null : "User object must not be null";
        assert sessionList != null : "Session list must not be null";
        assert goalList != null : "Goal list must not be null";

        String[] sentence = {input, input};
        String command = input;
        String description = "";

        LocalDateTime timeNow = LocalDateTime.now();
        String formattedTimeNow = timeNow.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        // Split the input into command and description if applicable
        if (input.contains(" ")) {
            sentence = input.split(" ", 2);
            command = sentence[0].trim();
            description = sentence[1].trim();
        }

        switch (command) {
        case HELP_COMMAND:
            printHelp();
            break;
        case SET_USER_COMMAND:
            try {
                // Check if details are appropriate
                String[] userInfo = parseUserInfo(description);
                validUser(userInfo[0], userInfo[1]);

                user.setGender(userInfo[0]);
                user.setAge(userInfo[1]);

                // Report updated user details to user
                printUser(user.getAge(), user.getGender().toString().toLowerCase());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            break;
        case ADD_SESSION_COMMAND:
            try {
                sessionList.add(validSession(description, user));
                int sessionIndex = sessionList.size() - 1;
                String sessionDescription = sessionList.get(sessionIndex).getSessionDescription();
                printAddedSession(sessionList, sessionIndex);
                updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        case EDIT_EXERCISE_COMMAND:
            try {
                String[] userInput = validEditDetails(description,sessionList.size());
                int sessionIndex = Integer.parseInt(userInput[0]) - 1;
                String exerciseAcronym = userInput[1];
                String exerciseData = userInput[2];

                sessionList.get(sessionIndex).editExercise(fromUserInput(exerciseAcronym), exerciseData);
                printSessionView(sessionList, sessionIndex);
                updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        case LIST_SESSIONS_COMMAND:
            printSessionList(sessionList); // Print the list of sessions
            break;
        case VIEW_SESSION_COMMAND:
            try {
                int indexToView = validSessionIndex(description, sessionList.size());
                printSessionView(sessionList, indexToView); // Print the session view
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        case DELETE_SESSION_COMMAND:
            try {
                int indexToDelete = validSessionIndex(description, sessionList.size());
                TrainingSession sessionToDelete = sessionList.get(indexToDelete);
                String sessionDescription = sessionList.get(indexToDelete).getSessionDescription();
                sessionList.remove(indexToDelete);
                printDeletedSession(sessionList, sessionToDelete, sessionDescription);
                updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;

        case GRAPH_POINTS_COMMAND:
            description = description.trim();
            if(description.isEmpty()){
                printPointGraph(null, sessionList);
            } else {
                try {
                    printPointGraph(fromUserInput(description), sessionList);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            break;

        case GRAPH_PERFORMANCE_COMMAND:
            try {
                printPerformanceGraph(fromUserInput(description), sessionList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;

        case ADD_REMINDER_COMMAND:
            try {
                String[] remindInfo = description.split(" ", 2);

                // Check if sentence contains both description and deadline
                if (remindInfo.length < 2) {
                    throw new IllegalArgumentException("Input must contain both a description and a deadline.");
                }

                String inputDeadline = remindInfo[1];
                String inputDescription = remindInfo[0];

                // Validate description and deadline
                if (inputDescription.isEmpty()) {
                    throw new IllegalArgumentException("Reminder description must not be empty");
                }
                if (inputDeadline.isEmpty()) {
                    throw new IllegalArgumentException("Reminder deadline must not be empty");
                }

                // Parse the deadline, handle any parsing errors
                LocalDateTime deadline;
                try {
                    deadline = parseDeadline(inputDeadline);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException(INVALID_DATETIME_MESSAGE, e);
                }

                // Proceed with adding the reminder if no exceptions were thrown
                reminderList.add(new Reminder(inputDescription, deadline, user));
                printAddedReminder(reminderList);
                updateSaveFile(sessionList, goalList, reminderList, foodWaterList);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
            break;

        case DELETE_REMINDER_COMMAND:
            try {
                int reminderIndexToDelete;

                // Try parsing the index; handle invalid number formats
                try {
                    reminderIndexToDelete = Integer.parseInt(description) - 1;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(INVALID_INDEX_NON_NUMERIC_MESSAGE);
                }

                // Check if the index is within bounds
                if (reminderIndexToDelete < 0 || reminderIndexToDelete >= reminderList.size()) {
                    throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
                }

                // Retrieve and delete the reminder if the index is valid
                Reminder reminderToDelete = reminderList.get(reminderIndexToDelete);
                reminderList.remove(reminderIndexToDelete);
                printDeletedReminder(reminderList, reminderToDelete);
                updateSaveFile(sessionList, goalList, reminderList, foodWaterList);

            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        case LIST_REMINDER_COMMAND:
            printReminderList(reminderList);
            break;
        case LIST_UPCOMING_REMINDER_COMMAND:
            beginSegment();
            printUpcomingReminders(reminderList);
            break;

        case ADD_GOAL_COMMAND:  // use "add-goal" consistently in input and command handling
            if (!description.isEmpty()) {
                String[] goalParts = description.split(" ", 2);
                String goalDescription = goalParts[0];
                LocalDateTime goalDeadline = null;

                if (goalParts.length > 1) {
                    String goalDeadlineInput = goalParts[1];
                    try {
                        goalDeadline = parseGoalDeadline(goalDeadlineInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format: " + e.getMessage());
                        return;
                    }
                }
                Goal newGoal = new Goal(goalDescription, goalDeadline);
                goalList.add(newGoal);
                printAddedGoal(goalList);
            } else {
                System.out.println("Please specify a goal to add.");
            }
            updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
            break;

        case DELETE_GOAL_COMMAND:
            try {
                int index = Integer.parseInt(description) - 1;
                if (index >= 0 && index < goalList.size()) {
                    goalList.remove(index);
                    System.out.println("Goal at index " + (index + 1) + " has been removed.");
                } else {
                    System.out.println("Invalid goal index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please specify a valid index to delete.");
            }
            updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
            break;

        case LIST_GOAL_COMMAND:
            if (goalList.isEmpty()) {
                System.out.println("No goals to display.");
            } else {
                System.out.println("Goals:");
                for (int i = 0; i < goalList.size(); i++) {
                    System.out.println((i + 1) + ". " + goalList.get(i));
                }
            }
            break;

        case ADD_WATER_COMMAND:

            // Check if description is empty or not a valid single numeral
            if (description.isEmpty() || !description.matches("\\d+")) {
                System.out.println("Please provide a valid amount of water.");
                break;
            }

            int waterAmount = Integer.parseInt(description);

            foodWaterList.addWater(new WaterEntry(waterAmount, LocalDateTime.now()));

            System.out.println(SEPARATOR);
            System.out.println("Got it. I've added " + waterAmount + "ml of water at " + formattedTimeNow + ".");
            System.out.println(SEPARATOR);
            updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
            break;

        case DELETE_WATER_COMMAND:
            // Check if description is empty or not a valid single numeral
            if (description.isEmpty() || !description.matches("\\d+")) {
                System.out.println("Please provide a valid water index number.");
                break;
            }

            int waterIndex = Integer.parseInt(description) - 1;
            foodWaterList.deleteWater(waterIndex);
            updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
            break;

        case LIST_WATER_COMMAND:
            System.out.println(SEPARATOR);
            foodWaterList.listDailyWaterIntake();
            System.out.println(SEPARATOR);
            break;

        case ADD_FOOD_COMMAND:
            String[] foodParts = description.split(" ", 2); // Split description into parts
            if (foodParts.length > 1) { // Ensure there are both food name and calories
                String foodName = foodParts[0];
                try {
                    int calories = Integer.parseInt(foodParts[1].trim());
                    foodWaterList.addFood(new FoodEntry(foodName,calories,LocalDateTime.now()));
                    System.out.println(SEPARATOR);
                    System.out.println("Got it. I've added food item: " + foodName
                            + " (" + calories + " calories) at " + formattedTimeNow + ").");
                    System.out.println(SEPARATOR);
                    updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number for calories.");
                }
            } else {
                System.out.println("Please provide both food name and calories.");
            }
            break;

        case DELETE_FOOD_COMMAND:
            // Check if description is empty or not a valid single numeral
            if (description.isEmpty() || !description.matches("\\d+")) {
                System.out.println("Please provide a valid food index number.");
                break;
            }

            int foodIndex = Integer.parseInt(description) - 1;
            foodWaterList.deleteFood(foodIndex);
            updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
            break;

        case LIST_FOOD_COMMAND:
            System.out.println(SEPARATOR);
            foodWaterList.listDailyFoodIntake();
            System.out.println(SEPARATOR);
            break;

        case EDIT_MOOD_COMMAND:
            String[] editMoodParts = description.split(" ", 2);
            if (editMoodParts.length < 2) {
                System.out.println("Please specify the session-ID and new mood");

                return;
            }
            try {
              
                // Parse session ID and new Mood String from provided user input
                int sessionId = validSessionIndex(editMoodParts[0], sessionList.size());
                String newMood = editMoodParts[1]; // New mood from the second part

                // Call the edit method with the necessary arguments
                TrainingSession sessionToEdit = sessionList.get(sessionId);
                sessionToEdit.setMood(newMood);
                printUpdatedMood(sessionId, newMood);

            } catch (NumberFormatException e) {
                System.out.println(INVALID_SESSION_INDEX_MESSAGE);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            updateSaveFile(sessionList, goalList, reminderList, foodWaterList);
            break;

        case LIST_DAILY_INTAKE_COMMAND:
            // Combine water, food, and calorie lists into one daily intake summary
            System.out.println("Here is your daily intake summary:");
            foodWaterList.listDailyIntake();
            break;

        default:
            printUnrecognizedInputMessage(); // Response to unrecognized inputs
            break;
        }
    }

    private static LocalDateTime parseGoalDeadline(String inputDeadline)
            throws IllegalArgumentException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            try {
                return LocalDateTime.parse(inputDeadline, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                LocalDate date = LocalDate.parse(inputDeadline, dateFormatter);
                return date.atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use DD/MM/YYYY or DD/MM/YYYY HH:mm:ss.");
        }
    }

}
