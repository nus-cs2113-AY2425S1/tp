# FitTrackCLI User Guide

## Introduction

FitTrackCLI is command-line-based chatbot to help users manage their NAPFA related exercises and goals!
The user can:
1. Record and track training sessions.
2. Calculate NAPFA scores from training sessions.
3. Set fitness goals.
4. Visualise their training progress.

The NAPFA score sheet used for this chatbot can be found here:
This guide will bring you through the various features of FitTrackCLI, and how to utilise them!


## FitTrackCLI's Features

### 1. User Configuration: `set`

**Purpose**: Set the age and gender of the user.

**Format**: `set [gender]  [age]`
- `gender` and `age` field must be non-empty. Gender must be "male" or "female". Age must be an integer.

**Example**: `male 12`

**Expected output**:
```
____________________________________________________________________________________________________
You are a 12 year old male.
____________________________________________________________________________________________________
```

### 2. Help Function: `help`
**Purpose**: Prints a complete list of valid commands.

**Format**: `help`

**Expected output**:
```
____________________________________________________________________________________________________
COMMAND                                                          EXAMPLE
help                                                             help
set (gender) (age)                                               set male 12
add (session name)                                               add session1
list                                                             list
view (session index)                                             view 1
edit (session index) (exercise acronym) (repetitions/time)       edit 1 PU 1
delete (session index)                                           delete 1
remind (Event / Task) (deadline)                                 remind NAPFA DD/MM/YYYY
list-remind                                                      list-remind 
delete-remind (reminder index)                                   delete-remind1
upcoming-remind                                                  upcoming-remind
add-goal (goal name) (deadline)                                  add-goal run 12/12/2024 14:00:00
delete-goal (goal index)                                         delete-goal 1
list-goal                                                        list-goal
add-water (amount)                                               add-water 500
delete-water (index)                                             delete-water 1
list-water                                                       list-water
add-food (food item) (calories)                                  add-food apple100
delete-food (index)                                              delete-food 1
list-food                                                        list-food
list-intake                                                      list-intake
add-mood (mood description) (date) (time) (description)          add-moodhappy 01/11/2024 18:30:00 feeling healthy
delete-mood (mood ID)                                            delete-mood 1
list-mood                                                        list-mood
exit                                                             exit
____________________________________________________________________________________________________
```

### 3. Add a Training Session: `add`
**Purpose**: Adds a Training Session with the specified name.

**Format**: `add [session name]`
- `session name` field must be non-empty.

**Example**: `add session1`

**Expected output**:
```
____________________________________________________________________________________________________
Got it. I've added a new training session:
1. session1 | 29/10/2024 12:40
There are 1 sessions in the list.
____________________________________________________________________________________________________
```

### 4. List all Training Sessions: `list`
**Purpose**: Displays all Training Sessions the user has added.

**Format**: `list`

**Example Output**:
```
____________________________________________________________________________________________________
Here are your training sessions:
1. session1 | 29/10/2024 12:40
2. session2 | 29/10/2024 12:41
There are 2 sessions in the list.
____________________________________________________________________________________________________
```

### 5. View a Training Session: `view`
**Purpose**: View the details of a Training Session, including session name, datetime, exercise data, points and awards.

**Format**: `view [session index]`

**Example**: `view 1`

**Expected Output**:
```
____________________________________________________________________________________________________
Training Session: session1
Training Datetime: 29/10/2024 12:40
Pull Up Station | Reps: 0 | 0 points
Shuttle Run Station | Time: NA | 0 points
Sit and Reach Station | Distance: 0cm | 0 points
Sit Up Station | Reps: 0 | 0 points
Standing Broad Jump Station | Distance: 0cm | 0 points
Walk and Run Station | Time: NA | 0 points
Total points: 0
Overall Award: No Award
____________________________________________________________________________________________________
```

### 6. Edit a Training Session: `edit`
**Purpose**: Edit the details of a training session, namely exercise and reps/time.

**Format**: `edit [session index] [exercise acronym] [repetitions/time]`

**Example**: `edit 1 PU 45`

**Expected Output**:
```
1
Exercise edited! Here's your new input: Reps: 45 | 5 points
____________________________________________________________________________________________________
Training Session: session1
Training Datetime: 29/10/2024 12:40
Pull Up Station | Reps: 45 | 5 points
Shuttle Run Station | Time: NA | 0 points
Sit and Reach Station | Distance: 0cm | 0 points
Sit Up Station | Reps: 0 | 0 points
Standing Broad Jump Station | Distance: 0cm | 0 points
Walk and Run Station | Time: NA | 0 points
Total points: 5
Overall Award: No Award
____________________________________________________________________________________________________
```

### 7. Deleting a Training Session: `delete`
**Purpose**: Removes a Training Session from the list.

**Format**: `delete [session index]`

**Example**: `delete 1`

**Expected Output**:
```
____________________________________________________________________________________________________
Got it. I've deleted this training session:session1 | 29/10/2024 12:40
There are 1 sessions in the list.
____________________________________________________________________________________________________
```

### 8. Exiting the program: `exit`
**Purpose**: Ends FitTrack CLI task and exits.

**Format**: `exit`

**Expected Output**:
```
____________________________________________________________________________________________________
Bye! Hope to see you again soon!
____________________________________________________________________________________________________
```

### 9. Add a Reminder: `remind`
**Purpose**: Adds a Reminder with the specified description and due date.

**Format**: `remind [description] [deadline]`
- `description` and `deadline` fields must be non-empty.
- `deadline` field must be formatted `dd-MM-yyyy` or `dd-MM-yyyy HH:mm`. 
- If `deadline` field is given as `dd-MM-yyyy`, `HH:mm` information will default to `00:00` on that date. 

**Example**: `remind NAPFA 31/12/2024`

**Expected output**:
```
____________________________________________________________________________________________________
Got it. I've added a new reminder
1. NAPFA | 31/12/2024 00:00
There are 1 reminders in your list.
____________________________________________________________________________________________________
```

### 10. List all Reminders: `list-remind`
**Purpose**: Displays all active Reminders the user has added.

**Format**: `list-remind`

**Example Output**:
```
____________________________________________________________________________________________________
Here are your reminders:
1. TEST1 | 31/12/2024 00:00
2. TEST2 | 30/12/2024 00:00
3. TEST3 | 29/12/2024 00:00
There are 3 reminders in your list.
____________________________________________________________________________________________________
```

### 11. List soon-due Reminders: `upcoming-remind`
**Purpose**: Displays all Reminders the user has added that are due in the next week (7 days).

**Format**: `upcoming-remind`

**Example Output**:
```
____________________________________________________________________________________________________
There are 1 reminders due in the next week:
1. UPCOMINGTEST | 06/11/2024 00:00
You have 2 reminders in total. View them with 'list-remind'.
____________________________________________________________________________________________________
```

### 12. Delete a Reminder: `delete-remind`
**Purpose**: Removes a reminder from your list.

**Format**: `delete-remind [reminder index]`
- `reminder index` can be found using the `list-remind` command.

**Example**: `delete-remind 1`

**Expected Output**:
```
____________________________________________________________________________________________________
Got it. I've deleted this reminder:NAPFA | 31/12/2024 00:00
There are 0 reminders in your list.
____________________________________________________________________________________________________
```

### 13. Add Goal: `add-goal`
**Purpose**: User can add a fitness goal to the the list
of goals and attach a deadline to it in order to
have clear targets to prepare for the NAPFA test.

**Format**: `add-goal (goal name) (deadline)`

**Example**: `add-goal run 12/12/2024 14:00:00`

**Expected Output**:
```
____________________________________________________________________________________________________
Goal added: run
Deadline: 12/12/2024 14:00:00
____________________________________________________________________________________________________
```

### 14. Delete Goal: `delete-goal`
User can delete a fitness goal to the the list
of goals to moderate a fitness goal.

**Format**: `delete-goal (goal index)`

**Example**: `delete-goal 1`

**Expected Output**:
```
____________________________________________________________________________________________________
Goal at index 1 has been removed.
____________________________________________________________________________________________________
```

### 15. List of Goals: `list-goal`
View a list of all fitness goals and deadlines
to keep track of progress in preparation for the NAPFA test

Input Command:
list-goal

Example Usage:
list-goal

**Format**: `list-goal`

**Example**: `list-goal`

**Expected Output**:
```
____________________________________________________________________________________________________
Goals:
1. Goal: run, Deadline: 2024-12-12T14:00
____________________________________________________________________________________________________
```

### 16. Display Non-Time Station Graph
**Purpose**: View the progress of User's performance for SitUpStation, PullUpStation, StandingBroadJumpStation
and SitAndReachStation class in the form of a bar chart.

**Format**: `graph [exercise acronym]`

**Example**: `graph ______` 

**Expected Output**:
```
```


### 17. Display Time Station Graph
**Purpose**: View the progress of User's performance for WalkAndRunStation class and ShuttleRunStation class 
in the form of a point graph.

**Format**: `graph [exercise acronym]`

**Example**: `graph ______`

**Expected Output**:
```
```

### 18. Add Food Intake: `add-food`
View a list of daily food intake
to have a more comprehensive understanding
of factors affecting my fitness.

**Format**: `add-food (food name) (calories)`

**Example**: `add-food apple 100`

**Expected Output**:
```
____________________________________________________________________________________________________
Got it. I've added food item: apple (100 calories, 06/11/2024 17:32:07).
____________________________________________________________________________________________________
```

### 19. Delete Food Intake: delete-food
**Purpose**: Remove a food item from 
the daily food intake list.

**Format**: `delete-food (food name)`

**Example**: `delete-food apple`

**Expected Output**:
```
____________________________________________________________________________________________________
Got it. I've deleted food item: apple (100 calories) at 06/11/2024 17:30:57
____________________________________________________________________________________________________
```

### 20. List Food Intake: list-food
**Purpose**: Display the list of all food items 
that have been added for the day.

**Format**: `list-food`

**Example**: `list-food`

**Expected Output**:
```
____________________________________________________________________________________________________
Here is your food intake list: 
1. apple (100 calories) at 06/11/2024 17:32:07
____________________________________________________________________________________________________
```

### 21. Add Water Intake: add-water
**Purpose**: Add water intake in milliliters 
to track hydration levels.

**Format**: `add-water (water ml)`

**Example**: `add-water 500`

**Expected Output**:
```
____________________________________________________________________________________________________
Got it. I've added 500ml of water at 06/11/2024 17:33:05.
____________________________________________________________________________________________________
```

### 22. Delete Water Intake: delete-water
**Purpose**: Remove a specified amount of water 
from the daily water intake record.

**Format**: `delete-water (water ml)`

**Example**: `delete-water 1`

**Expected Output**:
```
____________________________________________________________________________________________________
Got it. I've deleted 500 ml (06/11/2024 17:30:46).
____________________________________________________________________________________________________
```

### 23. List Water Intake: list-water
**Purpose**: Display the total water 
intake recorded for the day.

**Format**: `list-water`

**Example**: `list-water`

**Expected Output**:
```
____________________________________________________________________________________________________
Here is your water intake (in ml): 
1. 500 ml (06/11/2024 17:34:14)
____________________________________________________________________________________________________
```

### 24. List Daily Intake: list-intake
**Purpose**: Display the list of all food items
and water items that have been added for the day.

**Format**: `list-intake`

**Example**: `list-intake`

**Expected Output**:
```
____________________________________________________________________________________________________
Here is your daily intake summary:

Water Intake:
Here is your water intake (in ml): 
1. 500 ml (06/11/2024 17:35:24)

Food Intake:
Here is your food intake list: 
1. apple (100 calories) at 06/11/2024 17:35:18
____________________________________________________________________________________________________
```

### 28. Add Mood Log: add-mood

**Purpose**: Record a mood entry for a specific date and time.

**Format**: `add-mood <mood description> <date> <time> <optional description>`

**Example**: `add-mood happy 01/11/2024 18:30:00 feeling rejuvenated`

**Expected Output**:
```
____________________________________________________________________________________________________
Mood log added: Mood: happy, Timestamp: 01/11/2024 18:30:00, Description: feeling rejuvenated
____________________________________________________________________________________________________
```

### 29. Delete Mood Log: delete-mood
**Purpose**: Remove a specific mood entry by its ID.

**Format**: `delete-mood <mood ID>`

**Example**: `delete-mood 2`

**Expected Output**:
```
____________________________________________________________________________________________________
Mood log deleted with ID: 1
____________________________________________________________________________________________________
```

### 30. List Mood Logs: list-mood
**Purpose**: Display all recorded mood entries.

**Format**: `list-mood`

**Example**: `list-mood`

**Expected Output**:
```
____________________________________________________________________________________________________
Your mood logs:
1. Mood: happy, Timestamp: 01/11/2024 18:30:00, Description: feeling rejuvenated
____________________________________________________________________________________________________
```

## FitTrackCLI's Command Summary
| Command             | Format                                                         | Example                              |
|---------------------|----------------------------------------------------------------|--------------------------------------|
| **set**             | `set GENDER AGE`                                               | `set male 12`                        |
| **help**            | `help`                                                         | `help`                               |
| **add**             | `add SESSION_NAME`                                             | `add session1`                       |
| **list**            | `list`                                                         | `list`                               |
| **view**            | `view SESSION_INDEX`                                           | `view 1`                             |
| **edit**            | `edit SESSION_INDEX EXERCISE_ACRONYM REPETITION/TIME_DURATION` | `edit 1 PU 45`                       |
| **delete**          | `delete SESSION_INDEX `                                        | `delete 1 `                          |
| **exit**            | `exit`                                                         | `exit`                               |
| **remind**          | `remind REMINDER_NAME DEADLINE`                                | `remind run 12/12/2024`              |
| **list-remind**     | `list-goal`                                                    | `list-remind`                        |
| **upcoming-remind** | `upcoming-remind`                                              | `upcoming-remind`                    |
| **delete-remind**   | `delete-remind REMINDER_INDEX`                                 | `delete-remind 1`                    |
| **add-goal**        | `add-goal GOAL_NAME DEADLINE`                                  | `add-goal run 12/12/2024 14:00:00`   |
| **delete-goal**     | `delete-goal GOAL_INDEX`                                       | `delete-goal 1`                      |
| **list-goal**       | `list-goal`                                                    | `list-goal`                          |
| **graph**           | `graph EXERCISE_ACRONYM`                                       | `graph PU`                           |
| **add-water**       | `add-water`                                                    | `add-water 500`                      |
| **delete-water**    | `delete-water`                                                 | `delete-water 1`                     |
| **list-water**      | `list-water`                                                   | `list-water`                         |
| **add-food**        | `add-food`                                                     | `add-food apple 100`                 |
| **delete-food**     | `delete-food`                                                  | `delete-food 1`                      |
| **list-food**       | `list-food`                                                    | `list-food`                          |
| **list-calories**   | `list-intake`                                                  | `list-intake`                        |
| **add-mood**       | `add-mood`                                                     | `add-mood happy 01/11/2024 18:30:00` |
| **delete-mood**       | `delete-mood`                                                  | `delete-mood 1`                      |
| **list-mood**       | `list-mood`                                                    | `list-mood`                          |
