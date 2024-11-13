---
title: Kenneth
parent: About Us
# nav_order: 5
---

# Kenneth's Project Portfolio Page

## Project: MediTask

MediTask is a desktop application designed to help nurses efficiently manage and track their daily tasks via a Command Line Interface (CLI). This CLI tool streamlines task management, allowing nurses to quickly organize, monitor, and complete tasks, ensuring no important steps are missed.

---

### Summary of Contributions

**New Features**
- **User Interface (`Ui.java`) Enhancements**
  - **What it does**: Provides methods for displaying information and interacting with users, including main screens, task lists, and patient lists with task completion rates.
  - **Highlights**: Implemented `showTaskScreen` and `showPatientListWithCompletionRate`, which display a formatted list of patients or tasks with visual indicators of completion status, improving user interaction.
  - **Challenges**: Enhanced completion rate displays, implemented color-coded outputs, and ensured consistent messaging across the UI, enhancing usability.

- **Completion Rate Feature** ([PR #98](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/98))
  - **What it does**: Calculates and displays task completion rates for individual patients and the overall hospital.
  - **Justification**: Helps users quickly see patient progress, aiding in task prioritization.
  - **Implementation Highlights**:
    - **Show Task List for a Patient**: Displays task list with completion percentage.
    - **Show Patient List with Completion Rate**: Aggregates task completion data across all patients to provide an overall progress view.

- **Tag Patient Feature** ([PR #77](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/77))
  - **What it does**: Allows users to add tags to patients for quick identification (e.g., "VIP" or "Critical").
  - **Implementation**:
    - Developed the `AddPatientCommand` to include optional tags.
    - Displayed tags in formatted outputs, helping users categorize and prioritize patient care.
  - **Diagrams**: Added class diagram illustrating the relationships and flow for tagging patients.

- **Task Tagging Feature** ([PR #105](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/105))
  - **What it does**: Allows users to add optional tags to tasks (`Todo`, `Deadline`, `Repeat`) for better task categorization and prioritization.
  - **Implementation Highlights**:
    - Modified constructors and methods across `Todo`, `Deadline`, `Repeat`, and `Task` classes to accept and display tags.
    - Updated the `toString` method in `Task` to display tags, ensuring clear identification of task priorities.

- **State Switching for Patient and Task Management**([PR #36](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/36))
  - **What it does**: Enables switching between `MAIN_STATE` (patient management) and `TASK_STATE` (task management for a selected patient), allowing the system to respond appropriately to context-specific commands.
  - **Justification**: Improves usability by restricting certain commands based on the application state, ensuring that commands are context-appropriate.
  - **Implementation Highlights**:
    - **SelectPatientCommand**: Modified to set the application state to `TASK_STATE` when a patient is selected, enabling task-specific commands.
    - **BackCommand**: Implemented functionality to return the application to `MAIN_STATE`, clearing the selected patient to prevent residual data from affecting the main menu. Added logging to handle and report invalid transitions when already in `MAIN_STATE`.

**Documentation**
- **Developer Guide (DG)**: ([PR #98](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/98))
  - Documented state-switching logic in `SelectPatientCommand` and `BackCommand`, detailing the transitions between `MAIN_STATE` and `TASK_STATE`.
  - Included a sequence diagram illustrating how state transitions occur when selecting a patient and returning to the main state, helping developers understand the flow of state-dependent commands.
  - Documented `Ui` class functionalities and methods, including descriptions and sequence diagrams for task completion and patient management features.
  - Added sequence diagrams for `showTaskList` and `showPatientListWithCompletionRate` functions ([PR #98](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/98)), showing how data flows through the system.

**Enhancements to Existing Features** ([PR #105](https://github.com/AY2425S1-CS2113-T11-1/tp/pull/105))
- **Updated Task Creation in `Task.java`**
  - Enhanced `createTask` method to handle optional tags for `Todo`, `Deadline`, and `Repeat` tasks.
  - Ensured that tagged tasks display correctly in the CLI, providing users with organized and labeled task lists.

**Code Contributed**: [Link to Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=kenneth&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=kenneth&tabRepo=AY2425S1-CS2113-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Project Management**
- Participated in design discussions, contributing to project structure and feature planning.

**Testing**
- Developed and executed unit tests for `Ui.java`, validating output methods and ensuring they match expected formats:
  - `readCommand` and `showLine` tests for accurate user input handling.
  - Ensured `closeScanner` function works without exceptions, maintaining resource management.

---
