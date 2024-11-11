---
title: Low Jun Chen
parent: About Us
# nav_order: 5
---

# Low Jun Chen's Project Portfolio Page

## Project: MediTask

MediTask is a desktop application designed to help nurses efficiently manage and track their daily tasks via a Command Line Interface (CLI). This CLI tool streamlines task management, allowing nurses to quickly organize, monitor, and complete tasks, ensuring no important steps are missed.

---

Given below are my contributions to the project.

- **New Feature:** `Parser` Class
    - **What it does**: The Parser class interprets and routes different command inputs, creating and executing the appropriate command objects based on user input. It enables the application to handle commands like adding tasks, marking, unmarking, deleting, finding, and listing tasks and patients.
    - **Justification**: A centralized parser is essential for user interactions, allowing diverse commands to be processed and executed in a structured and manageable way. This improves the application’s ability to expand with additional commands.
    - **Highlights**: The Parser class supports error handling and logs specific errors for each command, enabling easier debugging and better user feedback for incorrect inputs.
- **New Feature**:`AddTodoParser`, `AddDeadlineParser`, `AddRepeatParser`, and `AddParser` for handling specific add commands.
    - **What it does**: These classes interpret and create command objects to add "todo," "deadline," "repeat" tasks, or new patients. They extract specific parameters such as task name, deadline, recurrence period, and tags.
    - **Justification**: Modular parsers for different add commands allow customization and extensibility in the handling of unique input patterns, facilitating complex task management.
    - **Highlights**: Each parser includes error handling for empty inputs, supports tag extraction, and uses a flexible design to parse optional parameters like tags or deadlines based on user input.
- **New Feature: `ListParser` to handle list commands.**
    - **What it does**: This parser checks the application state and lists either patients or tasks, depending on the current mode.
    - **Justification**: By allowing users to list either tasks or patients depending on context, the parser enhances the usability of the application in managing different types of data.
    - **Highlights**: The ListParser includes state-checking functionality that allows it to distinguish between listing patients in MAIN_STATE and tasks in TASK_STATE.
- **New Feature: `DeleteParser` for deleting tasks or patients.**
    - **What it does**:  Interprets the command for deleting a specified task or patient by extracting the ID and determining whether the deletion applies to tasks or patients based on the current state.
    - **Justification**: This parser provides a uniform way to handle deletion commands while adapting to context, which makes command processing simpler and more consistent.
    - **Highlights**:  It includes input validation and state-dependent behavior, improving user experience by automatically interpreting whether the deletion refers to a task or a patient.
- **New Feature:** `FindParser` Class
    - **What it does**: Extracts search terms from user input and initiates a search command for either tasks or patients, based on the application’s current state.
    - **Justification**: Searching by keywords allows users to quickly locate specific tasks or patients, enhancing navigation within the application.
    - **Highlights**: The parser supports dual functionality based on state, making it intuitive to search across different entities without needing multiple commands.
- **New Feature:** `SelectParser` Class
    - **What it does**: Parses commands for selecting a specific patient by ID and switches the application state to enable task management for the selected patient.
    - **Justification**: This enables focused task management for a specific patient, enhancing the flexibility of the application for patient-centric task tracking.
    - **Highlights**: The SelectParser integrates seamlessly with state management, enabling a smooth transition from patient selection to task assignment and viewing.
- **New Feature:** `MarkParser` and `UnmarkParser` Class
    - **What it does**: Parses commands for marking tasks as completed or uncompleted. These parsers extract task IDs and execute the respective marking or unmarking commands.
    - **Justification**: Marking tasks helps users track progress and completion, an essential feature in task management.
    - **Highlights**: The parsers handle errors gracefully if invalid IDs are provided, and they validate that marking commands are only executed in the TASK_STATE.
- **New Feature:** `BackParser` Class
    - **What it does**: Parses the "back" command to reset the application state to the main menu, enabling users to exit task management for a patient.
    - **Justification**: This feature provides a convenient way for users to navigate back without restarting the application, improving the user experience.
    - **Highlights**: Integrates smoothly with the state management system, ensuring that users can return to the main state reliably from any task-related view.
- **New Feature:** `ExitParser` Class
    - **What it does**: Parses the "exit" command to close the application, allowing users to quit without needing to close the program manually.
    - **Justification**: Directly quitting from within the application enhances usability and provides a clean, controlled exit.
    - **Highlights**: This parser ensures that the application is closed properly and can be easily customized for additional shutdown operations if needed.


- **Code contributed**: [Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=t11&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=lowjunchen&tabRepo=AY2425S1-CS2113-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- **Project management**:
    - Managed release `v1.0` (1 release) on GitHub
- **Documentation**:
    - **User Guide**:
        - Documented `Add`, `Todo`, `Deadline`, `Repeat`, and `Find` patient commands to improve command clarity and user onboarding ([PR #92](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/92)).
    - **Developer Guide**:
        - Added sequence diagrams for `AddTask`, `FindPatient` commands ([PR #88](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/88), [PR #92](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/92)), illustrating execution flow.
        - Included class diagrams for `Parser` classes ([PR #92](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/92)), clarifying structure and relationships.
- **Community and Contributions**:
    - **PRs Reviewed (with non-trivial comments)**: [PR #77](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/77)
    - Contributed to design discussions on project structure and best practices.
