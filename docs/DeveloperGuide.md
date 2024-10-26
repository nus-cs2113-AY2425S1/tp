# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### EasInternship (main class)

1. [Design](#design)
    1. [Architecture](#architecture)
    2. [UI Component](#ui-component)
    3. [Logic Component](#logic-component)
    4. [Model Component](#model-component)
    5. [Storage Component](#storage-component)
2. [Implementation](#implementation)
    1. [Parsing Input](#parsing-input)
    2. [Command Execution](#command-execution)
    3. [Task Management](#task-management)

---

## 1. Design

### 1.1 Architecture
The architecture of EasInternship is designed to follow the MVC (Model-View-Controller) pattern to facilitate separation of concerns, modularity, and maintainability.

- **View (UI)**: Responsible for interacting with the user by displaying output and reading input.
- **Controller (Command and Parser)**: Responsible for parsing user input and invoking the appropriate commands.
- **Model (InternshipList)**: Manages the state of the application, including the list of internships and tasks.
- **Storage**: Responsible for loading and saving data from and to the disk.

---

### 1.2 UI Component

The UI component is responsible for displaying information to the user and reading input. It interacts with the user by printing messages and prompts, and retrieves commands for the rest of the application to process.

### 1.3 Logic Component

The logic component is handled by the `Parser` and `Command` classes. The `Parser` interprets the user's input and returns a command object, which is then executed to perform the desired operation.

### 1.4 Model Component

The model component includes the `InternshipList`, which stores the list of internships or tasks that the user manages. It provides methods for retrieving, adding, and removing internships from the list.

### 1.5 Storage Component

The storage component is responsible for saving the current state of the `InternshipList` to a file and loading it back when the application is restarted. This ensures that users' progress is preserved between sessions.

---

## 2. Implementation

### 2.1 Parsing Input

The `Parser` class is responsible for parsing the input strings provided by the user. It breaks down the input into individual components like the command and its accompanying arguments. After parsing, it returns a `Command` object.

### 2.2 Command Execution

The `Command` class and its subclasses handle the execution of specific commands. Once a command is parsed, it is executed with the required arguments, and the result is displayed to the user.

### 2.3 Task Management

The `InternshipList` class handles adding, removing, and retrieving internships from the list. It interacts with both the UI and the storage components to ensure that updates are reflected in the user interface and persisted to disk.

---

### Overview

The `EasInternship` class serves as the entry point of the application. It manages the application's main loop, where the user is continually prompted for input, and commands are processed in response. The class is responsible for initializing the UI, loading saved data, and handling user input until the user chooses to exit the program.

### Key Responsibilities

- **Initialization**: Initializes the necessary components for the application, such as the `Ui`, `InternshipList`, and `Parser` classes. It also loads any saved data from the storage into the `InternshipList`.
- **Main Loop**: Continuously prompts the user for input. The input is parsed by the `Parser`, and the corresponding `Command` is executed. If the user enters the exit command, the loop terminates.
- **Command Processing**: After parsing the user input, the corresponding `Command` object is executed. The application catches and displays any errors that occur during command execution.
- **Saving State**: Upon exiting, the current state of the `InternshipList` is saved to a file to ensure persistence across sessions.

### Flow of Execution

1. **Welcome Message**: The `Ui` class displays a welcome message to the user.
2. **Load Data**: The `Storage` class loads any saved internships into the `InternshipList`.
3. **Input Loop**: The application enters a loop, prompting the user for input.
4. **Command Parsing**: The `Parser` interprets the input and returns the appropriate `Command`.
5. **Command Execution**: The command is executed, and the `InternshipList` is updated accordingly.
6. **Exit Flow**: If the user enters the exit command, the `InternshipList` is saved, and the application displays a goodbye message.

### Methods

- `public static void main(String[] args)`: The entry point of the application. It handles initialization, the main user input loop, and command execution.

### Error Handling

If the user inputs an unknown command or a command fails during execution, the `Ui` class displays an appropriate error message. This ensures that the application can continue running despite errors in user input.

---

### SortCommand Implementation

#### Overview:
The `SortCommand` class is responsible for sorting the internship listings based on different criteria such as the internship role (alphabetically) or the application deadline (by start and end dates). It extends the `Command` class, providing the sorting functionality as part of the command execution framework.

#### Design:
- The `SortCommand` class processes user input to determine which sorting method to apply (e.g., role or deadline).
- If no arguments or invalid arguments are given, it defaults to listing internships by ID.

#### Key Methods:
- **`execute(ArrayList<String> args)`**: Handles sorting logic based on the first argument provided. If the argument is "alphabet", internships are sorted by role; if the argument is "deadline", internships are sorted by start and end dates. If no valid argument is provided, it defaults to listing by ID.
- **`getUsage()`**: Returns a string showing the correct usage of the `sort` command, including valid options like "alphabet" or "deadline".

#### Example Usage Scenario:
1. The user enters `sort alphabet`, and the `execute` method sorts the internships by role.
2. The user enters `sort deadline`, and the internships are sorted by their start and end dates.
3. If the user enters an invalid sort option, the command returns an error and lists the internships by ID.

#### Sequence Diagram:
The following sequence diagram shows how the `SortCommand` is executed:

```plaintext
User -> System: sort alphabet
System -> SortCommand: execute("alphabet")
SortCommand -> InternshipList: listInternshipsSortedByRole()
InternshipList -> UI: showSortedInternshipsByRole()
```

### Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
