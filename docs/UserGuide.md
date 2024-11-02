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

{: .important-title }
> :information_source: Notes about the command format:
>
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `exit`) will be ignored. e.g. if the command specifies `help 123`, it will be interpreted as help.
>
> - Parameters cannot be in any order. e.g. if the command specifies `add Alice /tag HighPriority`, `/tag HighPriority add Alice` is not acceptable.
>
> - Ensure that Patient is selected before adding a task. e.g. `select 1` before adding a task or simply look above the prompt `Patient: <patient_name>`.
>
> - If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

### Viewing Help: `help`

Prints a list of available commands and their formats.

![Patient Help Screen](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/help_patient.png)
![Task Help Screen](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/help_task.png)

**Format**: `help`

### Adding a Patient: `add`
Adds a patient to the hospital’s patient list.

**Format**: `add NAME /tag TAG_NAME`

**Tip**: A patient can have any number of tags (including none).

**Examples**:
- `add Alice /tag HighPriority`
   - Adds a patient named Alice with the tag "HighPriority."
- `add Bob`
   - Adds a patient named Bob without any tags.

### Deleting a Patient: `delete`
Removes a patient from the hospital’s patient list by their index.

**Format**: `delete INDEX`

{: .highlight }
> **Note**:
> - The index refers to the position of the patient in the list and must be a valid, positive integer.

**Examples**:
- `delete 1`
   - Deletes the first patient in the patient list.
- `delete 3`
   - Deletes the third patient in the patient list.

### Finding a Patient: `find`
Searches for patients in the hospital system whose names contain the specified keyword.

**Format**: `find KEYWORD`

**Examples**:
- `find Alice`
   - Displays all patients whose names contain "Alice."
- `find Bob`
   - Displays all patients whose names contain "Bob."

### Listing All Patients: `list`
Displays all patients currently registered in the hospital system, along with their details and task completion rates.

**Format**: `list`

**Examples**:
- `list`
   - Lists all patients in the hospital system.

### Selecting a Patient: `select`
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

### Returning to Main State: `back`
Returns to the `MAIN_STATE` from the patient-specific task management state.

**Format**: `back`

**Example**:
- `back`
    - Returns to the main menu from the selected patient's task management view.

### Adding a to-do task: `todo`
Adds a new item to the list of to-do items.
Format: `todo TODO_NAME /tag TAG_NAME`

{: .highlight }
> **Note**:
> - Patient must be selected before adding a to-do task.

### Adding a deadline task: `deadline`
Adds a new item to the list of deadline items.
Format: `deadline DEADLINE_NAME /by DATE_TIME /tag TAG_NAME `

{: .highlight }
> **Note**:
> - Patient must be selected before adding a deadline task.

### Adding a recurring task: `repeat`
Adds a new item to the list of recurring items.
Format: `repeat TODO_NAME /every RECUR_BASIS /tag TAG_NAME`

{: .highlight }
> **Note**:
> - Patient must be selected before adding a repeat task.

### Finding task: `find`
Finds an existing item in the list of task items.
Format: `find KEYWORD`

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Exiting the application: `exit`

Exits the application.

**Format**: `exit`

### Saving data

MediTask data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MediTask data is saved as a JSON file [JAR file location]/data/hospital_data.json. Advanced users are welcome to update data directly by editing that data file.

{: .warning }
> :exclamation: Caution: If your changes to the data file makes its format invalid, MediTask will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
> Furthermore, certain edits can cause the MediTask to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Install the MediTask app on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MediTask home folder.

## Command Summary

- **add** `add PATIENT_NAME /tag TAG_NAME`: Adds a new patient to the list with an optional tag.
- **select** `select INDEX`: Selects a patient and switches to task management mode.
- **back** `back`: Returns to the main menu from the task management view.
- **list** `list`:
    - **In main state**: Lists all patients, showing overall task completion rates.
    - **In task state**: Lists all tasks for the selected patient, showing each task’s completion status.
- **delete** `delete INDEX`:
    - **In main state**: Deletes the patient at the specified index.
    - **In task state**: Deletes the task at the specified index for the selected patient.
- **todo** `todo TODO_NAME /tag TAG_NAME`: Adds a new to-do task with an optional tag.
- **deadline** `deadline DEADLINE_NAME /by DATE_TIME /tag TAG_NAME`: Adds a deadline task with a specified date and time, and an optional tag.
- **repeat** `repeat TASK_NAME /every INTERVAL /tag TAG_NAME`: Adds a recurring task with a specified interval (e.g., daily, weekly) and an optional tag.
- **find** `find KEYWORD`: Searches for a patient or task by the specified keyword.
- **help** `help`: Displays a help window with a list of available commands and their formats.
- **exit** `exit`: Exits the application.
