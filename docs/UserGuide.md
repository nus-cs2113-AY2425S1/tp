# TutorLink

## Introduction

**TutorLink** is a streamlined, offline application developed for professors at NUS who manage single-staffed classes. It is designed to simplify the management of class assignments, and other administrative tasks, allowing professors to focus on teaching and curriculum development. By automating repetitive tasks and providing quick access to essential information, TutorLink saves valuable time and reduces the burden of administrative work.

## Table of Contents

- [Quick Start](#quick-start)
- [Important Notes on Commands](#important-notes-on-commands)
- [Important Notes for Grade Calculation](#important-notes-for-grade-calculation)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a Student: `add_student`](#adding-a-student-add_student)
  - [Deleting a Student: `delete_student`](#deleting-a-student-delete_student)
  - [Listing All Students: `list_student`](#listing-all-students-list_student)
  - [Finding a Student: `find_student`](#finding-a-student-find_student)
  - [Adding a Component: `add_component`](#adding-a-component-add_component)
  - [Deleting a Component: `delete_component`](#deleting-a-component-delete_component)
  - [Listing Components: `list_component`](#listing-components-list_component)
  - [Adding a Grade of a Component for a Student: `add_grade`](#adding-a-grade-of-a-component-for-a-student-add_grade)
  - [Deleting a Grade of a Component for a Student: `delete_grade`](#deleting-a-grade-of-a-component-for-a-student-delete_grade)
  - [Listing Grades: `list_grade`](#listing-grades-list_grade)
  - [Exiting the Program: `bye`](#exiting-the-program-bye)
  - [Saving the Data](#saving-the-data)
- [FAQ](#faq)
- [Command Summary](#command-summary)

<div style="page-break-before:always">&nbsp;</div>
<p></p>

## Quick Start

1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest .jar file  of `TutorLink` from [here](https://github.com/AY2425S1-CS2113-W13-4/tp/releases/tag/v2.1).
3. Copy the file to the folder you want to use as the home folder for your TutorLink.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar TutorLink.jar command to run the application.

Your command terminal should look similar to the one below.

![tutorlink_startup.png](tutorlink_startup.png)

## Important Notes on Commands:
When inputting commands into `TutorLink`, kindly take note of the following:
- Commands with duplicate parameters will be rejected. *i.e* `add_student n/John Doe n/John Doe i/A1234567X`
- Parameters must be separated by at least one space character, otherwise the entire continuous string following a prefix
will be considered a single parameter. *i.e* `add_student i/A1234567X n/John Doei/A1234567X` will be intepreted as adding
a student with the name of `John Doei/A1234567X` and matric number `A1234567X`.
- Parameters must be passed **directly** after the corresponding prefix *i.e* `i/MATRIC_NUMBER`. 
Rouge spaces in between the prefix *i.e* `i/ MATRIC_NUMBER` will invalidate the command and be treated as a `null parameter`.
- Parameters can be supplied in any order. *i.e* `add_student n/John i/A1234567X` is the same as `add_student i/A1234567X n/John`
- **IMPORTANT**: Descriptions should **NOT** contain any separator tokens: `|` as this character is used for storage). 
Including these may yield unpredictable results with the `Storage` component. 
- Matric Number (`i/` argument) is case-sensitive. Therefore, only `A1234567X` is the accepted and not `a1234567x`. Matric numbers 
will be remain in uppercase for storage.
- In general, unless otherwise specified, parameters are case-insensitive. *I.e*, `test` will match against `TEST` and `Test`.

## Important Notes for Grade Calculation:
1. Total Weight (sum) of `Components` **cannot** exceed `100%`. An error will be displayed should you attempt to add a component 
that causes the overall weight to exceed `100%`. *For illustration, consider the following scenario:*

```angular2html
-------------------------------------------------------------
list_component
-------------------------   Result   -------------------------
	1: Quiz 2 (maxScore: 20.0, weight: 30%)
	2: Quiz 4 (maxScore: 20.0, weight: 25%)
-------------------------------------------------------------
add_component c/Quiz 1 w/60 m/10
-------------------------   Error   -------------------------
Error! Total weighting must not exceed 100%.
Current weighting (after addition): 115%
-------------------------------------------------------------
```

In this case, an error was thrown because total `component weighting` exceeded 100%
2. Total Weight of Components **do not need to sum to 100** for grade computation to be meaningful (see below). 
3. `percentage_score` computation follows the following equation: `sum(grade score [of student]/max component score * component_weight) / total component weight`
To illustrate: 

```angular2html
list_component
-------------------------   Result   -------------------------
1: Quiz 2 (maxScore: 20.0, weight: 30%)
2: Quiz 4 (maxScore: 20.0, weight: 25%)
-------------------------------------------------------------
list_grade
-------------------------   Result   -------------------------
List of All Grades:

1: Ethan Chua (A0276007H):
   1. Quiz 2         : 15.00
   2. Quiz 4         : 10.00
   Final Percentage Score: 63.64%


-------------------------------------------------------------
```
The computation of the final score is as follows:
`(15/20*30 [Quiz 2] + 10/20*25 [Quiz 1])/(30 + 25 [Total Weighting]) = 63.64% (2d.p)`

## Features

### Viewing help: `help`

Shows a message explaining different features of the app.

- **Format**: `help`

---

### Adding a Student: `add_student`

Adds a student to your class.

- **Format**: `add_student i/MATRIC_NUMBER n/STUDENT_NAME`
- **Parameters**:
    - `STUDENT_NAME`: The full name of the student.
  - `MATRIC_NUMBER`: The unique identifier of the student. It should start with "A", followed by 7 digits, and end with an uppercase letter (e.g., A1234567X)

- **Example**: `add_student i/A1234567X n/John Doe ` adds a new student named John Doe with the matric number of A1234567X to the class.
  
---

### Deleting a Student: `delete_student`

Removes a student from the class. Note that a student can only be deleted using his/her matric number to prevent identification errors.

- **Format**: `delete_student i/MATRIC_NUMBER`
- **Parameters**:
  - `MATRIC_NUMBER`: The unique identifier of the student. It should start with "A", followed by 7 digits, and end with an uppercase letter (e.g., A1234567X)

- **Example**:
    - `delete_student i/A1234567X` deletes a student with the matric number of A1234567X.
      
---

<div style="page-break-before:always">&nbsp;</div>
<p></p>

### Listing All Students: `list_student`

Displays a list of all students in the class.

- **Format**: `list_student`

- **Example**:
    - `list_student`
      
---

### Finding a Student: `find_student`

Searches for a student and returns a list of matching results, complete with information such as `matric number` and `percentage score`

- **Format**:
  - `find_student i/MATRIC_NUMBER`
  - `find_student n/STUDENT_NAME`
  - **Note**: If both `i/MATRIC_NUMBER` and `n/STUDENT_NAME` are supplied, `n/STUDENT_NAME` is disregarded for the query. 
- **Parameters**:
  - `STUDENT_NAME`: The name of the student.
  - `MATRIC_NUMBER`: The matriculation number of the student. It should start with "A", followed by 7 digits, and end with an uppercase letter (e.g., A1234567X)
  - **Note**  
    - `STUDENT_NAME` is case-sensitive; a search query for `n/John Doe` will not match against `john doe`
    - `STUDENT_NAME` matches substrings; a search query for `n/jo` will match against `john doe`
- **Example**:
  - `find_student i/A1234567X` find the student with the matric number of `A1234567X` among the list of students and prints out the student information.
  - `find_student n/John Doe` find the student with the name `John Doe` among the list of students and prints out the student information.
    
---

### Adding a Component: `add_component`

Adds a new grading component to the class (e.g., "Homework," "Midterm," "Final Exam").

- **Format**: `add_component c/COMPONENT w/WEIGHT m/MAX_SCORE`
- **Parameters**:
    - `COMPONENT`: The name of the grading component to add. Note that when adding, the component name is case-insensitive, 
  *i.e* if `Quiz` exists in the app, then `quiz` cannot be added. 
  Moreover, whitespace after the component string is trimmed.
    - `WEIGHT`: The weight of the component as a percentage, input as an integer from 0 - 100 (inclusive).
    - `MAX_SCORE`: The max_score of the component. **Must be a `double` between 0 and 10,000 (inclusive).**
  *Use case for `WEIGHT = 0`, `MAX_SCORE = 0`:* ungraded components like optional assignments etc

- **Example**:
    - `add_component c/Quiz 1 w/30 m/50` adds a Quiz 1 component with a weightage of 30%, it has a max score of 50 marks.

---

<div style="page-break-before:always">&nbsp;</div>
<p></p>

### Deleting a Component: `delete_component`

Removes an existing grading component from the class.

- **Format**: `delete_component c/COMPONENT`
- **Parameters**:
  - `COMPONENT`: The name of the grading component to delete. Note that component name is case-insensitive, *i.e* `Test` is the same as `test`.
    Moreover, whitespace after the component string is trimmed.
- **Example**:
    - `delete_component c/Quiz 1` deletes Quiz 1 component from the list of components that form the final grade.

---

### Updating Components: `update_component`

Updates an existing component in the class

- **Format**: `update_component c/COMPONENT w/WEIGHT m/MAX_SCORE`
- **Parameters**:
  - `COMPONENT`: The name of the grading component to update. Note that when updating, the component name case-insensitive, 
  *i.e* if there exists a component by the name of `Quiz 2` in the applicaiton, supplying `update_component` with a parameter `c/QUIZ 2` will update it. 
    Moreover, whitespace after the component string is trimmed.
  - `WEIGHT`: The weight of the component as a percentage, input as an integer from 0 - 100 (inclusive).
  - `MAX_SCORE`: The max_score of the component. **Must be a `double` between 0 and 10,000 (inclusive).**
    *Refer to `add_component` documentation for use cases of `WEIGHT=0`, `MAX_SCORE=0`.*

- **Example**:
  - `update_component c/Quiz 1 w/40 m/50` updates component by the name of `Quiz 1` to now bear a weightage of 40% and a max score of 50 marks.
- **Important Note**:
  - `update_component` checks through all the `Grade` objects in the app and if any `Grade` object has a score that is greater than
  the updated max score, the score in the `Grade` object is set to be the newly updated component max score. This is done to 
  prevent the case where a grade has a score that is greater than the max score of the corresponding component. 
  - Although a loss of grade data is inevitable in this case, this is unavoidable as from a practical standpoint, changing 
  the max score of an assignment/exam/quiz **after** it has already been graded is bound to run into this exact problem.

---

### Listing Components: `list_component`

Displays all grading components and their respective weights for a class.

- **Format**: `list_component`

- **Example**:
    - `list_component`

---

### Adding a Grade of a Component for a Student: `add_grade`

Records a grade for a specific student in a particular assignment or exam component.
- **Format**: `add_grade i/MATRIC_NUMBER c/COMPONENT s/SCORE`
- **Parameters**:
  - `MATRIC_NUMBER`: The unique identifier of the student. It should start with "A", followed by 7 digits, and end with an uppercase letter (e.g., A1234567X)
  - `COMPONENT`: The name of the grading component. Note that component name is case-insensitive, *i.e* `Test` is the same as `test`.
    Moreover, whitespace after the component string is trimmed.
  - `SCORE`: The score to be recorded. Must be a numerical value that will be interpreted as a `double`. Note that score cannot exceed the max score of the component.

- **Example**:
  - `add_grade i/A1234567X c/Quiz 1 s/45` adds the grade of Quiz 1 for the student with the matric number of A1234567X with a score of 45.
  - `add_grade i/A1234567X c/Quiz 2 s/30.5` adds the grade of Quiz 2 for the student with the matric number of A1234567X with a score of 30.5.
- **Note:** Scores of grades are stored to double precision but during display the value is rounded off to 2 decimal places.

---

<div style="page-break-before:always">&nbsp;</div>
<p></p>

### Deleting a Grade of a Component for a Student: `delete_grade`

Removes a previously recorded grade for a specific student and component.

- **Format**: `delete_grade i/MATRIC_NUMBER c/COMPONENT`
- **Parameters**:
  - `MATRIC_NUMBER`: The unique identifier of the student. It should start with "A", followed by 7 digits, and end with an uppercase letter (e.g., A1234567X)
  - `COMPONENT`: The name of the grading component. Note that component name is case-insensitive, *i.e* `Test` is the same as `test`.
    Moreover, whitespace after the component string is trimmed.
- **Example**:
  - `delete_grade i/A1234567X c/Quiz 1` deletes the grade of Quiz 1 for the student with the matric number of A1234567X.

---

### Listing Grades: `list_grade`

Views all recorded grades for a specific student or all students, and final percentage calculation. If the weightage of components does not add up to 100% (i.e., the course is still in progress), "IP" (In Progress) will be shown instead of a final percentage.

- **For a Specific Student**:
  - Lists all recorded grades and the final percentage for the specified student. 
  - **Format**: `list_grade i/MATRIC_NUMBER`
  - **Parameters**:
    - `MATRIC_NUMBER`: The unique identifier of the student. It should start with "A", followed by 7 digits, and end with an uppercase letter (e.g., A1234567X)
  - **Example**:
    - `list_grade i/A1234567X`


- **For All Students**:
  - Lists all recorded grades for each student in a numbered format, with individual grade details and a final percentage for each student.
  - **Format**: `list_grade`


---

<div style="page-break-before:always">&nbsp;</div>
<p></p>

### Exiting the program: `bye`

Exits the program.
- **Format**: `bye`

---
### Saving the data

TutorLink data is saved in the hard disk automatically after every command execution. There is no need to save manually.
Existing data from previous application runs are loaded on startup.

The data from the student, component and grade lists are stored in `studentlist.txt`, `componentlist.txt` and `gradelist.txt`
respectively, located in the `[JAR file location]/data/` directory.

---

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: To transfer data, simply copy the `TutorLink` home folder (where the `.jar` file and data files are located) to your new computer. Then, download Java 17 (if not already installed), place the `.jar` file in the copied folder, and run `java -jar TutorLink.jar` from that folder.

**Q**: Can I update data by directly editing the data files?

**A**: Do so at your own risk. If changes to the data file alter its format, invalid file lines will discarded 
during startup, and displayed in the command line for verification. While TutorLink can detect most invalid file entries, 
certain edits can cause unexpected behaviour. Therefore, it is not recommended to edit the data files
unless you are confident you can do so correctly.

---

<div style="page-break-before:always">&nbsp;</div>
<p></p>

## Command Summary

| **Command**        | **Description**                                              | **Example**                           |
|--------------------|--------------------------------------------------------------|---------------------------------------|
| `help`             | Displays list of commands                                    | `help`                                |
| `add_student`      | Adds a student to the class roster                           | `add_student i/A1234567X n/John Doe`  |
| `delete_student`   | Deletes a student from the class roster                      | `delete_student i/A1234567X`          |
| `list_student`     | Lists all students in the class                              | `list_student`                        |
| `find_student`     | Finds a student in the class roster by name or matric number | `find_student i/A1234567X n/John Doe` |
| `add_component`    | Adds a new grading component to the class                    | `add_component c/Quiz 1 w/30 m/50`    |
| `delete_component` | Deletes a grading component from the class                   | `delete_component c/Quiz 1`           |
| `update_component` | Updates a component with a new maxscore or weight            | `update_component c/Quiz 1 w/40 m/60` |
| `list_component`   | Lists all grading components                                 | `list_component`                      |
| `add_grade`        | Adds a grade for a student for a specific component          | `add_grade i/A1234567X c/Quiz 1 s/45` |
| `delete_grade`     | Deletes a student's grade for a specific component           | `delete_grade i/A1234567X c/Quiz 1`   |
| `list_grade`       | Lists all grades for a student                               | `list_grade i/A1234567X`              |
| `bye`              | Exits the program                                            | `bye`                                 |

---

## Coming Soon...
- `update_student`: updates a student entry in the app
- `update_grade`: updates a grade entry in the app
- `filter_student`: filters students by percentage score
- `filter_grade`: filters grades by percentage score
