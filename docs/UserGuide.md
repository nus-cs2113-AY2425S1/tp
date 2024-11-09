---
title: User Guide
nav_order: 1
---

# User Guide

MediTask is a desktop application designed to help nurses efficiently manage and track their daily tasks via a Command Line Interface (CLI). This CLI tool streamlines task management, allowing nurses to quickly organize, monitor, and complete tasks, ensuring no important steps are missed.

* Table of Contents
{:toc}

## Quick Start

1. Ensure that you have Java `17` or above installed.
2. Down the latest version of `MediTask` from [here](https://github.com/AY2425S1-CS2113-T11-1/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your `MediTask`.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tp.jar` command to run the application.<br>
   ![Ui](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/cli_preview.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   Patient-related commands:
    - `add Alice /tag patient` : Adds a patient named Alice to the list of patients.

    - `list` : Lists all patients.

    - `delete 1` : Deletes the 1st patient in the list.

    - `exit` : Exits the app.

   Task-related commands:

    - `todo Update patient records` : Adds a todo task named "Update patient records" to the list of tasks.

    - `list` : Lists all tasks.

    - `delete 1` : Deletes the 1st task in the list.

    - `mark 1` : Marks the 1st task in the list as done.

    - `unmark 1` : Marks the 1st task in the list as undone.

6. Refer to the [Features](#features) below for details of each command.

## Features

{: .important }
> ℹ️ Notes about the command format:
>
>- Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `exit`) will be ignored. e.g. if the command specifies `help 123`, it will be interpreted as help.
>
>- Parameters cannot be in any order. e.g. if the command specifies `add Alice /tag HighPriority`, `/tag HighPriority add Alice` is not acceptable.
>
>- Ensure that Patient is selected before entering any task-related commands. e.g. `select 1` before adding a task or simply look above the prompt `Patient: <patient_name>`.
>
>- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
> - **Dates and Times** (only in the `deadline` command) must follow specific formats:
>   - **Date Formats:**
>     - `1/9/2024` (d/m/yyyy)
>     - `01/09/2024` (dd/mm/yyyy)
>     - `1-9-2024` (d-m-yyyy)
>     - `2024-09-01` (yyyy-mm-dd)
>   - **Time Formats:**
>     - `930` (hmm)
>     - `0930` (hhmm)
>     - `9:30` (h:mm)
>     - `09:30` (hh:mm)
>     - `9:30AM` (h:mma)
>   - **Datetime Formats:**
>     - `1/9/2024 0930` (d/m/yyyy hhmm)
>     - `01-09-2024 9:30AM` (dd-mm-yyyy h:mma)
>     - `2024-09-01 09:30` (yyyy-mm-dd hh:mm)
>
> - **Supported Relative Terms** (only in the `deadline` command):
>   - You may also use relative terms like `today` and `tomorrow`, which will be automatically converted to the current date or the next day.


### Viewing Help: `help`

Prints a list of available commands and their formats.

![Patient Help Screen](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/help_patient.png)
![Task Help Screen](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/help_task.png)

**Format**: `help`

### Exiting the application: `exit`

Exits the application.

**Format**: `exit`

### Main State
The main state is used to control all patient-related command.

#### **Adding a Patient**: `add`
Adds a patient to the hospital’s patient list.

**Format**: `add NAME /tag TAG_NAME`

**Tip**: A patient can have any number of tags (including none).

**Examples**:
- `add Alice /tag HighPriority`
    - Adds a patient named Alice with the tag "HighPriority."
- `add Bob`
    - Adds a patient named Bob without any tags.

#### **Deleting a Patient**: `delete`
Removes a patient from the hospital’s patient list by their index.

**Format**: `delete INDEX`

{: .highlight }
>**Note**:
>- The index refers to the position of the patient in the list and must be a valid, positive integer.

**Examples**:
- `delete 1`
    - Deletes the first patient in the patient list.
- `delete 3`
    - Deletes the third patient in the patient list.

#### **Finding a Patient**: `find`
Searches for patients in the hospital system whose names contain the specified keyword.

**Format**: `find KEYWORD`

**Examples**:
- `find Alice`
    - Displays all patients whose names contain "Alice."
- `find Bob`
    - Displays all patients whose names contain "Bob."

#### **Listing All Patients**: `list`
Displays all patients currently registered in the hospital system, along with their details and task completion rates.

**Format**: `list`

**Examples**:
- `list`
    - Lists all patients in the hospital system.

#### **Selecting a Patient**: `select`
Switches the application state to `TASK_STATE`, enabling task management features specifically for the selected patient by their index.

**Format**: `select INDEX`

{: .highlight }
> **Note**:
> - The index refers to the position of the patient in the list and must be a valid, positive integer.

**Examples**:
- `select 1`
    - Selects the first patient in the patient list and enables task management features for that patient.
- `select 3`
    - Selects the third patient in the patient list and enables task management features for that patient.

### Task State
The task state is used to control all task-related command. The application enters task state when user selects a patient in the patient list.

#### **Returning to Main State**: `back`
Returns to the `MAIN_STATE` from the patient-specific task management state.

**Format**: `back`

**Example**:
- `back`
    - Returns to the main menu from the selected patient's task management view.

#### **Adding a to-do task**: `todo`
Adds a new task to the list of tasks.

**Format**: `todo TODO_NAME /tag TAG_NAME`

**Examples**:
- `todo Check up /tag urgent`
    - Add task with description "Check up" with "urgent" tag to the bottom of selected patient's task list.

{: .highlight }
> **Note**:
> - Patient must be selected before adding a to-do task.

#### **Adding a deadline task**: `deadline`
Adds a task with deadline to the list of tasks.

**Format**: `deadline DEADLINE_NAME /by DATE_TIME /tag TAG_NAME `

**Examples**:
- `deadline Have medicine /by 19:00 /tag urgent`
    - Add task with description "Have medicine" with "urgent" tag and deadline at 19:00 of the same day to the bottom of selected patient's task list.
      {: .highlight }
> **Note**:
> - Patient must be selected before adding a deadline task.
> - Ensure that the date and time follow the accepted formats, Refer to the
    [Notes about the command format](#Features) for valid date and time formats.

#### **Adding a recurring task**: `repeat`
Adds a task with reminder to repeat it to the list of tasks.

**Format**: `repeat TODO_NAME /every RECUR_BASIS /tag TAG_NAME`

**Examples**:
- `repeat Drink supplements /every day /tag important`
    - Add task with description "Drink supplements" with "important" tag and a reminder "repeat: every day" to the bottom of selected patient's task list.

{: .highlight }
> **Note**:
> - Patient must be selected before adding a repeat task.
> - Relative terms are not supported here.


#### **Deleting a task**: `delete`
Removes a task from the selected patient's list by their index.

**Format**: `delete INDEX`

{: .highlight }
>**Note**:
>- The index refers to the position of the task in the list and must be a valid, positive integer.

**Examples**:
- `delete 1`
    - Deletes the first task in the list.

#### **Finding a task**: `find`
Finds an existing task in the list of task.

**Format**: `find KEYWORD`

**Examples**:
- `find Drink`
    - Displays all tasks with description contains "Drink" in the selected patient's list.

#### **Marking a task**: `mark`
Checking the task as done by their index. This action will update the tasks' completion rate.

**Format**: `mark INDEX`

{: .highlight }
>**Note**:
>- The index refers to the position of the task in the list and must be a valid, positive integer.

**Examples**:
- `mark 1`
    - Mark the first task in the list as done.

#### **Unmarking a task**: `unmark`
Checking the task as undone by their index. This action will update the tasks' completion rate.

**Format**: `unmark INDEX`

{: .highlight }
>**Note**:
>- The index refers to the position of the task in the list and must be a valid, positive integer.

**Examples**:
- `unmark 1`
    - Mark the first task in the list as undone.


### Saving data

MediTask data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MediTask data is saved as a JSON file [JAR file location]/data/hospital_data.json. Advanced users are welcome to update data directly by editing that data file.

{: .warning }
> ⚠️ Caution: If your changes to the data file makes its format invalid, MediTask will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
>
> Furthermore, certain edits can cause the MediTask to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Install the MediTask app on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MediTask home folder.

## Command Summary

| **Action**        | **Format, Examples**                                                                                                                                     |
|-------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | ```add PATIENT_NAME /tag TAG_NAME```<br>e.g., ```add John Doe /tag VIP```                                                                                |
| **Select** | ```select INDEX```<br>e.g., ```select 1```                                                                                                               |
| **Back**          | ```back```                                                                                                                                               |
| **List**          | ```list```<br>- In main state: Lists all patients, showing overall task completion rates.<br>- In task state: Lists all tasks for the selected patient, showing each task’s completion status. |
| **Delete**        | ```delete INDEX```<br>- In main state: Deletes the patient at the specified index.<br>- In task state: Deletes the task at the specified index for the selected patient.<br>e.g., ```delete 2``` |
| **Todo**          | ```todo TODO_NAME /tag TAG_NAME```<br>e.g., ```todo Check vitals /tag Urgent```                                                                          |
| **Deadline**      | ```deadline DEADLINE_NAME /by DATE_TIME /tag TAG_NAME```<br>e.g., ```deadline Administer medication /by 15/09/2024 23:59 /tag Medication```              |
| **Repeat**        | ```repeat TASK_NAME /every INTERVAL /tag TAG_NAME```<br>e.g., ```repeat Check blood pressure /every daily /tag Routine```                                |
| **Find**          | ```find KEYWORD```<br>e.g., ```find John```                                                                                                              |
| **Mark**          | ```mark INDEX```<br>e.g., ```mark 1```                                                                                                                   |
| **Unmark**        | ```unmark INDEX```<br>e.g., ```unmark 1```                                                                                                               |
| **Help**          | ```help```                                                                                                                                               |
| **Exit**          | ```exit```                                                                                                                                               |
