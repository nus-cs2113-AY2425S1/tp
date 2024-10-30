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

### Adding a patient: `add`
Adds a new patient to the list of patients.
A patient with the same name cannot be added twice.
Format: `add NAME /tag TAG_NAME`

### Adding a todo task: `todo`
Adds a new item to the list of todo items.
Format: `todo TODO_NAME /tag TAG_NAME`

### Adding a deadline task: `deadline`
Adds a new item to the list of deadline items.
Format: `deadline DEADLINE_NAME /by DATE_TIME /tag TAG_NAME `

### Adding a recurring task: `repeat`
Adds a new item to the list of recurring items.
Format: `repeat TODO_NAME /every RECUR_BASIS /tag TAG_NAME`

### Finding task: `find`
Finds an existing item in the list of task items.
Format: `find KEYWORD`

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
