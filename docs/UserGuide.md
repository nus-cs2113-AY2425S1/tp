<!-- @@author nirala-ts -->

# User Guide

## Table of Contents

1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
   - [1. Add New Programme](#1-add-new-programme)
   - [2. Set Programme as Active](#2-set-programme-as-active)
   - [3. List All Programmes](#3-list-all-programmes)
   - [4. View Programme](#4-view-programme)
   - [5. Delete Programme](#5-delete-programme)
   - [6. Add Day to Programme](#6-add-day-to-programme)
   - [7. Delete Day from Programme](#7-delete-day-from-programme)
   - [8. Add Exercise in Programme](#8-add-exercise-in-programme)
   - [9. Delete Exercise from Programme](#9-delete-exercise-from-programme)
   - [10. Update Exercise in Programme](#10-update-exercise-in-programme)
   - [11. Log Workout](#11-log-workout)
   - [12. Add New Meal](#12-add-new-meal)
   - [13. View Meals](#13-view-meals)
   - [14. Delete Meal](#14-delete-meal)
   - [15. Add New Water Log](#15-add-new-water-log)
   - [16. View Water Logs](#16-view-water-logs)
   - [17. Delete Water Log](#17-delete-water-log)
   - [18. View History](#18-view-history)
   - [19. View Specific Record](#19-view-specific-record)
   - [20. View Weekly Summary](#20-view-weekly-summary)
   - [21. View PB for an Exercise](#21-view-pb-for-an-exercise)
   - [22. View PBs for All Exercises](#22-view-pbs-for-all-exercises-)
   - [23. Delete Record](#23-delete-record-)
   - [24. Exiting BuffBuddy](#24-exit-buffbuddy)
4. [Data Storage](#data-storage)
5. [FAQ](#FAQ)
6. [Alias Table](#alias-table)
7. [Command Summary](#command-summary)

## Introduction

BuffBuddy is your all-in-one fitness tracking companion, designed to help you streamline and organize your workout routines.
Whether you’re planning custom programmes, tracking daily exercises, or logging your workout history, BuffBuddy keeps everything in one place.
Build personalized workout plans, log progress, and stay motivated with an intuitive interface that’s perfect for fitness enthusiasts of all levels.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `BuffBuddy` from [here](https://github.com/AY2425S1-CS2113-W10-3/tp/releases/tag/v1.0).
3. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar BuffBuddy.jar` command to run the application.

4. Open a command terminal
5. Navigate into the folder you put the jar file in
6. Use the `java -jar BuffBuddy.jar` command to run the application.

<!-- @@author nirala-ts -->

## Features

> ### Notes on Command format
>
> Text written in `SCREAMING_SNAKE_CASE` are command parameters to be supplied by the user.
>
> Text preceded with a `/` will be read as flags. `/` is a reserved character and is not to be used as part of a parameter. e.g. `meal add /n Choc/Pie /c 200` will throw an invalid flag error.
>
> Square brackets `[...]` indicate optional parameters. e.g. `history view` and `history view 11-11-2024` are both valid.
>
> Flagged parameters can be supplied in any order. e.g. `meal add /n Pie /c 200` is equivalent to `meal add /c 200 /n Pie`
>
> Parameters can include spaces. e.g. `meal add /n Chicken Rice /c 200` is a valid command.
>
> All flags have aliases. Refer to the [alias table](#Alias-Table) to see the alternative options available for each flag.
>
> For date parameters, dates should be supplied in the `dd-MM-yyyy` format. e.g. `11-11-2024`


> ### Terminology
>
> **Exercise**: A weighted exercise defined by a name, number of reps and sets, weight and average calories burned.
>
> **Day**: A ‘workout day’ is a collection of exercises to be done together.
>
> **Programme**: A programme is a collection of workout days.
>
> **Daily Record**: A daily record contains a user's workout activity, food intake and water intake for any given day.


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

**Example**: `prog create Advanced Starter /d Monday /e /n Bench Press /s 3 /r 15 /w 30 /c 200 /e /n Squat /s 3 /r 15 /w 50 /c 200 /d Wednesday /e /n Bicep Curl /s 3 /r 10 /w 10 /c 100`

```
==================================================
New programme created:
Advanced Starter

Day 1: Monday
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 kcal
2. Squat: 3 sets of 15 at 50kg | Burnt 200 kcal

Day 2: Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 kcal
==================================================
```

_Note_: If the programme list was empty, the new programme added would be set to active by default.

---

### 2. Set Programme as Active

This feature sets the specified programme as the "active programme".  
Once a programme is active, other commands will default to this programme if `PROG_INDEX` is not provided for those commands.
**Note:** If the active programme is deleted, it will reset to the first programme (if exist).

**Command**: `prog start [PROG_INDEX]`

**Example**: `prog start 2`

```
==================================================
Started programme:
Advanced Starter

Day 1: Monday
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 kcal
2. Squat: 3 sets of 15 at 50kg | Burnt 200 kcal

Day 2: Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 kcal
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
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 kcal
2. Squat: 3 sets of 15 at 50kg | Burnt 200 kcal

Day 2: Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 kcal
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
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 kcal
2. Squat: 3 sets of 15 at 50kg | Burnt 200 kcal

Day 2: Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 kcal
==================================================
```

---

<!-- @@author TVageesan -->

### 6. Add Day to Programme

This feature adds a new day to the specified existing programme.

**Command**: `prog edit [/p PROG_INDEX] /ad DAY_NAME`

If `PROG_INDEX` is not specified, the command defaults to editing the current active programme.

**Example**: `prog edit /p 1 /ad Cardio`

```
==================================================
Created new day: Cardio
==================================================
```

_Note_: Advanced users can directly create a day with multiple exercises and add it to to an existing programme using the following command:

**Command**: `prog edit [/p PROG_INDEX] /ad DAY_NAME /e /n EXERCISE_NAME /s SETS /r REPS /w WEIGHT /c CALORIES /e ...`

**Example**: `prog edit /p 1 /ad Cardio /e /n Dumbbell squat /w 10 /r 15 /s 10 /c 100 /e /n Kettlebell swing /w 10 /r 15 /s 10 /c 100`

```
==================================================
Created new day: Cardio
1. Dumbbell squat: 10 sets of 15 at 10kg | Burnt 100 kcal
2. Kettlebell swing: 10 sets of 15 at 10kg | Burnt 100 kcal
==================================================
```

---

### 7. Delete Day from Programme

This feature deletes the specified day from the specified existing programme.

**Command**: `prog edit [/p PROG_INDEX] /xd DAY_INDEX`

If `PROG_INDEX` is not specified, the command defaults to editing the current active programme.

**Example**: `prog edit /p 1 /xd 1`

```
==================================================
Deleted day:
Cardio
==================================================
```

---

### 8. Add Exercise in Programme

This feature adds a new exercise to the specified existing day in the specified existing programme.

**Command**: `prog edit [/p PROG_INDEX] /d DAY_INDEX /ae /n EXERCISE_NAME /w WEIGHT /r REPS /s SETS /c CALORIES`

If `PROG_INDEX` is not specified, the command defaults to editing the current active programme.

**Example**: `prog edit /p 1 /d 2 /ae /n Lateral Pulldown /w 30 /r 15 /s 3 /c 100`

```
==================================================
Created new exercise: 
Lateral Pulldown: 3 sets of 15 at 30kg | Burnt 100 kcal
==================================================
```

---

### 9. Delete Exercise from Programme

This feature deletes the specified exercise from the specified existing day in the specified existing programme.

**Command**: `prog edit [/p PROG_INDEX] /d DAY_INDEX /xe EXERCISE_INDEX`

If `PROG_INDEX` is not specified, the command defaults to editing the current active programme.

**Example**: `prog edit /p 1 /d 3 /xe 2`

```
==================================================
Deleted exercise 1: 
Kettlebell swing: 10 sets of 15 at 10kg | Burnt 100 kcal
==================================================
```

---

### 10. Update Exercise in Programme

This feature updates the specified exercise in the specified existing day of the specified existing programme.

**Command**: `prog edit [/p PROG_INDEX] /d DAY_INDEX /ue EXERCISE_INDEX [args]`

If `PROG_INDEX` is not specified, the command defaults to editing the current active programme.

`[args]` must have at least 1 of the arguments below.

- **`/w WEIGHT`**: Sets the weight for the exercise on update.
- **`/r REPS`**: Sets the repetitions for the exercise on update.
- **`/s SETS`**: Sets the number of sets for the exercise on update.
- **`/n NAME`**: Sets the name of the exercise on update.
- **`/c CALORIES`**: Sets the calories of the exercise on update.

**Example**: `prog edit /p 1 /d 2 /ue 1 /w 8 /r 15`

```
==================================================
Updated exercise: Bicep Curl: 3 sets of 15 at 8kg | Burnt 100 kcal
==================================================
```

---

<!-- @@author nirala-ts -->

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
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 kcal
2. Squat: 3 sets of 15 at 50kg | Burnt 200 kcal
==================================================
```

---

<!-- @@author Atulteja -->

### 12. Add New Meal

This feature adds a meal to the daily record of the specific date.

**Command**: `meal add /n MEAL_NAME /c CALORIES [/t DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of adding.

**Example**: `meal add /n Chicken Breast /c 250 /t 30-10-2024`

```
==================================================
Chicken Breast | 250kcal has been added
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

1: Chicken Breast | 250kcal
2: Scrambled Eggs | 150kcal
==================================================
```

---

### 14. Delete Meal

This feature deletes the meal at the specified index from the daily record of the specific date.

**Command**: `meal delete /m MEAL_INDEX [/t DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of deleting.

**Example**: `meal delete /m 1 /t 30-10-2024`

```
==================================================
Chicken Breast | 250kcal has been deleted
==================================================
```

---

<!-- @@author Bev-low -->

### 15. Add New Water Log

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

### 17. Delete Water Log

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

### 18. View History

This feature displays a comprehensive record of workouts, meals, and water intake for each logged day.

**Command**: `history list`

**Example**: `history list`

```
==================================================
Completed On: 07-11-2024

Day:
Monday
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 kcal
2. Squat: 3 sets of 15 at 50kg | Burnt 200 kcal

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
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 kcal

Total Calories burnt: 100 kcal

Meals:
1: Chicken Breast | 250kcal
2: Scrambled Eggs | 150kcal
Total Calories from Meals: 400 kcal

Water Intake:
1: 200.2
Total Water Intake: 200.2 liters

Caloric Balance: 300 kcal
==================================================
```

---

<!-- @@author andreusxcarvalho -->

### 19. View Specific Record

This feature displays the recorded information for the specified day.

**Command**: `history view [DATE]`

If `DATE` is not specified, the command defaults to the current date at the time of viewing.

**Example**: `history view 11-11-2024`

```
==================================================
Day:
Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 kcal

Total Calories burnt: 100 kcal

Meals:
1: Chicken Breast | 250kcal
2: Scrambled Eggs | 150kcal
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
1. Bench Press: 3 sets of 15 at 30kg | Burnt 200 kcal
2. Squat: 3 sets of 15 at 50kg | Burnt 200 kcal
Completed On: 07-11-2024

Wednesday
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 kcal
Completed On: 11-11-2024
==================================================
```

---

### 21. View PB for an Exercise

This feature displays the personal best for the specified exercise.

**Command**: `history pb EXERCISE_NAME`

_Note_: `EXERCISE_NAME` is not case-sensitive.

**Example**: `history pb bench press`

```
==================================================
Personal best for bench press: 3 sets of 15 at 30kg
==================================================
```

---

### 22. View PBs for All Exercises

This feature displays personal bests for all the exercises.

**Command**: `history pb`

**Example**: `history pb`

```
==================================================
Personal bests for all exercises:
Bench Press: 3 sets of 15 at 30kg
Squat: 3 sets of 15 at 50kg
Bicep Curl: 3 sets of 10 at 10kg
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
1. Bicep Curl: 3 sets of 10 at 10kg | Burnt 100 kcal

Total Calories burnt: 100 kcal

Meals:
1: Chicken Breast | 250kcal
2: Scrambled Eggs | 150kcal
Total Calories from Meals: 400 kcal

Water Intake:
1: 200.2
Total Water Intake: 200.2 liters

Caloric Balance: 300 kcal
==================================================
```

<!-- @@author nirala-ts -->

### 24. Exit BuffBuddy

This feature exits and closes the programme.

**Command**: `bye`

**Example**: `bye`

```
==================================================
Exiting BuffBuddy...
==================================================
Bye. Hope to see you again soon!
```

---

___

<!-- @@author Bev-low -->

## Data Storage

BuffBuddy uses a JSON file to store user data, ensuring persistence across sessions.

### Saving your data

- Saving is done automatically after each user command. It does not need to be manually triggered by a command.
- All records, including logged days, meals, and water intake, are saved in a structured format within a designated file (./data/data.json).
- The JSON format is human-readable, allowing users to view their stored data easily if needed.

### Loading your data

- Loading happens automatically when BuffBuddy initializes.
- If the structure of the JSON file has been tampered with (e.g., removing the "programmeList" key or using {} as the entire content), the program will handle this scenario by treating the user as a first-time user and initializing a fresh data file.
- If any data values within the JSON file are found to be invalid (e.g., negative numbers where only positive values are allowed), the specific section containing corrupted data (either the `ProgrammeList` or `History`) will be re-initialized to be empty.

### Editing the data file

- Users can directly edit the data file to easily change their records or import data from another file
- Users should note that they need to first exit BuffBuddy before making their changes. If the data file is edited while actively entering commands into BuffBuddy, the contents of the file will be overwritten.
<!-- @@author nirala-ts -->

---

<!-- @@author nirala-ts -->
## FAQ

1. **How can I back up my data?**

   - BuffBuddy saves data in a JSON file located at `./data/data.json`. You can create a backup by copying this file to
     another location.

2. **What happens if I accidentally delete or corrupt the data file?**

   - If the data file is deleted or corrupted, BuffBuddy will reset your program list and history to prevent data issues.
     However, restoring a backup of the JSON file (if you have one) can also recover your data.

3. **Can I add exercises that don’t involve weights?**

   - BuffBuddy currently only supports weighted exercises. Exercises like jumping jacks or other body weight exercises cannot
     be added without a weight parameter.

4. **What is the caloric balance in the history view?**

   - The caloric balance shows the difference between the calories burned through exercise and the calories consumed through
     meals, helping you monitor your energy intake and expenditure.

5. **What happens if I input invalid values for commands?**
   - BuffBuddy performs basic validation for parameters. Negative values or missing required parameters will prompt an error,
     and the command won’t be executed. Ensure all required fields are filled correctly.

---

## Alias Table

| Flag | Alias             |
|------|-------------------|
| /p   | /prog, /programme |
| /d   | /day              |
| /t   | /date             |
| /n   | /name             |
| /e   | /exercise, /ex    |
| /s   | /set, /sets       |
| /r   | /rep, /reps       |
| /w   | /weight           |
| /c   | /calories         |
| /ae  | /addEx            |
| /ue  | /updateEx         |
| /xe  | /removeEx         |
| /ad  | /addDay           |
| /xd  | /removeDay        |
| /m   | /meal             |
| /w   | /water            |
| /v   | /volume, /vol     |

---

___

## Command Summary

| Command                                   | Format                                                                                              | Example                                                                   |
| ----------------------------------------- | --------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| **Add Programme**                         | `prog create PROG_NAME`                                                                             | `prog create Starter`                                                     |
| **Add Detailed Programme**                | `prog create PROG_NAME /d DAY_NAME /e /n EXERCISE_NAME /s SETS /r REPS /w WEIGHT /c CALORIES`       | `prog create Starter /d Monday /e /n Bench Press /s 3 /r 12 /w 30 /c 100` |
| **Set Active Programme**                  | `prog start [PROG_INDEX]`                                                                           | `prog start 1`                                                            |
| **List Programmes**                       | `prog list`                                                                                         | `prog list`                                                               |
| **View Programme**                        | `prog view [PROG_INDEX]`                                                                            | `prog view 1`                                                             |
| **Delete Programme**                      | `prog delete [PROG_INDEX]`                                                                          | `prog delete 1`                                                           |
| **Add Day to Programme**                  | `prog edit [/p PROG_INDEX] /ad DAY_NAME`                                                            | `prog edit /p 1 /ad Cardio Day`                                           |
| **Delete Day from Programme**             | `prog edit [/p PROG_INDEX] /xd DAY_INDEX`                                                           | `prog edit /p 1 /xd 1`                                                    |
| **Add Exercise to Programme**             | `prog edit [/p PROG_INDEX] /d DAY_INDEX /ae /n EXERCISE_NAME /w WEIGHT /r REPS /s SETS /c CALORIES` | `prog edit /p 1 /d 1 /ae /n Push Up /w 30 /r 15 /s 3 /c 100`              |
| **Delete Exercise from Programme**        | `prog edit [/p PROG_INDEX] /d DAY_INDEX /xe EXERCISE_INDEX`                                         | `prog edit /p 1 /d 1 /xe 1`                                               |
| **Update Exercise in Programme**          | `prog edit [/p PROG_INDEX] /d DAY_INDEX /ue EXERCISE_INDEX [args]`                                  | `prog edit /p 1 /d 1 /ue 1 /w 30 /r 12`                                   |
| **Log Workout**                           | `prog log [/p PROG_INDEX] /d DAY_INDEX [/t DATE]`                                                   | `prog log /p 1 /d 1 /t 12-10-2024`                                        |
| **Add Meal**                              | `meal add /n MEAL_NAME /c CALORIES [/t DATE]`                                                       | `meal add /n Chicken Breast /c 250 /t 30-10-2024`                         |
| **View Meals**                            | `meal view [DATE]`                                                                                  | `meal view 30-10-2024`                                                    |
| **Delete Meal**                           | `meal delete /m MEAL_INDEX [/t DATE]`                                                               | `meal delete /m 1 /t 30-10-2024`                                          |
| **Add Water Log**                         | `water add /v WATER_VOLUME [/t DATE]`                                                               | `water add /v 200.2 /t 30-10-2024`                                        |
| **View Water Logs**                       | `water view [DATE]`                                                                                 | `water view 30-10-2024`                                                   |
| **Delete Water Log**                      | `water delete /w WATER_INDEX [/t DATE]`                                                             | `water delete /w 1 /t 30-10-2024`                                         |
| **View History**                          | `history list`                                                                                      | `history list`                                                            |
| **View Specific Record**                  | `history view [DATE]`                                                                               | `history view 30-10-2024`                                                 |
| **View Weekly Summary**                   | `history wk`                                                                                        | `history wk`                                                              |
| **View Personal Best for an Exercise**    | `history pb EXERCISE_NAME`                                                                          | `history pb bench press`                                                  |
| **View Personal Bests for All Exercises** | `history pb`                                                                                        | `history pb`                                                              |
| **Delete Record**                         | `history delete [DATE]`                                                                             | `history delete 30-10-2024`                                               |
| **Exit BuffBuddy**                        | `bye`                                                                                               | `bye`                                                                     |

---


