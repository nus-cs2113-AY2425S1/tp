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

## Features

### 1. Add New Programme

This feature adds a new empty workout programme with a specified name.

Command: `prog create PROG_NAME`

Example: `prog create Starter`

```
========================================
New programme created:
Starter
========================================
```

_Note_: Advanced users can create a detailed programme with multiple days and exercises in one step by using the following command structure. 
        This allows users to add specific exercises with sets, reps, weight, and calorie details for each day:

Command: `prog create PROG_NAME /d DAY_NAME /e /n EXERCISE_NAME /s SETS /r REPS /w WEIGHT /c CALORIES /e ...`

Example: `prog create Advanced Starter /d Monday /e /n Bench_Press /s 3 /r 15 /w 30 /c 200 /e /n Squat /s 3 /r 15 /w 50 /c 200 /d Wednesday /e /n Bicep_Curl /s 3 /r 10 /w 10 /c 100`
 
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

---

### 2. Set Programme as Active

This feature sets the specified programme as the "active programme".  
Once a programme is active, other commands will default to this programme if `PROG_INDEX` is not provided for those commands.

Command: `prog start PROG_INDEX`

Example: `prog start 2`

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

Command: `prog list`

Example: `prog list`

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

Command: `prog view PROG_INDEX`

If `PROG_INDEX` is not specified, the command defaults to displaying the details of the currently active programme.


Example: `prog view 2`

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

Command: `prog delete PROG_INDEX`

If `PROG_INDEX` is not specified, the command defaults to deleting the currently active programme.


Example: `prog delete 2`

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

### 6. Log Workout

This feature records the successful completion of a workout for the specified day within the chosen programme.

Command: `prog log /p PROG_INDEX /d DAY_INDEX /t DATE`

If `PROG_INDEX` is not specified, the command defaults to deleting the currently active programme.
If `DATE` is not specified, the command defaults to the current date at the time of logging.
_Note_: `DATE`: Specifies the date (in `dd-MM-yyyy` format) when the workout was completed.


Example: `prog log /p 2 /d 1 /t 07-11-2024`

```
==================================================
Congrats! You've successfully completed:
Monday
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 15 at 50kg | Burnt 200 cals
==================================================
```

---

### 7. Add a New Day to an Existing Programme

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

### 8. Delete a Day from an Existing Programme

Delete a day from an existing programme.

Command: `prog edit [/p PORG_INDEX] /xd DAY_INDEX`

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

### 9. Add a New Exercise in a Programme

Add an exercise to an existing day in an existing programme.
**NOTE:** The exercise is added to an **EXISTING** day.

Command: `prog edit [/p PORG_INDEX] /d DAY_INDEX /a /n EXERCISE_NAME /w WEIGHT /r REPS /s SETS /c CALORIES`

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

### 10. Delete Exercise in a Programme

Delete an exercise from an existing day in an existing programme.
**NOTE:** The exercise is deleted from an **EXISTING** day.

Command: `prog edit [/p PORG_INDEX] /d DAY_INDEX /x EXERCISE_INDEX`

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

### 11. Update Existing Exercise in Programme

Update an exercise in an existing day of an existing programme.
**NOTE:** The exercise to be updated exist in an **EXISTING** day.

Command: `prog edit [/p PORG_INDEX] /d DAY_INDEX /x EXERCISE_INDEX [args]`

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

### 12. Add a Meal

Adds a meal to the daily record of a specific date.

Command: `meal add /n MEAL_NAME /c CALORIES /t DATE`

Parameters:

- `/n MEAL_NAME`: Name of the meal.
- `/c CALORIES`: Number of calories in the meal.
- `/t DATE`: Date in the format `dd-MM-yyyy`.

**Example**: `meal add /n Chicken_Breast /c 250 /t 30-10-2024`

```
Chicken Breast | 250kcal has been added to 30-10-2024.
```

---

### 13. View Meals

Displays all meals recorded for a specific date.

Command: `meal view DATE`

Parameters:

- `DATE`: Date in the format `dd-MM-yyyy`.

**Example**: `meal view 30-10-2024`

```
Meals for 30-10-2024:

1. Chicken Breast | 250 kcal
2. Scrambled Eggs | 150 kcal
```

---

### 14. Delete a Meal

Deletes a meal from the daily record of a specific date.

Command: `meal delete /m MEAL_INDEX /t DATE`

Parameters:

- `/m MEAL_INDEX`: Index of the meal to delete.
- `/t DATE`: Date in the format `dd-MM-yyyy`.

**Example**: `meal delete /m 1 /t 30-10-2024`

```
Chicken Breast | 250kcal has been deleted from 30-10-2024
```

---

### 15. Add a Water Log

Adds a water log to the daily record of a specific date.

Command: `water add /v WATER_VOLUME /t DATE`

Parameters:

- `/v WATER_VOLUME `: Volume of Water. (floating number)
- `/t DATE`: Date in the format `dd-MM-yyyy`.

**Example**: `water add /v 200.2 /t 30-10-2024`

```
200.2 liters of water has been added
```

---

### 16. View Water Logs

Displays all water logs recorded for a specific date.

Command: `water view DATE`

Parameters:

- `DATE`: Date in the format `dd-MM-yyyy`.

**Example**: `water view 30-10-2024`

```
Water intake for 30-10-2024:

1: 100.0
2. 200.2
```

---

### 17. Delete a Water Log

Deletes a water log from the daily record of a specific date.

Command: `water delete /w WATER_INDEX /t DATE`

Parameters:

- `/w WATER_INDEX`: Index of the water to delete.
- `/t DATE`: Date in the format `dd-MM-yyyy`.

**Example**: `water delete /w 1 /t 30-10-2024`

```
100.0 liters of water has been deleted
```

---

### 18. View All History

Displays a comprehensive record of workouts, meals, and water intake for each logged day.

Command: `history list`

**Example**: `history list`

```
Completed On: 12-12-2024

Day: 
ONE
1. Bench Press: 3 sets of 12 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 12 at 50kg | Burnt 200 cals

Total Calories burnt: 400 kcal

Meals: 
1: pasta | 560kcal
Total Calories from Meals: 560 kcal

Water Intake: 
1: 300.0
Total Water Intake: 300.0 liters 

Caloric Balance: 160 kcal

==============

Completed On: 06-11-2024

Day: 
ONE
1. Bench Press: 3 sets of 12 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 12 at 50kg | Burnt 200 cals

Total Calories burnt: 400 kcal

Meals: 
No Meals.

Water Intake: 
No Water.

Caloric Balance: -400 kcal

```

---

### 19. View Specific Record
Displays the recorded information for a specified day.

Command: `history view [DATE]`
- **[DATE]:** Date is in format dd-MM-yyyy

If `DATE` is not provided, command will default to showing the record for the current date.

**Example**: `history view 30-10-2024` 
```
Day:
ONE
1. Bench Press: 3 sets of 12 at 30 | Burnt 220 cals
2. Squat: 3 sets of 12 at 50 | Burnt 300 cals

Total Calories burnt: 520 kcal

Meals:
1: pasta | 100kcal
Total Calories from Meals: 100 kcal

Water Intake:
1: 100.0
Total Water Intake: 100.0 liters

Caloric Balance: -420 kcal
```

---

### 20. View Weekly Summary

Displays a summary of workouts, meals, and water intake for the past week

Command: `history wk`

**Example**: `history wk`

```
Your weekly workout summary: 
Empty Day
Completed On: 30-10-2024

ONE
1. Bench Press: 3 sets of 12 at 30 | Burnt 200 cals
2. Squat: 3 sets of 12 at 50 | Burnt 200 cals
Completed On: 02-11-2024

```

---

### 21. View PB for exercise

Displays Personal Best for specified exercise

Command: `history pb EXERICSE_NAME`

**Note:** Must use underscores ('_') to connect words in Exercise_Name if it consists of multiple words. 

**Example**: `history pb bench_press`

```
Personal best for bench press: sets of 12 at 30
```

---

### 22. View PBs for exercises 

Displays Personal Bests for all exercises

Command: `history pb`

**Example**: `history pb`

```
Personal bests for all exercises:
Bench Press: 3 sets of 12 at 30 
Squat: 3 sets of 12 at 50 
Bicep Curl:  3 sets of 12 at 10 
```

---

### 23. Delete Record 

Delete a record at a specific date.

Command: `history delete [DATE]`
If `DATE` is not provided, default to the current date.

**Example**: `history delete 30-10-24`
```
Deleted Record:
Day: 
ONE
1. Bench Press: 3 sets of 12 at 30kg | Burnt 200 cals
2. Squat: 3 sets of 12 at 50kg | Burnt 200 cals

Total Calories burnt: 400 kcal

Meals: 
1: pasta | 560kcal
Total Calories from Meals: 560 kcal

Water Intake: 
1: 300.0
Total Water Intake: 300.0 liters 

Caloric Balance: 160 kcal
```

___


## Command Summary

| Command                                     | Description                                                                               | Format                                                                                           | Example                                                              |
|---------------------------------------------|-------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|
| **Add Programme**                           | Creates a new workout Programme                                                           | `prog create PROG_NAME /d DAY_NAME /e /n EXERCISE_NAME /s SETS /r REPS /w WEIGHT /c CALORIES`    | `prog create Starter /d ONE /e /n Bench_Press /s 3 /r 12 /w 30 /c 100` |
| **Set Active**                              | Sets a Programme as the active one                                                        | `prog start PROG_INDEX`                                                                          | `prog start 1`                                                       |
| **List Programmes**                         | Lists all workout Programmes with their index and name                                    | `prog list`                                                                                      | `prog list`                                                          |
| **View Programme**                          | Displays the detailed workout routine of a specific Programme                             | `prog view PROG_INDEX`                                                                           | `prog view 1`                                                        |
| **Delete Programme**                        | Deletes a Programme by its index                                                          | `prog delete PROG_INDEX`                                                                         | `prog delete 1`                                                      |
| **Log Workout**                             | Logs a workout for a specific day                                                         | `prog log /p PROG_INDEX /d DAY_INDEX /t DATE`                                                    | `log /p 1 /d 1 /t 12-10-2024`                                        |
| **Add a New Day to an Existing Programme**  | Add a new Day to an existing programme                                                    | `prog edit [/p PROG_INDEX] /ad DAY_NAME`                                                         | `prog edit /p 1 /ad "Cardio Day"`                                    |
| **Delete a Day from an Existing Programme** | Delete a day from an existing programme.                                                  | `prog edit [/p PORG_INDEX] /xd DAY_INDEX`                                                        | `prog edit /p 1 /xd 1"`                                              |
| **Add a New Exercise in a Programme**       | Add an exercise to an existing day in an existing programme                               | `prog edit [/p PORG_INDEX] /d DAY_INDEX /a /n EXERCISE_NAME /w WEIGHT /r REPS /s SETS /c CALORIES` | `prog edit /p 1 /d 1 /a /n Push-Up /w 30 /r 15 /s 3 /c 100`          |
| **Delete Exercise in a Programme**          | Delete an exercise from an existing day in an existing programme                          | `prog edit [/p PORG_INDEX] /d DAY_INDEX /x EXERCISE_INDEX`                                       | `prog edit /p 1 /d 1 /x 1`                                           |
| **Update Existing Exercise in Programme**   | Update an exercise in an existing day of an existing programme                            | `prog edit [/p PORG_INDEX] /d DAY_INDEX /x EXERCISE_INDEX [args]`                                | `prog edit /p 1 /d 1 /u 1 /w 30 /r 12`                               |
| **Add Meal**                                | Adds a meal to a daily record                                                             | `meal add /n MEAL_NAME /c CALORIES /t DATE`                                                      | `meal add /n Chicken_Breast /c 250 /t 30-10-2024`                    |
| **View Meals**                              | Displays all meals for a specific date                                                    | `meal view /t DATE`                                                                              | `meal view 30-10-2024`                                               |
| **Delete Meal**                             | Deletes a meal from a daily record                                                        | `meal delete /m MEAL_INDEX /t DATE`                                                              | `meal delete /m 1 /t 30-10-2024`                                     |
| **Add Water**                               | Adds a water to a daily record                                                            | `water add /n MEAL_NAME /c CALORIES /t DATE`                                                     | `water add /v 200.2 /t 30-10-2024`                                   |
| **View Water**                              | Displays all water for a specific date                                                    | `water view /t DATE`                                                                             | `water view 30-10-2024`                                              |
| **Delete Water**                            | Deletes a water from a daily record                                                       | `water delete /m MEAL_INDEX /t DATE`                                                             | `water delete /w 1 /t 30-10-2024`                                    |
| **List History**                            | Displays a comprehensive record of workouts, meals, and water intake for each logged day. | `history list`                                                                                   | `history list`                                                       |
| **View History**                            | Displays a given daily record for a specific date | `history view [DATE]`                                                                            | `history view 30-10-2024`                                            |                                                 |
| **View Weekly Summary**                     | Displays a summary of workouts, meals, and water intake for the past week                 | `history wk`                                                                                | `history wk`                                                 |
| **View PB for exercise**                    | Displays Personal Best for specified exercise                                             | `history pb <exercise_name>`                                                                     | `history pb squat`                                                   |
| **View PBs for exercises**                  | Displays Personal Bests for all exercises                                                 | `history pb`                                                                                     | `history pb`                                                         |
| **Delete Record**                           | Delete a daily record for  a given day                                                    | `history delete [DATE]`                                                                          | `histroy delete 30-10-2024`                                          |