# User Guide

## Introduction

BuffBuddy is your all-in-one fitness tracking companion, designed to help you streamline and organize your workout routines. 
Whether you’re planning custom programs, tracking daily exercises, or logging your workout history, BuffBuddy keeps everything in one place. 
Build personalized workout plans, log progress, and stay motivated with an intuitive interface that’s perfect for fitness enthusiasts of all levels.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `BuffBuddy` from [here](https://github.com/AY2425S1-CS2113-W10-3/tp/releases/tag/v1.0).
3. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar BuffBuddy.jar` command to run the application.

## TERMINOLOGY:
- **EXERCISE**: An exercise defined by a name, rep, set, and weight.
- **DAY**: A ‘workout day’ is a collection of exercises to be done together.
- **PROGRAMME**: A programme is a collection of workout days.
- **DAILYRECORD** A daily record is a class containing day, water array and meal list objects.

---

## Features 


### 1. Create a New Program

Command: `prog create PROG_NAME /d /e /n EXERCISE_NAME /s SET /r REP /w WEIGHT /c CALORIES`

Example: `prog create Starter /d ONE /e /n Bench_Press /s 3 /r 12 /w 30 /c 200 /e /n Squat /s 3 /r 12 /w 50 /c 200 /d TWO /e /n Bicep_Curl /s 3 /r 12 /w 10 /c 100`

(To create a programme ‘Starter’ with Day One being Bench & Squat, Day Two being Bicep Curl.)

**Note**: Since this command can be quite long when adding multiple exercises across multiple days, you can alternatively create an empty program first and then edit it step by step.

### Alternative Method: Create an Empty Program and Edit It

1. **Create an Empty Program**:
    - Command: `prog create PROG_NAME`

   Example: `prog create Starter`

This creates a new program called "Starter" without adding any days or exercises.

2. **Edit the Program**:
- You can now use the `prog edit` command to add exercises or days to your program incrementally.

Example: `prog edit /p 1 /ad ONE /e /n Bench_Press /r 10 /s 30 /w 50`

This adds a new day "ONE" and inserts the exercise "Bench Press" with 10 reps, 30 sets, and 50kg.

---

### 2. View Program
Command: `prog view [PROG_INDEX]`

This command displays the detailed workout routine, separated by days, for the chosen program. 
Each exercise includes its name, sets, reps, weight and how many calories you have burnt. 
If no program index is provided, it defaults to viewing the currently active program.

Example: `prog view 1`

```
========================================
Viewing Program: Starter

Day 1: Name: ONE
1. Bench Press: 3 sets of 12 reps at 30 kg | Burnt 0 calories
2. Squat: 3 sets of 12 reps at 50 kg | Burnt 0 calories

Day 2: Name: TWO
1. Bicep Curl: 3 sets of 12 reps at 10 kg | Burnt 0 calories
========================================
```

---

### 3. List All Programs
Command: `prog list`

Lists all workout programs with their index and name.

Example: `prog list`
```
1: Starter  --  ACTIVE

2: Full Body
```
---

### 4. Editing a Program

**Command Format:**
```
prog edit [/p PROG_INDEX] [/d DAY_INDEX] [command] [args]
```

Edits a given programme based on command and argument flags.

**Parameters:**

- **/p PROG_INDEX** *(optional)*: Specifies the program index. Defaults to the current active program if omitted.  
  **Alias**: `/programme` – Either `/p` or `/programme` can be used to specify the programme index.


- **/d DAY_INDEX**: Specifies the day index within the program.  
  **Alias**: `/day` – Either `/d` or `/day` can be used to specify the day index.


- **command**: Determines the action type on the exercise or day:
  - `/a` : **Add a New Exercise** to the specified day.  
    **Alias**: `/addExercise` – Either `/ae` or `/addExercise` can be used to add an exercise.
  - `/u EXERICSE_INDEX` : **Update an Existing Exercise** within the specified day.  
    **Alias**: `/updateExercise` – Either `/ue` or `/updateExercise` can be used to update an exercise.
  - `/x EXERICSE_INDEX` : **Remove an Existing Exercise** from the specified day.  
    **Alias**: `/removeExercise` – Either `/xe` or `/removeExercise` can be used to delete an exercise.
  - `/ad`: **Create a New Day** in the program.  
    **Alias**: `/addDay` – Either `/ad` or `/addDay` can be used to add a day.
  - `/xd`: **Remove an Existing Day** from the program.  
    **Alias**: `/removeDay` – Either `/xd` or `/removeDay` can be used to delete a day.


- **args**: These arguments set specific attributes for edit actions:
  - **/w WEIGHT**: Sets the weight for the exercise on update.  
    **Alias**: `/weight` – Either `/w` or `/weight` can be used to specify weight.
  - **/r REPS**: Sets the repetitions on update.  
    **Alias**: `/reps` – Either `/r` or `/reps` can be used to specify repetitions.
  - **/s SETS**: Sets the number of sets on update.  
    **Alias**: `/sets` – Either `/s` or `/sets` can be used to specify sets.
  - **/n NAME**: Sets or updates the name of the exercise on update.  
    **Alias**: `/name` – Either `/n` or `/name` can be used to specify the exercise name.
  - **/c CALORIES**: Sets the calories of the exercise on update.  
    **Alias**: `/calories` – Either `/c` or `/calories` can be used to specify the calorie count.

**Example Commands:**

**Create a New Exercise**:
   ```plaintext
   prog edit /p 1 /d 1 /a "Push-Up /w 30 /r 15 /s 3 /c 100"
   ```
Adds a "Push-Up" exercise on Day 1 of Program 1.

**Update an Existing Exercise**:
   ```plaintext
   prog edit /p 1 /d 1 /u 1 /w 30 /r 12
   ```
Updates Exercise 1 on Day 1 of Program 1, setting the weight to 30 and resp to 12.

**Delete an Exercise**:
   ```plaintext
   prog edit /p 1 /d 1 /x 1
   ```
Deletes Exercise 1 on Day 1 of Program 1.

   **Create a New Day**:
   ```plaintext
   prog edit /p 1 /ad "Cardio Day"
   ```
   Creates a new empty Day named "Cardio Day" in Program 1.
   
   *Note*: Advanced users can directly create days with exercises using the syntax found in `Create Programme`
   ```plaintext
   prog edit /p 1 /ad "Pull Day" /e /n "Push-Up /w 30 /r 15 /s 3 /c 100 /e ...
   ```
   **Remove an Existing Day**:
   ```plaintext
   prog edit /p 1 /xd 1
   ```
   Removes Day 1 of Programme 1. 

---

### 5. Set Program as Active
Command: `prog start PROGRAMME_INDEX`

Sets given programme as the ‘active programme’, which other commands will default to if `programme_index` is not supplied.

---

### 6. Log a Workout
Command: `prog log /t DATE /p PROGRAMME_INDEX /d DAY_INDEX`  
- Date in form of `dd-MM-yyyy`

Log a workout as if each exercise was performed correctly.

Example: `prog log /t 12-12-2024 /p 1 /d 1`

```
Marking Day 1 as Done!

You’ve completed:
Starter Programme  
Workout 1  
Deadlift [3x10 100kg], Bench [3x12 30kg].
```

---

### 7. Change Active Programme
Command: `prog active INDEX`

Changes the ‘active’ programme to a new programme.

---

### 8. List Completed Workouts
Command: `history`

View all completed workouts in order.

---

### 9. Delete Program
Command: `prog delete INDEX`

Deletes the programme at the given index.

### 10. Add a Meal
Command: `meal add /n MEAL_NAME /c CALORIES /t DATE`

Adds a meal to the daily record of a specific date.

- `/n MEAL_NAME`: Name of the meal.
- `/c CALORIES`: Number of calories in the meal.
- `/t DATE`: Date in the format `dd-MM-yyyy`.

**Example**: `meal add /n Chicken_Breast /c 250 /t 30-10-2024`

Chicken Breast | 250kcal has been added to 30-10-2024.

---

### 11. Delete a Meal
Command: `meal delete /m MEAL_INDEX /t DATE`

Deletes a meal from the daily record of a specific date.

- `/m MEAL_INDEX`: Index of the meal to delete.
- `/t DATE`: Date in the format `dd-MM-yyyy`.

**Example**: `meal delete /m 1 /t 30-10-2024`

Chicken Breast | 250kcal has been deleted from 30-10-2024

---

### 12. View Meals
Command: `meal view /t DATE`

Displays all meals recorded for a specific date.

- `/t DATE`: Date in the format `dd-MM-yyyy`.

**Example**: `meal view /t 30-10-2024`

Meals for 30-10-2024:

1. Chicken Breast | 250 kcal
2. Scrambled Eggs | 150 kcal



## Command Summary

| Command                | Description                                                 | Format                                                                          | Example                                                         |
|------------------------|-------------------------------------------------------------|---------------------------------------------------------------------------------|-----------------------------------------------------------------|
| **Create Program**     | Creates a new workout program                               | `prog create PROG_NAME /d DAY_NAME /e /n EXERCISE_NAME /s SET /r REP /w WEIGHT` | `prog create Starter /d ONE /e /n Bench_Press /s 3 /r 12 /w 30` |
| **View Program**       | Displays the detailed workout routine of a specific program | `prog view [INDEX]`                                                             | `prog view 1`                                                   |
| **List Programs**      | Lists all workout programs with their index and name        | `prog list`                                                                     | `prog list`                                                     |
| **Edit Program**       | Edits exercises or days within a program                    | `prog edit /p PROG_INDEX /d DAY /u INDEX /w WEIGHT /r REP /s SET`               | `prog edit /p 1 /d 1 /u 1 /w 30`                                |
| **Set Active**         | Sets a program as the active one                            | `prog start INDEX`                                                              | `prog start 1`                                                  |
| **Log Workout**        | Logs a workout for a specific day                           | `log /p PROGRAMME_INDEX /d DAY_INDEX /t DATE`                                   | `log /p 1 /d 1 /t 12/10/2024`                                   |
| **View History**       | Displays the history of completed workouts                  | `history`                                                                       | `history`                                                       |
| **Delete Program**     | Deletes a program by its index                              | `prog delete INDEX`                                                             | `prog delete 1`                                                 |
| **Set Active Program** | Changes the active program to a new one                     | `prog active INDEX`                                                             | `prog active 2`                                                 |
| **Add Meal**           | Adds a meal to a daily record                               | `meal add /n MEAL_NAME /c CALORIES /t DATE`                                     | `meal add /n Chicken_Breast /c 250 /t 30-10-2024`               |
| **Delete Meal**        | Deletes a meal from a daily record                          | `meal delete /m MEAL_INDEX /t DATE`                                             | `meal delete /m 1 /t 30-10-2024`                                |
| **View Meals**         | Displays all meals for a specific date                      | `meal view /t DATE`                                                             | `meal view /t 30-10-2024`                                       |



