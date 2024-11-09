---
title: Developer Guide
nav_order: 2
---

# Developer Guide

* Table of Contents
{:toc}

---

## Acknowledgements

- This project was inspired by [AddressBook-Level3 (AB3)](https://github.com/se-edu/addressbook-level3), and several code structures were adapted for the MediTask application.
- JSON serialization and deserialization functionality uses the [Jackson library](https://github.com/FasterXML/jackson).
- Logging setup was referenced from open-source Java projects using `Logger`.

---

## Setting up, getting started

Refer to the guide for 'Setting up and getting started.'

---

## Design

### Architecture

![Architecture](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/ArchitectureDiagram.png)

The Architecture Diagram given above explains the high-level design of the MediTask App.

Given below is a quick overview of main components and how they interact with each other.

**Main Components**

`MediTask` is in charge of the CLI to launch and the exit.

- At CLI launch, it initializes the other components in the correct sequence, and connects them up with each other.
- At exit, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the CLI's work id done by the following components:

- [**`UI`**](#ui): Handles the user interface and user interactions.
- [**`Parser`**](#parser): Interprets user commands and executes them.
- [**`Command`**](#command): Contains the logic for executing user commands.
- [**`StateManager`**](#state-manger): Manages the state logic of the application and keeps track of the current state of the application.
- [**`Storage`**](#storage): Handles reading and writing data from and to the hard disk.
- [**`Data`**](#data): Holds the data of the App in memory.
- [**`Common`**](#common): Contains common classes used by other components.

### UI

### Parser

The Parser interface uses a series of classes to implement the various commands.
![Parser_Class_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/ParserClass.png)

### Command

The `commands` package includes the command pattern used in the application to handle user operations. An abstract `Command` class serves as the base for all specific command implementations. Each command class extends `Command` and overrides the `execute()` method to perform its intended action.

![Command_Class_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/CommandClass.png)

### Data

### task
The `task` package manages all task-related functionality. A `Task` class serves as the base for other task types: `Todo`, `Deadline`, and `Repeat`. Each task type extends `Task` and introduces additional attributes relevant to its behavior. The `TaskList` class maintains a collection of tasks and provides methods to add, delete, find and track the completion rate of tasks.

![Task_Class_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/TaskClassDiagram.png)


### Hospital

The `Hospital` class manages the patient data within the system, including adding, deleting, and finding patients. It also manages the selection of a patient for task-related operations, calculates task completion rates, and handles persistence through serialization.

#### Attributes

- **`patients`**: A `List<Patient>` that stores all patients in the hospital system.
- **`selectedPatient`**: A static variable that holds the currently selected patient for task-related operations.
- **`logger`**: A static `Logger` used for logging actions, set to log warnings and errors only.

#### Key Methods

1. **`addPatient(String name)` / `addPatient(String name, String tag)`**: Adds a new patient to the hospital. The method checks for duplicate patient names before adding and logs the action.
2. **`deletePatient(int index)`**: Deletes a patient by index. It verifies if the index is valid and logs errors if the index is out of bounds.
3. **`getPatient(int index)`**: Retrieves a patient by index, throwing a `PatientNotFoundException` if the index is invalid.
4. **`setSelectedPatient(int index)`**: Sets a patient as the selected patient by index, enabling task-related commands to operate on this patient.
5. **`isDuplicatePatient(String name)`**: Checks if a patient with the specified name already exists.
6. **`calculateOverallCompletionRate()`**: Calculates the total completion rate across all patients' tasks, returning a percentage.
7. **`printList()`**: Prints the list of patients with their names and tags.

#### Class Diagram

The following diagram illustrates the structure of the `Hospital` class and its relationships:

![Hospital Class Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/HospitalClassDiagram.png)

- **Data Management**: The class supports data management functions like adding, deleting, and finding patients.
- **Logging**: All major actions, such as adding and deleting patients, are logged at an appropriate level to facilitate debugging and monitoring.
- **Error Handling**: Throws `PatientNotFoundException` for invalid indices, ensuring robustness in data handling.

### State Management

#### State Class

The `State` class manages the application’s current operational mode, allowing the system to handle different types of commands depending on whether it is in the `MAIN_STATE` or `TASK_STATE`.

- **Attributes**: - `currentStage`: Holds the current state of the application, represented by `StateType` enum values.
- **Methods**: - `getState()`: Returns the current state. - `setState()`: Changes the application’s state to the provided `StateType`.

The state ensures that certain commands, like task-related commands, are only available when a patient is selected.

#### State Manager

The `StateManager` class coordinates the application’s state transitions, managing the current state and delegating command execution based on the active state.

- **Attributes**: - `currentState`: Stores the current state as a `State` object.
- **Methods**: - `changeState(StateType state)`: Changes the application state. - `runState(String commandInput, Command command, Hospital hospital)`: Executes commands based on the active state. It distinguishes between `MAIN_STATE` for patient-related commands and `TASK_STATE` for task-related commands. - `runMainState` and `runTaskState`: Helper methods to handle specific command logic within each state.

#### Class Diagram

The following class diagram shows the structure of the `StateManager`:

![StateManager Class Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/StateManagerClassDiagram.png)

#### Implementation Considerations

- **Error Handling**: The `StateManager` throws an `UnknownStateFound` exception if it encounters an unrecognized state, ensuring robustness.
- **State Transitions**: Commands like `SelectPatientCommand` and `BackCommand` are responsible for transitioning between `MAIN_STATE` and `TASK_STATE`.

### Storage

#### Class Diagram

The following class diagram shows the structure of the `Storage` component:

![Storage_Class_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/StorageClassDiagram.png)

**API** : [`StorageFile.java`](https://github.com/AY2425S1-CS2113-T11-1/tp/tree/master/src/main/java/seedu/duke/storage/StorageFile.java)

- Depends on the `Hospital` class in `Data` component as `Storage` component's job to save retrieve objects that belongs to `Data` gracefully.

#### Implementation Considerations

- **Data Persistence**: The `Storage` component ensures that patient and task data is saved to disk and loaded back into the system when the application starts.
- **Error Handling**: `StorageFile` provide error handling for file I/O operations to prevent data loss and maintain data integrity in JSON format.
- **Data Serialization**: The `Storage` component uses JSON serialization to save and load hospital data, ensuring data consistency and compatibility.
- **Data Backup**: The `Storage` component includes a backup mechanism to safeguard critical data and provide a safety net for accidental deletions or corruption.


### Common Classes

Classes that are used by multiple components are placed in the `Common` package.

---

## Implementation

### **Add Patient Command**

The add patient feature allows users to register a new patient within the hospital system. This feature is implemented in the `AddPatientCommand` class, which handles the validation, addition, and logging related to patient registration.

1. **User Input**: The user enters the `add` command followed by the patient's name and an optional tag (e.g., "add John Doe /tag Critical").
2. **Command Parsing**: The `Parser` interprets the input and creates an `AddPatientCommand` object with the specified name and tag.
3. **Execution**: The `AddPatientCommand`: - Checks if a patient with the given name already exists in the hospital system using the `hospital.isDuplicatePatient()` method. - If a duplicate is detected, a severe-level log entry is created, and the user is notified with a message. - If no duplicate is found, the patient is added to the hospital’s records, and a success message is generated.
4. **Storage Update**: The updated hospital data, which now includes the new patient, is saved to storage for persistence across sessions.

#### Sequence Diagram

The following sequence diagram illustrates how the `AddPatientCommand` is executed:

![AddPatientCommand Sequence Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/AddPatientSequenceDiagram.png)

- **Conditional Check**: The command checks for duplicate entries by verifying if a patient with the given name already exists.
- **Logging**: If a duplicate is detected, a severe-level log entry is recorded, and no patient is added.

#### Implementation Considerations

- **Error Handling**: This command includes assertions to validate the presence of a non-null, non-empty name.
- **Logging Configuration**: The logger is set to `Level.SEVERE` to log only warnings and errors.
- **Tag Handling**: The tag attribute is optional, and the command formats the success message based on whether a tag is provided.

### **Delete Patient Command**

The delete patient feature allows users to remove a patient by their index in the hospital’s patient list. This feature is handled by the `DeletePatientCommand` class, which performs validation, deletion, and logging.

1. **User Input**: The user enters the `delete` command followed by the patient's index.
2. **Command Parsing**: The `Parser` converts the input into a `DeletePatientCommand` object with the index adjusted to match the list’s 0-based indexing.
3. **Execution**: The `DeletePatientCommand`: - Verifies if the specified index is valid and corresponds to an existing patient. - If the index is invalid, a severe log entry is generated, and an error message is returned. - If valid, the command retrieves the patient’s name, deletes the patient from the hospital, and generates a success message.
4. **Logging**: The logger is configured to `Level.SEVERE`, and any errors, such as attempting to delete a non-existent patient, are logged.

#### Sequence Diagram

The following sequence diagram illustrates the `DeletePatientCommand` execution:

![DeletePatientCommand Sequence Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/DeletePatientSequenceDiagram.png)

- **Error Handling**: The command checks if the specified index is within bounds and logs errors if the patient is not found.
- **Logging**: Success and error messages are logged for traceability.

### **AddTaskCommand**

The add Task feature allows users to add different types of task to a selected patient's records. This is facilitated by the `AddTaskCommand`, which handles the logic of adding a task and provides feedback on the success or failure of task addition. The feature is only available when the user has navigated to the `TASK_STATE` by selecting a patient.

There are three possible types of tasks - Todo, Deadline and Repeat.

The sequence to add tasks involves:

1. **User Input**:
* **Todo**: The user enters the `todo` command followed by tag details (e.g., /tag).
* **Deadline**: The user enters the `deadline` command followed by the deadline (e.g., /by) and tag details (e.g., /tag).
* * **Repeat**: The user enters the `repeat` command followed by the recurring basis (e.g., /every) and tag details (e.g., /tag).
2. **Command Parsing**:

- **Todo Task**: The `Parser` parses the input and creates an `AddTodoParser` object.
- **Deadline Task**: The `Parser` parses the input and creates an `AddDeadlineParser` object.
- **Repeat Task**: The `Parser` parses the input and creates an `AddRepeatParser` object.

3. **Execution**: The `AddTaskCommand` verifies if the input has a valid taskType and has sufficient arguments for selected type. If the input is valid, it is then verified if a task with the same description already exists within selected patient's task list. If there isn't, new task will be added to the list and user will be notified of the addition.
4. **Storage Update**: The updated patient's data, now containing the new task, is saved to storage.

#### Sequence Diagram

The following sequence diagram illustrates how `AddTaskCommand` is executed:

![Add_Task_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/AddTaskSequenceDiagram.png)

#### Implementation considerations:

The `AddTaskCommand` has been implemented with a design that prioritizes scalability and maintainability. This allows the system to easily support additional task types in the future without requiring major code changes. For example, if new task types are needed in the future (e.g., `event`, `appointment`), they can be added by simply extending the Task class hierarchy and updating the createTask() factory method.

### **FindCommand**

The find feature allows users to find the name of a patient, or the name of a task. This is facilitated by the `FindPatientCommand` and `FindTaskCommand`.

1. **User Input**: The user enters the `find` command followed by keywords
2. **Command Parsing**: The `Parser` parses the input and creates a `FindParser` object.
3. **Execution**: The `FindPatientCommand` or `FindTaskCommand` searches for the task.

#### Sequence Diagram

The following sequence diagram illustrates how the `FindPatientCommand` is executed:

![Find_Sequence_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/FindSequenceDiagram.png)

### **Completion Rate Feature**

The completion rate feature provides users with task progress summaries for individual patients and across all patients in the hospital. It consists of two main commands:

1. **Show Task List for a Patient**: Displays a specific patient’s tasks with the percentage completed. - The user initiates `showTaskList()`. - `Ui` retrieves the selected `Patient` from `Hospital`, then calls `getTaskList()` to access tasks. - The `TaskList` calculates the completion rate and returns it to `Ui`. - `Ui` formats and displays the task list and completion rate to the user.

2. **Show Patient List with Completion Rate**: Displays all patients with their overall task completion rates. - The user initiates `showPatientListWithCompletionRate()`. - `Ui` calls `calculateOverallCompletionRate()` on `Hospital`, which iterates through each `Patient` to get individual completion rates from their `TaskList`. - `Hospital` aggregates these to determine the overall completion rate, then returns the list of patients with completion data. - `Ui` formats and displays the list to the user.

#### Sequence Diagram

The following sequence diagram illustrates the **Completion Rate Feature**:

![Completion Rate Sequence Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/CompletionRateSequenceDiagram.png)

### **Tag Patient Feature**

The Tag Patient Feature allows users to assign tags for each patient, providing additional information for quick identification. This feature is managed through the `AddPatientCommand`

1. **User Input**: The user can add or edit a patient's tag by including `/tag` followed by the tag text with the `addPatient` command (e.g., `add John Doe /tag Critical`).
2. **Command Parsing**: The `Parser` parses the input, identifying and handling tags with `AddParser`, and creates an `AddPatientCommand`
3. **Execution**:`AddPatientCommand` stores the patient with the specified name and tag in the `Hospital`.
4. **Storage Update**: The `Hospital`’s updated patient list, including the new or edited tag, is saved to storage.

#### Sequence Diagram

The following sequence diagram illustrates how the **Tag Patient Feature** operates when adding or updating a tag on a patient:

![TagFeature Sequence Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/TagPatientClassDiagram.png)

- **Conditional Check**: `AddPatientCommand` verifies that the patient does not already exist before adding them.
- **Tag Display**: When viewing patient information, tags are displayed in a formatted way, such as `[VIP]` or `[Critical]`, if provided.

This feature enables users to quickly categorize and prioritize patients within the hospital system by adding relevant tags to patient profiles.

### **State Switching Feature**

The state switching feature allows the system to change its mode of operation between `MAIN_STATE` (for patient management) and `TASK_STATE` (for task management). This allows the software to handle commands differently based on the current state.

1. **User Input**: The user enters a command pertaining either to patients (in `MAIN_STATE`) or tasks (in `TASK_STATE`).
2. **Command Parsing**: The `Parser` checks the current state of the software by interacting with the `State` class. Depending on the state, it interprets the command differently.
3. **State Checking**: The `State` class tracks the current state of the application. If the system is in `MAIN_STATE`, the `Parser` creates patient-related commands (e.g., `AddPatientCommand`). If it's in `TASK_STATE`, task-related commands (e.g., `AddTaskCommand`)
4. **Command Execution**: The appropriate command is then executed. For example, in `MAIN_STATE`, it adds a new patient, while in `TASK_STATE`, it adds a new task to a patient's task list.
5. **State Transitions**: Commands like `SelectPatientCommand` and `BackCommand`, trigger state transitions.

#### Sequence Diagram

The following sequence diagram illustrates how the state-switching mechanism works:

![State-Switching Sequence Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/StateSwitchingSequenceDiagram.png)

- **State Checking**: When user inputs a command, the `Parser` queries the `State` object to check whether the system is in `MAIN_STATE` or `TASK_STATE`.
- **State Transitions**: When a `SelectPatientCommand` is executed, the system transitions from `MAIN_STATE` to `TASK_STATE`. Vice versa for `BackCommand`.
- **Command Execution**: Commands are executed based on the current state. For example, in `MAIN_STATE`, patient-related commands are processed, while in `TASK_STATE`, task-related commands are handled.

## Product scope

### Target user profile

- Nurses who need to manage their tasks for each patient with tagging.
- Nurses who need to keep track of their tasks for each patient.
- Is reasonably comfortable with the command line interface.
- Is familiar with the concept of tasks and patients.

### Value proposition

- MediTask is a handy tool for nurses to coordinate their tasks according to patients.

## User Stories

| Version | As a ... | I want to ...                                                        | So that I can ...                                                    |
|---------|----------|----------------------------------------------------------------------|----------------------------------------------------------------------|
| v1.0    | nurse    | add tasks with specific details (e.g., patient illness, precautions) | ensure all safety and medical steps are followed for each patient    |
| v1.0    | nurse    | delete tasks that are no longer relevant                             | keep my task list up to date and avoid unnecessary clutter           |
| v1.0    | nurse    | mark tasks as completed                                              | stay organized and ensure all tasks are done during my shift         |
| v1.0    | nurse    | unmark tasks that were incorrectly marked as completed               | quickly correct errors and keep an accurate account of ongoing tasks |
| v1.0    | nurse    | save my tasks                                                        | access and view them after closing and reopening the interface       |
| v1.0    | nurse    | list my tasks                                                        | see all my tasks that I have currently                               |
| v2.0    | nurse    | check my task completion progress                                    | see the rate of completion of my current tasks                       |
| v2.0    | nurse    | add deadlines to my tasks                                            | know when I need to finish my given task                             |
| v2.0    | nurse    | find my task by keyword                                              | check on specific tasks                                              |
| v2.0    | nurse    | find my patient by name                                              | check my tasks for specific patients                                 |
| v2.0    | nurse    | tag my patients                                                      | categorize my patients for easier identification                     |

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 17 or above installed.

2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.

3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary

- **Mainstream OS**: Windows, Linux, Unix, MacOS
- **Personal data**: Data that can be used to identify a person, such as a name and medical records.

## Instructions for manual testing

### Getting Started
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

For more details on the commands of MediTask, please refer to the [User Guide](https://ay2425s1-cs2113-t11-1.github.io/tp/UserGuide.html).
