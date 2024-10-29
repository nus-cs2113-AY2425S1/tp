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

- **UI**: Handles the user interface and user interactions.
- **Parser**: Interprets user commands and executes them.
- **Command**: Contains the logic for executing user commands.
- **StateManager**: Manages the state logic of the application and keeps track of the current state of the application.
- **Storage**: Handles reading and writing data from and to the hard disk.
- **Data**:  Holds the data of the App in memory.
- **Common**: Contains common classes used by other components.

### UI

### Parser
The Parser interface uses a series of classes to implement the various commands.
![Parser_Class_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/ParserClass.png)

### Command
The `commands` package includes the command pattern used in the application to handle user operations. An abstract `Command` class serves as the base for all specific command implementations. Each command class extends `Command` and overrides the `execute()` method to perform its intended action.

![Command_Class_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/CommandClass.png)

### Data
#### task
The `task` package manages all task-related functionality. A `Task` class serves as the base for other task types: `Todo`, `Deadline`, and `Repeat`. Each task type extends `Task` and introduces additional attributes relevant to its behavior. The `TaskList` class maintains a collection of tasks and provides methods to add, delete, find and track the completion rate of tasks.

![Task_Class_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/TaskClassDiagram.png)
### State Manager

### Storage

![Storage_Class_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/StorageClassDiagram.png)

**API** : [`StorageFile.java`](https://github.com/AY2425S1-CS2113-T11-1/tp/tree/master/src/main/java/seedu/duke/storage/StorageFile.java)

The `Storage` component,

- Can save hospital data in Json format to a file, and read it back into hospital class.
- Depends on the `Hospital` class in `Data` component as `Storage` component's job to save retrieve objects that belongs to `Data` gracefully.

### Common Classes

Classes that are used by multiple components are placed in the `Common` package.


## Implementation

### **AddPatientCommand**

The add patient feature allows users to add a new patient to the hospital system. This is facilitated by the `AddPatientCommand`, which handles the logic of adding a patient and updating the system’s state and storage.

1. **User Input**: The user enters the `addPatient` command followed by patient details (e.g., name).
2. **Command Parsing**: The `Parser` parses the input and creates an `AddPatientCommand` object.
3. **Execution**: The `AddPatientCommand` checks if the patient already exists in the `Hospital`. If not, it adds the patient.
4. **Storage Update**: The updated hospital data, now containing the new patient, is saved to storage.

#### Sequence Diagram

The following sequence diagram illustrates how the `AddPatientCommand` is executed:

![AddPatientCommand Sequence Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/AddPatientSequenceDiagram.png)

- **If Condition**: The command checks if the patient already exists before adding the patient.
- **Logging**: If a duplicate is detected, an error is logged.

### **AddTaskCommand**

The add Task feature allows users to add different types of task to a selected patient's records. This is facilitated by the `AddTaskCommand`, which handles the logic of adding a task and provides feedback on the success or failure of task addition. The feature is only available when the user has navigated to the `TASK_STATE` by selecting a patient.

There are three possible types of tasks - Todo, Deadline and Repeat.

The sequence to add tasks involves:
1. **User Input**:
* **Todo**: The user enters the `todo` command followed by tag details (e.g., /tag).
* **Deadline**: The user enters the `deadline` command followed by the deadline (e.g., /by) and tag details (e.g., /tag).
* * **Repeat**: The user enters the `repeat` command followed by the recurring basis (e.g., /every) and tag details (e.g., /tag).
2. **Command Parsing**:
* **Todo Task**: The `Parser` parses the input and creates an `AddTodoParser` object.
* **Deadline Task**: The `Parser` parses the input and creates an `AddDeadlineParser` object.
* **Repeat Task**: The `Parser` parses the input and creates an `AddRepeatParser` object.
3. **Execution**: The `AddTaskCommand` adds the task.
4. **Storage Update**: The updated patient's data, now containing the new task, is saved to storage.



#### Sequence Diagram

The following sequence diagram illustrates how `Parser` parse command for `AddTaskCommand`:

![Parse_Add_Task_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/ParseAddTaskClassDiagram.png)

A closer look on how `AddTaskCommand` is executed is shown below:

![Add_Task_Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/AddTaskClassDiagram.png)

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

### **State Switching Feature**

The state switching feature allows the system to change its mode of operation between `MAIN_STATE` (for patient management) and `TASK_STATE` (for task management). This allows the software to handle commands differently based on the current state.

1. **User Input**: The user enters a command pertaining either to patients (in `MAIN_STATE`) or tasks (in `TASK_STATE`).
2. **Command Parsing**: The `Parser` checks the current state of the software by interacting with the `State` class. Depending on the state, it interprets  the command differently.
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

| Version | As a ... | I want to ... | So that I can ... |
|---------|----------|---------------|-------------------|
| v1.0    | nurse    | add tasks with specific details (e.g., patient illness, precautions) | ensure all safety and medical steps are followed for each patient |
| v1.0    | nurse    | delete tasks that are no longer relevant | keep my task list up to date and avoid unnecessary clutter |
| v1.0    | nurse    | mark tasks as completed | stay organized and ensure all tasks are done during my shift |
| v1.0    | nurse    | unmark tasks that were incorrectly marked as completed | quickly correct errors and keep an accurate account of ongoing tasks |
| v1.0    | nurse    | save my tasks | access and view them after closing and reopening the interface |
| v1.0    | nurse    | list my tasks | see all my tasks that I have currently |


## Non-Functional Requirements


1. Should work on any mainstream OS as long as it has Java 17 or above installed.

2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.

3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary

- **Mainstream OS**: Windows, Linux, Unix, MacOS
- **Personal data**: Data that can be used to identify a person, such as a name and medical records.


## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
