# User Guide

## Introduction

BuffBuddy is your all-in-one fitness tracking companion, designed to help you streamline and organize your workout routines.
Whether you’re planning custom programmes, tracking daily exercises, or logging your workout history, BuffBuddy keeps everything in one place.
Build personalized workout plans, log progress, and stay motivated with an intuitive interface that’s perfect for fitness enthusiasts of all levels.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `BuffBuddy` from [here](https://github.com/AY2425S1-CS2113-W10-3/tp/releases/tag/v1.0).
3. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar BuffBuddy.jar` command to run the application.

## Terminology:

- **Exercise**: An exercise defined by a name, number of reps and sets, weight and average calories burned.
- **Day**: A ‘workout day’ is a collection of exercises to be done together.
- **Programme**: A programme is a collection of workout days.
- **Daily Record**: A daily record contains a user's workout activity, food intake and water intake for any given day.

---

## To Note

- Parameters that the user needs to provide are in `UPPER_CASE`, while commands that the user must follow exactly are in `lowercase`.
  For example in `prog create PROG_NAME`, `PROG_NAME` should be replaced by the user's own input while `prog create` should be followed exactly.
  So an example of a valid command would look like this: `prog create Starter_Programme`

- Items in square brackets are optional inputs.
  For example for 'prog delete [PROG_INDEX]', PROG_INDEX is optional. 
  So examples of a valid command would look like this: 'prog delete', 'prog delete 1'

- Extra parameters that are not required in a command will be ignored.
  For example in case of the `bye` command, any parameter given after "bye" will be ignored. 
  So, `bye /d 45` will simply be interpreted as `bye`, and the programme will exit itself.

- The **/** sequence is a reserved character. If entering " /name sitUps/Crunches," it will be interpreted as "sitUps" only.

- The `DATE` parameter expects the date in `dd-MM-yyyy` format. `WATER_VOLUME` is expected to be in litres and `CALORIES` is expected
  to be in kCal.


## Features

### 1. Add New Programme

This feature adds a new empty workout programme with a specified name.

**Command**: `prog create PROG_NAME`

**Example**: `prog create Starter`

```
========================================
New programme created:
Starter
========================================
```

_Note_: Advanced users can create a detailed programme with multiple days and exercises in one step by using the following command structure. 
        This allows users to add specific exercises with sets, reps, weight, and calorie details for each day:

**Command**: `prog create PROG_NAME /d DAY_NAME /e /n EXERCISE_NAME /s SETS /r REPS /w WEIGHT /c CALORIES /e ...`

**Example**: `prog create Advanced Starter /d Monday /e /n Bench_Press /s 3 /r 15 /w 30 /c 200 /e /n Squat /s 3 /r 15 /w 50 /c 200 /d Wednesday /e /n Bicep_Curl /s 3 /r 10 /w 10 /c 100`
 
```
==================================================
New programme created: 
Advanced Starter

Day 1: Monday
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 15 at 50kg | Burnt 200 cals

Day 2: Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 cals
==================================================
```
If the programme list was empty, the new programme added would be set to active by default. 

---

### 2. Set Programme as Active

This feature sets the specified programme as the "active programme".  
Once a programme is active, other commands will default to this programme if `PROG_INDEX` is not provided for those commands.

**Command**: `prog start [PROG_INDEX]`

**Example**: `prog start 2`

```
==================================================
Started programme: 
Advanced Starter

Day 1: Monday
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 15 at 50kg | Burnt 200 cals

Day 2: Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 cals
==================================================
```

---

### 3. List All Programmes

This feature displays a list of all workout programmes created by the user, showing each programme’s name and index.

**Command**: `prog list`

**Example**: `prog list`

```
==================================================
Listing programmes: 
1. Starter
2. Advanced Starter -- Active
==================================================
```

---

### 4. View Programme

This feature displays the detailed workout routine for the specified programme, organized by day. 
Each exercise entry includes its name, number of sets and reps, weight, and estimated calorie burn.

**Command**: `prog view [PROG_INDEX]`

If `PROG_INDEX` is not specified, the command defaults to displaying the details of the currently active programme.


**Example**: `prog view 2`

```
==================================================
Viewing programme: 
Advanced Starter

Day 1: Monday
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 15 at 50kg | Burnt 200 cals

Day 2: Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 cals
==================================================
```

---

### 5. Delete Programme

This feature deletes the programme at the specified index.

_Note_: If the programme deleted was the active programme, the 1st programme will be set to active.

**Command**: `prog delete [PROG_INDEX]`

If `PROG_INDEX` is not specified, the command defaults to deleting the currently active programme.

**Example**: `prog delete 2`

```
==================================================
Deleted:
Advanced Starter

Day 1: Monday
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 15 at 50kg | Burnt 200 cals

Day 2: Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 cals
==================================================
```

---

### 6. Add a New Day to an Existing Programme

Add a new Day to an existing programme.

Command: `prog edit [/p PROG_INDEX] /ad DAY_NAME`

Parameters:
- **/p PROG_INDEX** _(optional)_: Specifies the Programme index. Defaults to the current active Programme if omitted.  
  **Alias**: `/programme` – Either `/p` or `/programme` can be used to specify the programme index.
- **/ad DAY_NAME**: DAY_NAME is a string that names the day. 
  **Alias**: `/addDay` – Either `/ad` or `/addDay` can be used to add a day.

Example: `prog edit /p 1 /ad Cardio Day`

Creates a new empty Day named "Cardio Day" in Programme 1.

```plaintext
Created new day: Cardio Day
```

_Note_: Advanced users can directly create days with exercises using the syntax found in `Create Programme`

```plaintext
prog edit /p 1 /ad Pull Day /e /n "Push-Up /w 30 /r 15 /s 3 /c 100 /e ...
```

---

### 7. Delete a Day from an Existing Programme

Delete a day from an existing programme.

Command: `prog edit [/p PROG_INDEX] /xd DAY_INDEX`

Parameters:
- **/p PROG_INDEX** _(optional)_: Specifies the Programme index. Defaults to the current active Programme if omitted.  
  **Alias**: `/programme` – Either `/p` or `/programme` can be used to specify the programme index.
- **/xd DAY_INDEX**: DAY_INDEX is the index of the day in the programme that the user wants to delete.
  **Alias**: `/removeDay` – Either `/xd` or `/removeDay` can be used to delete a day.

Example: `prog edit /p 1 /xd 1`

Deletes day 1 in programme 1

```plaintext
Deleted day:
Cardio Day
```

---

### 8. Add a New Exercise in a Programme

Add an exercise to an existing day in an existing programme.
**NOTE:** The exercise is added to an **EXISTING** day.

Command: `prog edit [/p PROG_INDEX] /d DAY_INDEX /a /n EXERCISE_NAME /w WEIGHT /r REPS /s SETS /c CALORIES`

Parameters:
- **/p PROG_INDEX** _(optional)_: Specifies the Programme index. Defaults to the current active Programme if omitted.  
  **Alias**: `/programme` – Either `/p` or `/programme` can be used to specify the programme index.
- **/d DAY_INDEX**: Index of day the user wants to add exercise to.
- **/a** : Flag to add exercise.
  **Alias**: `/addExercise` – Either `/ae` or `/addExercise` can be used to add an exercise.
- **/n NAME**: Sets or updates the name of the exercise on update.  
  **Alias**: `/name` – Either `/n` or `/name` can be used to specify the exercise name.
- **/w WEIGHT**: Sets the weight for the exercise on update.  
  **Alias**: `/weight` – Either `/w` or `/weight` can be used to specify weight.
- **/r REPS**: Sets the repetitions on update.  
  **Alias**: `/reps` – Either `/r` or `/reps` can be used to specify repetitions.
- **/s SETS**: Sets the number of sets on update.  
  **Alias**: `/sets` – Either `/s` or `/sets` can be used to specify sets.
- **/c CALORIES**: Sets the calories of the exercise on update.  
  **Alias**: `/calories` – Either `/c` or `/calories` can be used to specify the calorie count.

Example: `prog edit /p 1 /d 1 /a /n Push-Up /w 30 /r 15 /s 3 /c 100`

Add an exercise called 'Push Up' with a weight of 30, 15 reps, 3 sets, and 100 calories burned to Day 1 of Programme 1.

```plaintext
Created new exercise:
Push-Up: 3 sets of 15 at 30 | Burnt 100 cals
```

---

### 9. Delete Exercise in a Programme

Delete an exercise from an existing day in an existing programme.
**NOTE:** The exercise is deleted from an **EXISTING** day.

Command: `prog edit [/p PROG_INDEX] /d DAY_INDEX /x EXERCISE_INDEX`

Parameters:
- **/p PROG_INDEX** _(optional)_: Specifies the Programme index. Defaults to the current active Programme if omitted.  
  **Alias**: `/programme` – Either `/p` or `/programme` can be used to specify the programme index.
- **/d DAY_INDEX**: Index of day the user wants to delete an exercise from.
- **/x EXERCISE_INDEX** : **Remove an Existing Exercise** from the specified day.  
  **Alias**: `/removeExercise` – Either `/xe` or `/removeExercise` can be used to delete an exercise.

Example: `prog edit /p 1 /d 1 /x 1`

Delete the exercise at index 1 in Day 1 of Programme 1.

```plaintext
Deleted exercise:
Push-Up: 3 sets of 15 at 30 | Burnt 100 cals
```

---

### 10. Update Existing Exercise in Programme

Update an exercise in an existing day of an existing programme.
**NOTE:** The exercise to be updated exist in an **EXISTING** day.

Command: `prog edit [/p PROG_INDEX] /d DAY_INDEX /x EXERCISE_INDEX [args]`

Parameters:
- **/p PROG_INDEX** _(optional)_: Specifies the Programme index. Defaults to the current active Programme if omitted.  
  **Alias**: `/programme` – Either `/p` or `/programme` can be used to specify the programme index.
- **/d DAY_INDEX**: Index of day the user wants to edit an exercise from.
- **/u EXERCISE_INDEX** : EXERCISE_INDEX is the index of exercise the user wants to edit.  
  **Alias**: `/updateExercise` – Either `/ue` or `/updateExercise` can be used to update an exercise.

This Edit Command must have at least 1 of the arguments below.
- **args**: These arguments set specific attributes for edit exercise actions:
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

Example: `prog edit /p 1 /d 1 /u 1 /w 30 /r 12`

Update Exercise 1 on Day 1 of Programme 1 by setting the weight to 30 and reps to 12.

```plaintext
Updated exercise: Push-Up: 3 sets of 15 at 30 | Burnt 100 cals
```
---

### 11. Log Workout

This feature records the successful completion of a workout for the specified day within the chosen programme.

**Command**: `prog log [/p PROG_INDEX] /d DAY_INDEX [/t DATE]`

If `PROG_INDEX` is not specified, the command defaults to deleting the currently active programme.
If `DATE` is not specified, the command defaults to the current date at the time of logging.

**Example**: `prog log /p 2 /d 1 /t 07-11-2024`

```
==================================================
Congrats! You've successfully completed:
Monday
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 15 at 50kg | Burnt 200 cals
==================================================
```

---

### 12. Add a Meal

This feature adds a meal to the daily record of the specific date.

**Command**: `meal add /n MEAL_NAME /c CALORIES [/t DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of adding.

**Example**: `meal add /n Chicken_Breast /c 250 /t 30-10-2024`

```
==================================================
Chicken_Breast | 250kcal has been added
==================================================
```

---

### 13. View Meals

This feature displays all meals recorded for the specific date.

**Command**: `meal view [DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of viewing.

**Example**: `meal view 30-10-2024`

```
==================================================
Meals for 30-10-2024: 

1: Chicken_Breast | 250kcal
2: Scrambled Eggs | 150kcal
==================================================
```

---

### 14. Delete a Meal

This feature deletes the meal at the specified index from the daily record of the specific date.

**Command**: `meal delete /m MEAL_INDEX [/t DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of deleting.

**Example**: `meal delete /m 1 /t 30-10-2024`

```
==================================================
Chicken_Breast | 250kcal has been deleted
==================================================
```

---

### 15. Add a Water Log

This feature adds a water log to the daily record of the specific date.

Command: `water add /v WATER_VOLUME [/t DATE]`

`WATER_VOLUME` is stored as a floating point number.
If `DATE` is not specified, the command defaults to the current date at the time of adding.

**Example**: `water add /v 200.2 /t 30-10-2024`

```
==================================================
200.2 liters of water has been added
==================================================
```

---

### 16. View Water Logs

This feature displays all water logs recorded for the specific date.

**Command**: `water view [DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of viewing.

**Example**: `water view 30-10-2024`

```
==================================================
Water intake for 30-10-2024: 

1: 200.2
==================================================
```

---

### 17. Delete a Water Log

This feature deletes the water log at the specified index from the daily record of the specific date.

**Command**: `water delete /w WATER_INDEX [/t DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of deleting.

**Example**: `water delete /w 1 /t 30-10-2024`

```
==================================================
200.2 liters of water has been deleted
==================================================
```

---

### 18. View All History

This feature displays a comprehensive record of workouts, meals, and water intake for each logged day.

**Command**: `history list`

**Example**: `history list`

```
==================================================
Completed On: 07-11-2024

Day: 
Monday
1. Bench_Press: 3 sets of 15 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 15 at 50kg | Burnt 200 cals

Total Calories burnt: 400 kcal

Meals: 
No Meals.

Water Intake: 
1: 500.3
Total Water Intake: 500.3 liters 

Caloric Balance: -400 kcal

==============

Completed On: 11-11-2024

Day: 
Wednesday
1. Bicep_Curl: 3 sets of 10 at 10kg | Burnt 100 cals

Total Calories burnt: 100 kcal

Meals: 
1: Chicken_Breast | 250kcal
2: Scrambled_Eggs | 150kcal
Total Calories from Meals: 400 kcal

Water Intake: 
1: 200.2
Total Water Intake: 200.2 liters 

Caloric Balance: 300 kcal
==================================================
```

---

### 19. View Specific Record

This feature displays the recorded information for the specified day.

**Command**: `history view [DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of viewing.

**Example**: `history view 11-11-2024` 

```
==================================================
Day: 
Wednesday
1. Bicep_Curl: 3 sets of 10 at 10kg | Burnt 100 cals

Total Calories burnt: 100 kcal

Meals: 
1: Chicken_Breast | 250kcal
2: Scrambled_Eggs | 150kcal
Total Calories from Meals: 400 kcal

Water Intake: 
1: 200.2
Total Water Intake: 200.2 liters 

Caloric Balance: 300 kcal
==================================================
```

---

### 20. View Weekly Summary

This feature displays a summary of workouts, meals, and water intake for the past week.

**Command**: `history wk`

**Example**: `history wk`

```
==================================================
Your weekly workout summary: 
Monday
1. Bench_Press: 3 sets of 15 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 15 at 50kg | Burnt 200 cals
Completed On: 07-11-2024

Wednesday
1. Bicep_Curl: 3 sets of 10 at 10kg | Burnt 100 cals
Completed On: 11-11-2024
==================================================
```

---

### 21. View PB for exercise

This feature displays the personal best for the specified exercise.

**Command**: `history pb EXERICSE_NAME`

_Note_: `EXERICSE_NAME` is not case-sensitive.

**Example**: `history pb bench_press`

```
==================================================
Personal best for bench_press: 3 sets of 15 at 30kg
==================================================
```

---

### 22. View PBs for exercises 

This feature displays personal bests for all the exercises.

**Command**: `history pb`

**Example**: `history pb`

```
==================================================
Personal bests for all exercises:
Bench_Press: 3 sets of 15 at 30kg
Squat: 3 sets of 15 at 50kg
Bicep_Curl: 3 sets of 10 at 10kg
==================================================
```

---

### 23. Delete Record 

This feature deletes the record at the specified date.

**Command**: `history delete [DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of deleting.

**Example**: `history delete 11-11-2024`

```
==================================================
Deleted record: 
Day: 
Wednesday
1. Bicep_Curl: 3 sets of 10 at 10kg | Burnt 100 cals

Total Calories burnt: 100 kcal

Meals: 
1: Chicken_Breast | 250kcal
2: Scrambled_Eggs | 150kcal
Total Calories from Meals: 400 kcal

Water Intake: 
1: 200.2
Total Water Intake: 200.2 liters 

Caloric Balance: 300 kcal
==================================================
```

### Exiting BuffBuddy

This feature exits and closes the programme.

**Command**: `bye`

**Example**: `bye`

```
==================================================
Exiting BuffBuddy...
==================================================
Bye. Hope to see you again soon!
```
___


## Command Summary

| Command                                     | Description                                                                               | Format                                                                                             | Example                                                                |
|---------------------------------------------|-------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| **Add Programme**                           | Creates a new workout Programme                                                           | `prog create PROG_NAME /d DAY_NAME /e /n EXERCISE_NAME /s SETS /r REPS /w WEIGHT /c CALORIES`      | `prog create Starter /d ONE /e /n Bench_Press /s 3 /r 12 /w 30 /c 100` |
| **Set Active**                              | Sets a Programme as the active one                                                        | `prog start PROG_INDEX`                                                                            | `prog start 1`                                                         |
| **List Programmes**                         | Lists all workout Programmes with their index and name                                    | `prog list`                                                                                        | `prog list`                                                            |
| **View Programme**                          | Displays the detailed workout routine of a specific Programme                             | `prog view PROG_INDEX`                                                                             | `prog view 1`                                                          |
| **Delete Programme**                        | Deletes a Programme by its index                                                          | `prog delete PROG_INDEX`                                                                           | `prog delete 1`                                                        |
| **Log Workout**                             | Logs a workout for a specific day                                                         | `prog log /p PROG_INDEX /d DAY_INDEX /t DATE`                                                      | `prog log /p 1 /d 1 /t 12-10-2024`                                     |
| **Add a New Day to an Existing Programme**  | Add a new Day to an existing programme                                                    | `prog edit [/p PROG_INDEX] /ad DAY_NAME`                                                           | `prog edit /p 1 /ad "Cardio Day"`                                      |
| **Delete a Day from an Existing Programme** | Delete a day from an existing programme.                                                  | `prog edit [/p PORG_INDEX] /xd DAY_INDEX`                                                          | `prog edit /p 1 /xd 1"`                                                |
| **Add a New Exercise in a Programme**       | Add an exercise to an existing day in an existing programme                               | `prog edit [/p PORG_INDEX] /d DAY_INDEX /a /n EXERCISE_NAME /w WEIGHT /r REPS /s SETS /c CALORIES` | `prog edit /p 1 /d 1 /a /n Push-Up /w 30 /r 15 /s 3 /c 100`            |
| **Delete Exercise in a Programme**          | Delete an exercise from an existing day in an existing programme                          | `prog edit [/p PORG_INDEX] /d DAY_INDEX /x EXERCISE_INDEX`                                         | `prog edit /p 1 /d 1 /x 1`                                             |
| **Update Existing Exercise in Programme**   | Update an exercise in an existing day of an existing programme                            | `prog edit [/p PORG_INDEX] /d DAY_INDEX /x EXERCISE_INDEX [args]`                                  | `prog edit /p 1 /d 1 /u 1 /w 30 /r 12`                                 |
| **Add Meal**                                | Adds a meal to a daily record                                                             | `meal add /n MEAL_NAME /c CALORIES /t DATE`                                                        | `meal add /n Chicken_Breast /c 250 /t 30-10-2024`                      |
| **View Meals**                              | Displays all meals for a specific date                                                    | `meal view /t DATE`                                                                                | `meal view 30-10-2024`                                                 |
| **Delete Meal**                             | Deletes a meal from a daily record                                                        | `meal delete /m MEAL_INDEX /t DATE`                                                                | `meal delete /m 1 /t 30-10-2024`                                       |
| **Add Water**                               | Adds a water to a daily record                                                            | `water add /n MEAL_NAME /c CALORIES /t DATE`                                                       | `water add /v 200.2 /t 30-10-2024`                                     |
| **View Water**                              | Displays all water for a specific date                                                    | `water view /t DATE`                                                                               | `water view 30-10-2024`                                                |
| **Delete Water**                            | Deletes a water from a daily record                                                       | `water delete /m MEAL_INDEX /t DATE`                                                               | `water delete /w 1 /t 30-10-2024`                                      |
| **List History**                            | Displays a comprehensive record of workouts, meals, and water intake for each logged day. | `history list`                                                                                     | `history list`                                                         |
| **View History**                            | Displays a given daily record for a specific date                                         | `history view [DATE]`                                                                              | `history view 30-10-2024`                                              |                                                 |
| **View Weekly Summary**                     | Displays a summary of workouts, meals, and water intake for the past week                 | `history wk`                                                                                       | `history wk`                                                           |
| **View PB for exercise**                    | Displays Personal Best for specified exercise                                             | `history pb <exercise_name>`                                                                       | `history pb squat`                                                     |
| **View PBs for exercises**                  | Displays Personal Bests for all exercises                                                 | `history pb`                                                                                       | `history pb`                                                           |
| **Delete Record**                           | Delete a daily record for  a given day                                                    | `history delete [DATE]`                                                                            | `histroy delete 30-10-2024`                                            |